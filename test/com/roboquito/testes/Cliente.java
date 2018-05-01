package com.roboquito.testes;

import com.roboquito.email.cliente.service.Arquivo;
import com.roboquito.email.cliente.service.CriptografiaAES;
import com.roboquito.email.cliente.service.CriptografiaRSA;
import java.net.Socket;

import com.roboquito.email.cliente.service.Util;
import com.roboquito.email.model.Pacote;
import com.roboquito.email.model.ServerMethods;
import java.security.PrivateKey;
import java.util.ArrayList;
import javax.crypto.spec.SecretKeySpec;

public class Cliente {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {

        // Cria um pacote e define o método que o servidor deve executar ao recebê-lo
        Pacote pacote = new Pacote();
        pacote.setMetodo(ServerMethods.GET_ALL_OBJECTS);

        // Estabelece conexão via socket e envia o objeto pacate para o servidor
        Socket socket = new Socket("127.0.0.1", 5000);
        Util.enviarObjeto(pacote, socket.getOutputStream());

        // Ler o pocote enviado pelo servidor
        ArrayList<Pacote> pacotes = (ArrayList<Pacote>) Util.lerObjecto(socket.getInputStream());

        // Ler a chave privada no arquivo e em seguida decripta a chave simétrica recebida do servidor
        PrivateKey privatekey = (PrivateKey) Arquivo.lerObject("C:/keys/private.key");
        for (Pacote p : pacotes) {
            byte[] skey = CriptografiaRSA.decriptografa(p.getChaveSimetrica(), privatekey);
            // Instancia uma SecretKeySpec apartir da chave simétrica recebida do servidor
            SecretKeySpec skeyspec = new SecretKeySpec(skey, "AES");

            // Exibe as informações contidas no pacote recebido do servidor
            System.out.println("Remetente: " + p.getRemetente());
            System.out.println("Destinatário: " + p.getDestinatario());
            System.out.println("MENSAGEM RECEBIDA DO SERVIDOR: " + new String(CriptografiaAES.decriptografar(skeyspec, p.getMensagem())));

        }
/*
        byte[] skey = CriptografiaRSA.decriptografa(pacote.getChaveSimetrica(), privatekey);

        // Instancia uma SecretKeySpec apartir da chave simétrica recebida do servidor
        SecretKeySpec skeyspec = new SecretKeySpec(skey, "AES");

        // Exibe as informações contidas no pacote recebido do servidor
        System.out.println("Remetente: " + pacote.getRemetente());
        System.out.println("Destinatário: " + pacote.getDestinatario());
        System.out.println("MENSAGEM RECEBIDA DO SERVIDOR: " + new String(CriptografiaAES.decriptografar(skeyspec, pacote.getMensagem())));
*/
    }

}
