package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletAccueilConnecte
 */
@WebServlet("/accueilconnecte")
public class ServletAccueilConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		request.setAttribute("page", "accueilconnecte");
		request.setAttribute("title", "Accueil - connecté");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(1);
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateurPseudo =  utilisateurManager.selectByPseudoPassword(request.getParameter("identifiant"), request.getParameter("motDePasse"));
		Utilisateur utilisateurEmail =  utilisateurManager.selectByEmailPassword(request.getParameter("identifiant"), request.getParameter("motDePasse"));
		Utilisateur utilisateur = null;
		
		if (utilisateurPseudo != null ) {
			utilisateur = utilisateurPseudo;
		}
		else if (utilisateurEmail != null ) {
			utilisateur = utilisateurEmail;
		}

		if (utilisateur != null ){
			System.out.println(2);
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", utilisateur);
			session.setMaxInactiveInterval (300);
			
			CategorieManager categorieManager = new CategorieManager ();
			List<Categorie> categories = categorieManager.selectAll();
			request.setAttribute("categories", categories);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			request.setAttribute("page", "accueilconnecte");
			request.setAttribute("title", "Accueil - connecté");
			rd.forward(request, response);	
		}
		else {
			System.out.println(3);
			response.sendRedirect("redirection?page=login");
			
		}
	}

}

