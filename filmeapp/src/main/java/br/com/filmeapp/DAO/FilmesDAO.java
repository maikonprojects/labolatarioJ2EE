package br.com.filmeapp.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.filmeapp.session.ConexaoDB; 
import br.com.filmeapp.model.Filme;

public class FilmesDAO {

    public List<Filme> listarFilmes() {
        List<Filme> lista = new ArrayList<>();
        
        try {
        	Connection con = ConexaoDB.getConnection();
            Statement stmt = con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT id, nome FROM filmes");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                lista.add(new Filme(id, nome));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public List<Filme> adicionarFilme(String filme) {
        List<Filme> lista = new ArrayList<>();

        try {
        	Connection con = ConexaoDB.getConnection();
            Statement stmt = con.createStatement();

            // Inserir novo filme
            stmt.executeUpdate("INSERT INTO filmes (nome) VALUES ('" + filme + "')");

            // Buscar todos os filmes após inserção
            ResultSet rs = stmt.executeQuery("SELECT id, nome FROM filmes");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                lista.add(new Filme(id, nome)); // Corrigido: adiciona objeto Filme
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public boolean removerFilme(int id) {
        boolean sucesso = false;

        try {
        	Connection con = ConexaoDB.getConnection();
            
            String sql = "DELETE FROM filmes WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            int linhasAfetadas = pstmt.executeUpdate();
            sucesso = (linhasAfetadas > 0);

            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    public boolean editarFilme(int idFilme, Filme filme) {
        boolean sucesso = false;

        try {
        	Connection con = ConexaoDB.getConnection();

            String sql = "UPDATE filmes SET nome = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, filme.getNome());
            pstmt.setInt(2, idFilme);

            int linhasAfetadas = pstmt.executeUpdate();
            sucesso = (linhasAfetadas > 0);

            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }
    
}
