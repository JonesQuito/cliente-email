package com.roboquito.email.cliente.controller;

import com.roboquito.email.cliente.service.Arquivo;
import com.roboquito.email.cliente.service.CriptografiaAES;
import com.roboquito.email.cliente.service.CriptografiaRSA;
import com.roboquito.email.cliente.service.Util;
import com.roboquito.email.cliente.view.CaixaEntradaView;
import com.roboquito.email.cliente.view.NovaMensagem;
import com.roboquito.email.model.Cliente;
import com.roboquito.email.model.Pacote;
import com.roboquito.email.model.ServerMethods;
import java.io.File;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class NovaMensagemController {

    private static NovaMensagem viewTelaPrincipal;
    private Cliente usuario = null;

    public NovaMensagemController(NovaMensagem view, Cliente usuario) {
        this.viewTelaPrincipal = view;
        this.usuario = usuario;
    }

    public void bloquearTelaPrincipal() {
        viewTelaPrincipal.setEnabled(false);
    }

    public void desbloquearTelaPrincipal() {
        viewTelaPrincipal.setEnabled(true);
    }

    public void abrirCaixaEntrada() {
        new CaixaEntradaView(this.usuario);
    }

    public void enviarPocote(Pacote pacote) throws Exception {
        /*
        *1 - Calcular o MD5 da mensagem original
        *2 - Criptograr o MD5 com a chave privada do remetente
        *3 - Criptografar a mensagem com AES
        *4 - Criptografar a chave simétrica usada para criptografar a mensagem. Usar a chave pública do destinatário
        Resultado: {Mensagem criptografada, chave simétrica criptografada, hash criptografado}
         */

        //###############################################################
        byte[] hashCriptografado = null;
        // Calculo do hash da mensagem
        String hash = Util.md5(new String(pacote.getMensagem()));

        //Criptografado o hash com a chave privada do remetente
        PrivateKey privatekey = (PrivateKey) Arquivo.lerObject("C:/keys/private.key");
        hashCriptografado = CriptografiaRSA.criptografa(hash.getBytes(), privatekey);
        pacote.setHashCriptografado(hashCriptografado);

        System.out.println("Mensagem clara: " + new String(pacote.getMensagem()));
        System.out.println("Hash: " + hash);
        System.out.println("Hash criptografado: " + hashCriptografado);

        PublicKey publickey = (PublicKey) Arquivo.lerObject("C:/keys/public.key");

        hashCriptografado = CriptografiaRSA.decriptografa(hashCriptografado, publickey);
        System.out.println("Hash decriptografado: " + new String(hashCriptografado));

        // Adiciona o hash criptografado no pacote
        //pacote.setHashCriptografado(hashCriptografado);
        //###############################################################
        // Criptografa a mensagem do pacote
        SecretKey skey = CriptografiaAES.getKeyAES();
        SecretKeySpec skeyspec = new SecretKeySpec(skey.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        pacote.setMensagem(CriptografiaAES.criptografar(skeyspec, pacote.getMensagem(), cipher));

        // Criptografa a chave simétrica usada para criptografar a mensagem
        File arquivo = Arquivo.abrirArquivo();
        PublicKey publicKey = (PublicKey) Arquivo.lerObject(arquivo.getAbsolutePath());
        pacote.setChaveSimetrica(CriptografiaRSA.criptografa(skey.getEncoded(), publicKey));
        pacote.setMetodo(ServerMethods.SAVE_OBJECT);

        Socket socket = new Socket("127.0.0.1", 5000);

        Util.enviarObjeto(pacote, socket.getOutputStream());

    }

}
