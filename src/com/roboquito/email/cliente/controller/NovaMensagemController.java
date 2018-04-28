package com.roboquito.email.cliente.controller;

import com.roboquito.email.cliente.view.CaixaEntradaView;
import com.roboquito.email.cliente.view.NovaMensagem;

public class NovaMensagemController {

    private static NovaMensagem viewTelaPrincipal;
    
    
    public NovaMensagemController(NovaMensagem view){
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
