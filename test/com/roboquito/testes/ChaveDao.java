/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboquito.testes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jones
 */
public class ChaveDao {

    private Connection connection;

    public ChaveDao(Connection connection) {
        this.connection = connection;
    }

    //SALVAR CHAVE
    public void salvarChave(String owner, byte[] chave) {
        try {
            String sql = "insert into chave (owner, chave) values (?, ?);";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, owner);
            stmt.setBytes(2, chave);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    //PESQUISAR CHAVE POR OWNER
    public byte[] buscarChave(String owner) {
        byte[] chave = null;
        try {
            String sql = "select chave from chave where owner = ?";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, owner);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                chave = rs.getBytes("chave");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return chave;
    }
}
