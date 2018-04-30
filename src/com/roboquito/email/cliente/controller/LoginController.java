
package com.roboquito.email.cliente.controller;

import com.roboquito.email.cliente.view.Dashboard;
import com.roboquito.email.cliente.view.Login;
import javax.swing.JOptionPane;


public class LoginController {
    
    private static Login viewLogin;
    
    public LoginController(Login viewLogin){
        this.viewLogin = viewLogin;
    }

    
    public void logar(String email, String senha){
        if("jonesdhy@hotmail.com".equals(email) && "123456".equals(senha)){
            Dashboard.getInstance();
            this.viewLogin.dispose();
        }
        
    }
    
    /*
    public static void main(String[] args){
        viewLogin = new Login();
        viewLogin.setVisible(true);
    }
*/
    
}
