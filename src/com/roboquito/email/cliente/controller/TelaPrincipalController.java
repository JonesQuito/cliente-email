package com.roboquito.email.cliente.controller;

import com.roboquito.email.cliente.view.CaixaEntradaView;
import com.roboquito.email.cliente.view.TelaPrincipal;

public class TelaPrincipalController {

    private static TelaPrincipal viewTelaPrincipal;
    
    
    public TelaPrincipalController(TelaPrincipal view){
        this.viewTelaPrincipal =  view;
    }

    public void bloquearTelaPrincipal() {
        viewTelaPrincipal.setEnabled(false);
    }

    public void desbloquearTelaPrincipal() {
        viewTelaPrincipal.setEnabled(true);
    }
    
    public void abrirCaixaEntrada(){
        new CaixaEntradaView();
    }

}
