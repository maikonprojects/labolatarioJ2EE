package lab02;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class CalculadoraServelet extends HttpServlet{

	 @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        
	        double num1 = Double.parseDouble(req.getParameter("num1"));
	        double num2 = Double.parseDouble(req.getParameter("num2"));
	        String operacao = req.getParameter("operacao");

	        double resultado = 0;
	        String mensagem = "";

	        switch (operacao) {
	            case "+":
	                resultado = num1 + num2;
	                break;
	            case "-":
	                resultado = num1 - num2;
	                break;
	            case "*":
	                resultado = num1 * num2;
	                break;
	            case "/":
	                if (num2 == 0) {
	                    mensagem = "Erro: Divisão por zero.";
	                } else {
	                    resultado = num1 / num2;
	                }
	                break;
	            default:
	                mensagem = "Operação inválida.";
	        }

	        if (mensagem.isEmpty()) {
	            mensagem = "Resultado: " + resultado;
	        }

	        // envia o resultado para a página JSP
	        req.setAttribute("mensagem", mensagem);
	        req.getRequestDispatcher("index.jsp").forward(req, resp);
	    }
}
