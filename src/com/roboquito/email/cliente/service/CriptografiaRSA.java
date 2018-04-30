package com.roboquito.email.cliente.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
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
  public static final String PATH_CHAVE_PRIVADA = "C:/keys/private.key";
 
  /**
   * Local da chave publica no sistema de arquivos.
   */
  public static final String PATH_CHAVE_PUBLICA = "C:/keys/public.key";
 
  /**
   * Gera a chave que contém um par de chave Privada e Pública usando 1025 bytes.
   * Armazena o conjunto de chaves nos arquivos private.key e public.key
   */
  public static KeyPair geraChave() {
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
}