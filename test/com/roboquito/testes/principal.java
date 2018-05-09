package com.roboquito.testes;

import com.roboquito.email.cliente.service.Util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

public class principal {
	//Função para criar hash da mensagem informada
	public static String md5(String mensagem){
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
                //return hash;
	}
	public static void main(String[] args) {
		String senha = JOptionPane.showInputDialog("Digite uma senha:");
		//String saida = "Entrada: " + senha + "\nSenha com MD5: " + md5(senha);
                String saida = "Entrada: " + senha + "\nSenha com MD5: " + Util.md5(senha);
                System.out.println("Resultado: " + saida);
		JOptionPane.showConfirmDialog(null,saida, "Resultado", JOptionPane.CLOSED_OPTION);    
	}
}