package com.roboquito.email.cliente.controller;

import com.roboquito.email.cliente.service.Arquivo;
import com.roboquito.email.cliente.service.CriptografiaAES;
import com.roboquito.email.cliente.service.CriptografiaRSA;
import com.roboquito.email.cliente.service.Util;
import com.roboquito.email.cliente.view.CaixaEntradaView;
import com.roboquito.email.cliente.view.NovaMensagem;
import com.roboquito.email.model.Pacote;
import com.roboquito.email.model.ServerMethods;
import java.io.File;
import java.net.Socket;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class NovaMensagemController {

    private static NovaMensagem viewTelaPrincipal;
    
    
    public NovaMensagemController(NovaMensagem view){
        this.viewTelaPrincipal =  view;
    }

    public void bloquearTelaPrincipal() {
        viewTelaPrincipal.setEnabled(false);
    }

    public void desbloquearTelaPrincipal() {
        viewTelaPrincipal.setEnabled(true);
    }
    
    public void abrirCaixaEntrada(){
        new CaixaEntradaView();
    }
    
    public void enviarPocote(Pacote pacote) throws Exception{
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
