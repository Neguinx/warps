/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.com.warps.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.com.warps.Main;

/**
 *
 * @author Lenovo
 */
public class MySQL {

    //Conexoes
    private Connection connection = null;//Cria uma connection
    private Statement st = null;//Cria um Statment
    //=======================SISTEMA E CONFIGURAÇOES========================

    //<editor-fold defaultstate="collapsed" desc="pegaConexao">
    public synchronized Connection pegaConexao() {//Extende a Conexao
        return createConnection();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="createConnection">
    private Connection createConnection() {//Cria a Conexao apartir dos dados do banco
        try {
            String connStr = "jdbc:mysql://" + Main.Host + ":" + Main.Port + "/" + Main.Banco + "?autoReconnect=true";
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            Connection con = DriverManager.getConnection(connStr, Main.Usuario, Main.Senha);
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }//</editor-fold>

    //=======================METODOS APARTIR DO MYSQL=======================
    public void initBanco() {
        System.out.println("Inicializando Banco de dados !");
        try {
            connection = pegaConexao();
            st = connection.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Warps(Nome VARCHAR(30), world VARCHAR(30), x DOUBLE, y DOUBLE, z DOUBLE, yaw FLOAT, pitch FLOAT, item INT)");
            System.out.println("Banco inicializado com sucesso ");
        } catch (SQLException e) {
            System.out.println("Erro ao iniciar o banco de dados");
        } finally {
            fechaConexao(connection, st);
        }
    }

    /**
     * Fecha apenas a conexao
     *
     * @param conexao Retorna a conexao consultada.
     */
    public static void fechaConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     * Fecha a conexao e o Statement
     *
     * @param conexao Retorna a conexao consultada
     * @param stmt Retorna o statement consultado
     */
    public static void fechaConexao(Connection conexao, Statement stmt) {
        fechaConexao(conexao);
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     * Fecha a conexao, o statement e o resultset
     *
     * @param conexao Retorna a conexao consultada
     * @param stmt Retorna o statement consultado
     * @param rs Retorna o resultset consultado
     */
    public static void fechaConexao(Connection conexao, Statement stmt, ResultSet rs) {
        fechaConexao(conexao, stmt);
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

}
