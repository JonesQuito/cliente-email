/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboquito.email.cliente.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

/**
 *
 * @author Jones
 */
public class ConfigurationServer {
    
    private static String host;
    private static int port;
    
    private static String[] getConfiguration() throws FileNotFoundException, IOException{
        String[] servidor;
        servidor = Arquivo.lerArquivoTxt(new File("fileConfig.txt")).split("\n");

            host = servidor[0];
            port = Integer.parseInt(servidor[1]);

            System.out.println("Host: " + host);
            System.out.println("Port: " + port);
            
            return servidor;
    }

    public static void setConfiguration() throws IOException {

        Path path = Paths.get("fileConfig.txt");
        if(!Files.exists(path)){
            String serverHost = JOptionPane.showInputDialog(null, "Server Host", "Define Host", JOptionPane.QUESTION_MESSAGE);
            String serverport = JOptionPane.showInputDialog(null, "Server Port", "Define Port", JOptionPane.QUESTION_MESSAGE);
            String config = serverHost + "\n" + serverport;
            Arquivo.gravarTxt(config, "fileConfig.txt");
            
        }
        getConfiguration();
    }

    public static String getHost() {
        return host;
    }

    public static int getPort() {
        return port;
    }
    
    

}
