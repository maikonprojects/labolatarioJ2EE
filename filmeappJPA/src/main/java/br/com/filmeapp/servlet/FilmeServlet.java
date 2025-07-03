package br.com.filmeapp.servlet;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.filmeapp.DAO.FilmesDAO;
import br.com.filmeapp.model.Filme;
import br.com.filmeapp.session.FilmeSession;

@WebServlet("/filmes")
public class FilmeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final FilmeSession session = new FilmeSession();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        List<Filme> lista = session.listar();

        request.setAttribute("filmes", lista);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        FilmesDAO dao = new FilmesDAO();
        List<Filme> filmes;

        if ("delete".equals(action)) {
            int idFilme = Integer.parseInt(req.getParameter("idFilme"));
            session.deletar(idFilme);
            
        } else if("post".equals(action)) {
            String nomeFilme = req.getParameter("nomeFilme");
            String notaStr = req.getParameter("notaFilme");
    	    Double notaFilme = Double.parseDouble(notaStr);
            session.inserir(nomeFilme, notaFilme);
            
        } else if("put".equals(action)) {
        	 int idFilme = Integer.parseInt(req.getParameter("idFilme"));
        	    String nomeFilme = req.getParameter("nomeFilme"); 
        	    String notaStr = req.getParameter("notaFilme");
        	    Double notaFilme = Double.parseDouble(notaStr);
        	    Filme filme = new Filme();
        	    filme.setNome(nomeFilme);
        	    filme.setNota(notaFilme);
        	    session.editarFilme(idFilme, filme);
        }

        filmes = dao.listarFilmes(); 
        req.setAttribute("filmes", filmes);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    
	/*
	 * @Override protected void doPut(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException {
	 * 
	 * System.out.println("✅ SERVLET");
	 * 
	 * FilmesDAO dao = new FilmesDAO(); List<Filme> filmes;
	 * 
	 * int idFilme = Integer.parseInt(req.getParameter("idFilme")); String nomeFilme
	 * = req.getParameter("nomeFilme"); Filme filme = new Filme();
	 * filme.setNome(nomeFilme);
	 * 
	 * session.editarFilme(idFilme, filme); filmes = dao.listarFilmes();
	 * req.setAttribute("filmes", filmes);
	 * req.getRequestDispatcher("index.jsp").forward(req, resp); }
	 */

    
}
