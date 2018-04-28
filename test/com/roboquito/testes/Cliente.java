package com.roboquito.testes;

import java.net.Socket;

//import com.roboquito.email.cliente.model.Pacote;
//import com.roboquito.email.cliente.model.ServerMethods;
import com.roboquito.email.cliente.service.Util;
import com.roboquito.email.model.Pacote;
import com.roboquito.email.model.ServerMethods;

public class Cliente {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		Pacote pacote = new Pacote();
		//pacote.setMensagem("Boa tarde professor, por gentileza considera a avaliação do dia 10/03");
		pacote.setMensagem("NetBeans - Mensagem -05");
		pacote.setRemetente("Jones Quito");
		pacote.setMetodo(ServerMethods.GET_ALL_OBJECTS);
		
		Socket socket = new Socket("127.0.0.1", 5000);
		
		Util.enviarObjeto(pacote, socket.getOutputStream());
		
		//pacote = (Pacote)Util.lerObjecto(socket.getInputStream());
		//System.out.println("MENSAGEM RECEBIDA DO SERVIDOR: " + pacote.getMensagem());
		//System.out.println("Conectado: " + socket.isConnected());
		
	}

}