/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboquito.testes.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jones
 */
public class Teste extends javax.swing.JFrame{
    
    public Teste() {

        
        setResizable(false);
        setSize(848, 549);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
    
    public void setarBorderLayout(){
        this.getContentPane().setLayout(new BorderLayout());
        JPanel painelMenu = new JPanel(new GridBagLayout());
        JPanel painelConteudo = new JPanel();
        painelConteudo.setBackground(Color.blue);
        painelMenu.setBackground(Color.red);
        this.getContentPane().add(painelMenu, BorderLayout.WEST);
        this.getContentPane().add(painelConteudo, BorderLayout.CENTER);

      
        /*
        jpUsuario.setBackground(new java.awt.Color(127, 200, 237));
        jpUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jpUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        */
    }
    
    public static void main(String[] args){
        new Teste();
    }
    
}
