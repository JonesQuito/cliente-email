
package com.roboquito.testes;

import com.roboquito.email.cliente.service.CriptografiaRSA;
import com.roboquito.email.cliente.service.Util;
import java.security.KeyPair;
import java.security.KeyPairGenerator;


public class TesteRSA {
    
    public static final String ALGORITHM = "RSA";
    
    public static KeyPair geraChave() {
    try {
      KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
      keyGen.initialize(1024);
      KeyPair key = keyGen.generateKeyPair();
      
      return key;
  
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
 
  }
    public static void main(String[] args){
        
        KeyPair key = geraChave();
        
        String textoClaro = "Jones Quito";
        
        byte[] encripyted = CriptografiaRSA.criptografa(textoClaro.getBytes(), key.getPublic());
        byte[] textoDecriptografado = CriptografiaRSA.decriptografa(encripyted, key.getPrivate());
        
        System.out.println("Texto criptografado: "+ Util.asHex(encripyted));
        System.out.println("Texto decriptografado: "+ new String(textoDecriptografado));
    }
    
}
