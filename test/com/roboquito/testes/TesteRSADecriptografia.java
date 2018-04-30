/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboquito.testes;

import com.roboquito.email.cliente.service.Arquivo;
import com.roboquito.email.cliente.service.CriptografiaRSA;
import com.roboquito.email.cliente.service.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author Jones
 */
public class TesteRSADecriptografia {
    
    static byte[] textoCifrado = null;

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException{
        File arquivo = Arquivo.abrirArquivo();
        PublicKey publickey = (PublicKey)Arquivo.lerObject(arquivo.getAbsolutePath());
        
        //KeyPair kpar = CriptografiaRSA.geraChave();
        
        textoCifrado = CriptografiaRSA.criptografa("Jones Quito".getBytes(), publickey);
        System.out.println("Texto cifrado: " + Util.asHex(textoCifrado));
        
        
        arquivo = Arquivo.abrirArquivo();
        PrivateKey privatekey = (PrivateKey)Arquivo.lerObject(arquivo.getAbsolutePath());
        System.out.println("Texto decifrado: " + new String(CriptografiaRSA.decriptografa(textoCifrado, privatekey)));
        
        
        /*
        arquivo = Arquivo.abrirArquivo();
        PrivateKey key = (PrivateKey)Arquivo.lerObject(arquivo.getAbsolutePath());
        String textoClaro = CriptografiaRSA.decriptografa(textoCifrado, key);
        
        System.out.println("Texto decriptografado: " + textoClaro);
        System.out.println("Chave Privada: " + Util.asHex(key.getEncoded()));
*/
    }
    
}
