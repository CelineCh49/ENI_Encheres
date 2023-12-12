package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bo.Utilisateur;


/**
 * Servlet implementation class ServletArticleEnVente
 */
@WebServlet("/articleenvente")
public class ServletArticleEnVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//j'indique la page index qui est mon layout
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");	
		//j'indique a mon layout qu'il s'agit de la page home
		request.setAttribute("page", "articleenvente");
		//j'indique le titre de la page pour le head
		request.setAttribute("title", "Article en vente");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// récupération des paramètres
		
		
      String nomArticle = request.getParameter("nomArticle");
      String description = request.getParameter("description");
      int noCategorie = Integer.parseInt(request.getParameter("categorie"));
      int prixInitial = Integer.parseInt(request.getParameter("miseAprix"));
      
      String dateDebutEncheresParam = request.getParameter("dateDebutEncheres");
      String dateFinEncheresParam = request.getParameter("dateFinEncheres");
      LocalDateTime dateDebutEncheres = null; // Valeur par défaut ou une autre valeur appropriée si la conversion échoue
      LocalDateTime dateFinEncheres = null;
      try {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
          dateDebutEncheres = LocalDateTime.parse(dateDebutEncheresParam, formatter);
          dateFinEncheres = LocalDateTime.parse(dateFinEncheresParam, formatter);
      } catch (DateTimeParseException e) {
          // Gérer une erreur de conversion, par exemple, enregistrer un message d'erreur ou affecter une valeur par défaut
    	// et affecter l'heure et la date actuelles à la variable dateDebutEncheres
    	    dateDebutEncheres = LocalDateTime.now();
      }
      
        
      //EtatVente etatVente = ?? 
      HttpSession session = request.getSession();
      Utilisateur utilisateur= (Utilisateur) session.getAttribute("utilisateur");
      int noUtilisateurVendeur = utilisateur.getNoUtilisateur();
    		  
      
      
      // refaire un constructeur Article sans prixVente, int noUtilisateurAcheteur
		
		
		
		// qd appuie sur enregistrer:
//		création d'un article
//		création d'une enchère
//		mise à jour bdd article
//		mise à jour bdd enchere
		
	}

}
