
package com.roboquito.email.cliente.controller;

import com.roboquito.email.cliente.view.CaixaEntradaView;
import com.roboquito.email.cliente.view.Dashboard;
import com.roboquito.email.model.Cliente;


public class CaixaEntradaController {
    
    private CaixaEntradaView viewCaixaEntrada;
    private Dashboard viewDashboard;
    private Cliente usuario = null;
    
    
    public CaixaEntradaController(CaixaEntradaView viewCaixaEntrada){
        viewCaixaEntrada = viewCaixaEntrada;
    }
    
    
 
        
    
    public void abrirCaixaEntrada(){
        viewCaixaEntrada = new CaixaEntradaView(this.usuario);
        viewCaixaEntrada.setVisible(true);
    }
    
    public void fecharCaixaEntrada(){
        viewCaixaEntrada.dispose();
    }
    

    
}
