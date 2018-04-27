package com.roboquito.email.cliente.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CriptografiaAES {

    public static SecretKeySpec getKeyAES() throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        //kgen.init(256); 
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        return skeySpec;
    }
    
    
    public static byte[] criptografar(SecretKeySpec skeySpec, String message, Cipher cipher) 
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
            IllegalBlockSizeException, BadPaddingException {
        //Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(message.getBytes());
        return encrypted;
    }
    
    public static String decriptografar(SecretKeySpec skeySpec, byte[] encrypted, Cipher cipher)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
            IllegalBlockSizeException, BadPaddingException{
        //Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] original = cipher.doFinal(encrypted);
        return new String(original);
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
