package br.com.filmeapp.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.filmeapp.session.ConexaoDB;
import br.com.filmeapp.util.JPAUtil;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import br.com.filmeapp.model.Filme;

@Stateless
public class FilmesDAO {

	public FilmesDAO() {
        this.em = JPAUtil.getEntityManager();
    }
	
	 @PersistenceContext(unitName = "filmePU")
	    private EntityManager em;

	    public List<Filme> listarFilmes() {
	        return em.createQuery("SELECT f FROM Filme f", Filme.class).getResultList();
	    }
    
	    public List<Filme> adicionarFilme(String nomeFilme, Double notaFilme) {
	        List<Filme> lista = new ArrayList<>();

	        try {
	            EntityTransaction tx = em.getTransaction();
	            tx.begin();

	            // Criar e persistir o novo filme
	            Filme novoFilme = new Filme();
	            novoFilme.setNome(nomeFilme);
	            novoFilme.setNota(notaFilme);
	            em.persist(novoFilme);

	            // Buscar todos os filmes após inserção
	            TypedQuery<Filme> query = em.createQuery("SELECT f FROM Filme f", Filme.class);
	            lista = query.getResultList();

	            tx.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	            if (em.getTransaction().isActive()) {
	                em.getTransaction().rollback();
	            }
	        }

	        return lista;
	    }

    public boolean removerFilme(int id) {
        boolean sucesso = false;

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Filme filme = em.find(Filme.class, id);
            if (filme != null) {
                em.remove(filme);
                sucesso = true;
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
          
        }

        return sucesso;
    }


    public boolean editarFilme(int idFilme, Filme filme) {
    	System.out.println("✅ DAO ANTES TRY");
    	try {
    		EntityTransaction tx = em.getTransaction();
    		tx.begin();
            Filme existente = em.find(Filme.class, idFilme);
            
            System.out.println(existente.getNome());
            
            if (existente != null) {
                existente.setNome(filme.getNome());
                existente.setNota(filme.getNota());
                em.merge(existente); 
                System.out.println("✅ DAO DENTRO TRY");
                tx.commit();
                return true;
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
