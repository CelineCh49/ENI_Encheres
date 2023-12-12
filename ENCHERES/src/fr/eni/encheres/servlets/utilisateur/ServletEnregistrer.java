package fr.eni.encheres.servlets.utilisateur;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDao;
import fr.eni.encheres.dal.jdbc.UtilisateurDaoJdbcImpl;

/**
 * Servlet implementation class ServletEnregistrerUtilisateur
 */
@WebServlet("/enregistrer")
public class ServletEnregistrer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//j'indique la page index qui est mon layout
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		//j'indique a mon layout qu'il s'agit de la page home
		request.setAttribute("page", "enregistrer");
		//j'indique le titre de la page dans le head
		request.setAttribute("title", "Créer un compte");
		rd.forward(request, response);

    }

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // création d'un nouvel utilisateur avec les données du formulaire
        Utilisateur utilisateur = new Utilisateur(
                request.getParameter("pseudo"),
                request.getParameter("nom"),
                request.getParameter("prenom"),
                request.getParameter("email"),
                request.getParameter("telephone"),
                request.getParameter("rue"),
                request.getParameter("codePostal"),
                request.getParameter("ville"),
                request.getParameter("motDePasse"),
                0,
                false
        );

        try {
            UtilisateurManager utilisateurManager = new UtilisateurManager();
            utilisateurManager.enregistrerUtilisateur(utilisateur);
            response.sendRedirect(request.getContextPath() + "/acceuilconnecte");
        } catch (BLLException e) {
            // Ajouter les erreurs à l'objet request pour pouvoir les afficher dans la JSP
            request.setAttribute("errors", e.getListErrorCodes());
            request.setAttribute("utilisateur", utilisateur);

            // Rediriger à nouveau vers le formulaire d'enregistrement
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
            request.setAttribute("page", "enregistrer");
            rd.forward(request, response);
        } catch (SQLException e) {
            // gérer l'exception SQL
            e.printStackTrace();
        }
    }


}
