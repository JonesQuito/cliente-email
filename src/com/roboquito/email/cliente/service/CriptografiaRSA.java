package com.roboquito.email.cliente.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class CriptografiaRSA {

    public static final String ALGORITHM = "RSA";
 

    /**
     * Local da chave privada no sistema de arquivos.
     */
    public static String PATH_CHAVE;
    static{
        if(System.getProperty("os.name").startsWith("Windows")){
            PATH_CHAVE = "C:/keys/";
        }else{
            PATH_CHAVE = "/home/keys/";
        }
    }


    /**
     * Gera a chave que contém um par de chave Privada e Pública usando 1025
     * bytes. Armazena o conjunto de chaves nos arquivos private.key e
     * public.key
     */
    @Deprecated
    public static KeyPair geraChave2() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024);
            final KeyPair key = keyGen.generateKeyPair();

            return key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Criptografa o texto puro usando chave pública.
     */
    public static byte[] criptografa(byte[] texto, PublicKey chave) {
        byte[] cipherText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Criptografa o texto puro usando a chave Púlica
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            cipherText = cipher.doFinal(texto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     * Criptografa o texto puro usando chave privada.
     */
    public static byte[] criptografa(byte[] texto, PrivateKey chave) {
        byte[] cipherText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Criptografa o texto puro usando a chave Púlica
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            cipherText = cipher.doFinal(texto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     * Decriptografa o texto puro usando chave privada.
     */
    public static byte[] decriptografa(byte[] texto, PrivateKey chave) {
        byte[] dectyptedText;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Decriptografa o texto puro usando a chave Privada
            cipher.init(Cipher.DECRYPT_MODE, chave);
            dectyptedText = cipher.doFinal(texto);
            return dectyptedText;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Decriptografa o texto puro usando chave public.
     */
    public static byte[] decriptografa(byte[] texto, PublicKey chave) {
        byte[] dectyptedText;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Decriptografa o texto puro usando a chave Privada
            cipher.init(Cipher.DECRYPT_MODE, chave);
            dectyptedText = cipher.doFinal(texto);
            return dectyptedText;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean existeChavesNoSO(String chavePublica, String chavePrivada) {

        File privatekey = new File(chavePrivada);
        File publickey = new File(chavePublica);

        if (privatekey.exists() && publickey.exists()) {
            return true;
        }

        return false;
    }

    /**
     * Gera a chave que contém um par de chave Privada e Pública usando 1025
     * bytes. Armazena o conjunto de chaves nos arquivos private.key e
     * public.key
     */
    public static void geraChave(String usuario) {
        

        if(existeChavesNoSO(PATH_CHAVE.concat(usuario).concat("public.key"), 
                            PATH_CHAVE.concat(usuario.concat("privatekey")))){
            return;
        }

        
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(2048);
            final KeyPair key = keyGen.generateKeyPair();

            File chavePrivadaFile = new File(PATH_CHAVE.concat(usuario.concat("private.key")));
            File chavePublicaFile = new File(PATH_CHAVE.concat(usuario).concat("public.key"));

            // Cria os arquivos para armazenar a chave Privada e a chave Publica
            if (chavePrivadaFile.getParentFile() != null) {
                chavePrivadaFile.getParentFile().mkdirs();
            }

            chavePrivadaFile.createNewFile();

            if (chavePublicaFile.getParentFile() != null) {
                chavePublicaFile.getParentFile().mkdirs();
            }

            chavePublicaFile.createNewFile();

            // Salva a Chave Pública no arquivo
            ObjectOutputStream chavePublicaOS = new ObjectOutputStream(
                    new FileOutputStream(chavePublicaFile));
            chavePublicaOS.writeObject(key.getPublic());
            chavePublicaOS.close();

            // Salva a Chave Privada no arquivo
            ObjectOutputStream chavePrivadaOS = new ObjectOutputStream(
                    new FileOutputStream(chavePrivadaFile));
            chavePrivadaOS.writeObject(key.getPrivate());
            chavePrivadaOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
