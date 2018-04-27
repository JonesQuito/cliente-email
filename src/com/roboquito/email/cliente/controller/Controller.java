
package com.roboquito.email.cliente.controller;

import com.roboquito.email.cliente.view.CaixaEntradaView;
import com.roboquito.email.cliente.view.TelaPrincipal;


public class Controller {
    private CaixaEntradaView caixaEntrada;
    private static TelaPrincipal telaPrincipal;
    private static Controller controller = null;
    
    
    public static Controller getController(){
        if(controller == null){
            controller = new Controller();
        }
        return controller;
    }
    
    public void bloquearTelaPrincipal(){
        telaPrincipal.setEnabled(false);
    }
    
    public void desbloquearTelaPrincipal(){
        telaPrincipal.setEnabled(true);
    }
        
    
    public void abrirCaixaEntrada(){
        caixaEntrada = new CaixaEntradaView();
        caixaEntrada.setVisible(true);
    }
    
    public void fecharCaixaEntrada(){
        caixaEntrada.dispose();
    }
    
    
    public static void main(String[] args){
        telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
    }
    
}
