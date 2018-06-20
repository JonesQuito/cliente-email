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

/**
 * 
 * @author Jones
 */
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

    /**
     * 
     * @param pacote
     * @throws Exception 
     */
    public void enviarPocote(Pacote pacote) throws Exception {

        byte[] hashCriptografado = null;
        // Calculo do hash da mensagem
        String hash = Util.md5(new String(pacote.getMensagem()));

        /**
         * Criptografado o hash com a chave privada do remetente
         * Isso funciona como uma assinatura, pois quando o destinatário receber o
         * hash, se ele poder ser decriptografado com a chave pública do remetente
         * é sinal de que foi reamente enviada por quem diz está enviando
        */
     
        String path = CriptografiaRSA.PATH_CHAVE.concat(this.usuario.getEmail().split("@")[0]).concat("private.key");
        System.out.println(path);
        PrivateKey privatekey = (PrivateKey) Arquivo.lerObject(path);
        hashCriptografado = CriptografiaRSA.criptografa(hash.getBytes(), privatekey);
        pacote.setHashCriptografado(hashCriptografado);

        System.out.println("Mensagem clara: " + new String(pacote.getMensagem()));
        System.out.println("Hash: " + hash);
        System.out.println("Hash criptografado: " + hashCriptografado);

        /*
        Tenta obter no servidor a chave pública do destinatário
        */
        Cliente c = new Cliente();
        c.setEmail(pacote.getDestinatario());
        c.setMetodo(ServerMethods.GET_PUBLIC_KEY);
        Socket socket = Util.getSocket();
        Util.enviarObjeto(c, socket.getOutputStream());
        PublicKey publicKey = (PublicKey)Util.lerObjecto(socket.getInputStream());
        System.out.println("passou aqui");
        
        /*
        Gera uma chave simétrica e criptografa a mensagem com essa chave
        */
        SecretKey skey = CriptografiaAES.getKeyAES();
        SecretKeySpec skeyspec = new SecretKeySpec(skey.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        pacote.setMensagem(CriptografiaAES.criptografar(skeyspec, pacote.getMensagem(), cipher));
        
        
        /*
        Verifica se achave pública do destinatário foi obtida junto ao servidor
        caso a busca no servidor não obtenha sucesso, é solicitado ao usuário que
        informe onde está a chave pública do destinatário
        */
        if(publicKey == null){
            File arquivo = Arquivo.abrirArquivo2("Escolher chave pública do destinatário");
            publicKey = (PublicKey) Arquivo.lerObject(arquivo.getAbsolutePath());
        }


        /*
        Usa a chave pública do destinatário para criptografar a chave simétrica
        usada na criptografia da mensagem. Na sequência envia o pacote com dados
        do remetente e destinatário, chave simetrica criptografada, mensagem criptografada,
        hash criptografado etc.
        */
        pacote.setChaveSimetrica(CriptografiaRSA.criptografa(skey.getEncoded(), publicKey));
        pacote.setMetodo(ServerMethods.SAVE_OBJECT);

        socket = Util.getSocket();
        Util.enviarObjeto(pacote, socket.getOutputStream());

    }

}
