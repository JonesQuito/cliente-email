package com.roboquito.email.cliente.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

    private static String host = ConfigurationServer.getHost();
    private static int port = ConfigurationServer.getPort();
    
    public static Socket getSocket() throws IOException {
        return new Socket(host, port);
    }

    //Função para criar hash da mensagem informada
    public static String md5(String mensagem) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(mensagem.getBytes()));
        sen = hash.toString(16);
        return sen;
    }

    public static Object lerObjecto(InputStream inputStream) throws IOException, ClassNotFoundException {
        Object objetoRetorno = null;
        ObjectInputStream objetoOrigem = new ObjectInputStream(inputStream);
        objetoRetorno = objetoOrigem.readObject();
        return objetoRetorno;
    }

    public static void enviarObjeto(Object objeto, OutputStream outputStream) throws IOException {
        ObjectOutputStream objetoDestino = new ObjectOutputStream(outputStream);
        objetoDestino.writeObject(objeto);
        objetoDestino.flush();
        //objetoDestino.close();

    }

    public static String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }

}
