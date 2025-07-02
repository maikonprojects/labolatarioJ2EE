package br.com.filmeapp.session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static final String URL = "jdbc:mysql://localhost:3306/filmesdb";
    private static final String USER = "root";
    private static final String PASSWORD = "solutis";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carrega o driver uma única vez
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}