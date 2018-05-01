/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboquito.testes;

import com.roboquito.email.cliente.service.Util;
import java.io.IOException;
import java.net.UnknownHostException;

import com.roboquito.email.model.Cliente;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Jones
 */
public class TesteAutenticarLogin {
    
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Cliente cliente = new Cliente();
		cliente.setEmail("fernandadhy@hotmail.com");
		cliente.setSenha("123456");
		
		Socket socket = new Socket("127.0.0.1", 5000);
		
		Util.enviarObjeto(cliente, socket.getOutputStream());
		
		cliente = (Cliente) Util.lerObjecto(socket.getInputStream());
		//Cliente c = (Cliente) Util.lerObjecto(socket.getInputStream());
		
                System.out.println(cliente.getNome());

		
	}
    
}
