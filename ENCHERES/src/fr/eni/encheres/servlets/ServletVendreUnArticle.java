package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletVendreUnArticle
 */
@WebServlet("/vendreunarticle")
public class ServletVendreUnArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//j'indique la page index qui est mon layout
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		//j'indique a mon layout qu'il s'agit de la page home
		request.setAttribute("page", "vendreunarticle");
		//j'indique le titre de la page dans le head
		request.setAttribute("title", "Vendre un Article");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}

