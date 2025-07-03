package br.com.filmeapp.session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexaoDB {

	 public static Connection getConnection() throws SQLException {
	        try {
	            InitialContext ctx = new InitialContext();
	            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FilmeDB");
	            return ds.getConnection();
	        } catch (NamingException e) {
	            throw new SQLException("Erro ao buscar DataSource via JNDI", e);
	        }
	    }
}