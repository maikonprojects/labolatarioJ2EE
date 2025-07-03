package br.com.filmeapp.session;

import java.util.List;

import br.com.filmeapp.DAO.FilmesDAO;
import br.com.filmeapp.model.Filme;

public class FilmeSession {
	
	private final FilmesDAO dao = new FilmesDAO();

	public List<Filme> listar() {
		
		return dao.listarFilmes();
		
	}

	public void deletar(int idFilme) {
		
		dao.removerFilme(idFilme);
		
	}

	public void inserir(String nomeFilme) {
		
		dao.adicionarFilme(nomeFilme);
		
	}

	public void editarFilme(int idFilme, Filme filme) {
		System.out.println("✅ SESSION");
		dao.editarFilme(idFilme, filme);
		
	}
	
		

}
