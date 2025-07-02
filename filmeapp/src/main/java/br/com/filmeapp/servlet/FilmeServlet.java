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
            session.inserir(nomeFilme);
            
        } else if("put".equals(action)) {
        	 int idFilme = Integer.parseInt(req.getParameter("idFilme"));
        	    String nomeFilme = req.getParameter("nomeFilme"); 
        	    Filme filme = new Filme();
        	    filme.setNome(nomeFilme);

        	    session.editarFilme(idFilme, filme);
        }

        filmes = dao.listarFilmes(); 
        req.setAttribute("filmes", filmes);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    
	/*
	 * @Override protected void doDelete(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException {
	 * 
	 * String idParam = req.getParameter("idFilme");
	 * 
	 * if (idParam == null || idParam.isEmpty()) {
	 * resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
	 * "ID do filme não informado."); return; }
	 * 
	 * try { int idFilme = Integer.parseInt(idParam); FilmesDAO dao = new
	 * FilmesDAO(); dao.removerFilme(idFilme);
	 * resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 - sucesso, sem
	 * resposta } catch (NumberFormatException e) {
	 * resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido."); } }
	 */

    
}
