
package com.roboquito.email.cliente.controller;

import com.roboquito.email.cliente.service.Util;
import com.roboquito.email.cliente.view.Dashboard;
import com.roboquito.email.cliente.view.Login;
import com.roboquito.email.model.Cliente;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;


public class LoginController {
    
    private static Login viewLogin;
    private Cliente usuario = null;
    
    public LoginController(Login viewLogin, Cliente usuario){
        this.viewLogin = viewLogin;
        this.usuario = usuario;
    }

    
    public void logar(String email, String senha) throws IOException, ClassNotFoundException{
        Socket socket = new Socket("127.0.0.1", 5000);
        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        cliente.setSenha(senha);
        Util.enviarObjeto(cliente, socket.getOutputStream());
        usuario = (Cliente) Util.lerObjecto(socket.getInputStream());
        
        if(usuario != null){
            Dashboard.getInstance(this.usuario);
            this.viewLogin.dispose();
        }else{
            JOptionPane.showMessageDialog(viewLogin, "Usuário ou senha inválidos!");
        }
        
        
    }
    
}
