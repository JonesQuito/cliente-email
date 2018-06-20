/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboquito.testes;

import com.roboquito.email.cliente.service.CriptografiaAES;
import java.sql.Connection;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Jones
 */
public class Teste02 {
    
    private static SecretKeySpec getSks(byte[] semente){
        return new SecretKeySpec(semente, "AES");
    }
    
    public static void main(String[] args) throws Exception{
        System.out.println(System.getProperty("os.name"));
        String texto = "Um texto qualquer";
        
        
        SecretKey skey = CriptografiaAES.getKeyAES();
        SecretKeySpec skeyspec = getSks(skey.getEncoded());
        Cipher cipher = Cipher.getInstance("AES");
        byte[] textoCriptografado = CriptografiaAES.criptografar(skeyspec, texto.getBytes(), cipher);
        
        System.out.println("Texto criptografado em bytes: " + textoCriptografado);
        System.out.println("Texto criptografado string: " + new String(textoCriptografado));
        System.out.println("Texto descriptografado: " + 
                CriptografiaAES.decriptografar(skeyspec, textoCriptografado));
        
        ConnectionFactory cf = new ConnectionFactory();
        Connection connection = cf.getConnection();
        
        ChaveDao cd = new ChaveDao(connection);
        //cd.salvarChave("jonesdhy@hotmail.com", skeyspec.getEncoded());
        
        System.out.println("salvo com êxito!!");
        
        byte[] rs = cd.buscarChave("jonesdhy@hotmail.com");
        
        byte[] criptografado = CriptografiaAES.criptografar(getSks(rs), texto.getBytes(), cipher);
        System.out.println("Texto criptografado: " + criptografado);
        System.out.println("Retorno do banco: " + new String(rs));
        System.out.println("Texto claro: " + 
                CriptografiaAES.decriptografar(getSks(rs), criptografado));
        
        //System.out.println("Texto descriptografado: " + 
          //      CriptografiaAES.decriptografar(getSks(rs), textoCriptografado));
        
        
    }
    
}
