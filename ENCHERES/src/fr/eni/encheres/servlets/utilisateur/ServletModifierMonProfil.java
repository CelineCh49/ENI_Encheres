package fr.eni.encheres.servlets.utilisateur;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDao;
import fr.eni.encheres.dal.jdbc.UtilisateurDaoJdbcImpl;

/**
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/modifiermonprofil")
public class ServletModifierMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  HttpSession session = request.getSession();
	        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

	        if (utilisateur != null) {
	            UtilisateurDao utilisateurDao = new UtilisateurDaoJdbcImpl();
	            try {
	                Utilisateur profilUtilisateur = utilisateurDao.selectByPseudo(utilisateur.getPseudo());

	                if (profilUtilisateur != null) {
	                    request.setAttribute("profilUtilisateur", profilUtilisateur);
	            		//j'indique la page index qui est mon layout
	            		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
	            		//j'indique a mon layout qu'il s'agit de la page home
	            		request.setAttribute("page", "modifiermonprofil");
	            		//j'indique le titre de la page dans le head
	            		request.setAttribute("title", "Modifier mon profil");
	            		rd.forward(request, response);
	                } else {
	                    request.setAttribute("errorMessage", "Utilisateur introuvable");
	            		//j'indique la page index qui est mon layout
	            		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
	            		//j'indique a mon layout qu'il s'agit de la page home
	            		request.setAttribute("page", "erreur");
	            		//j'indique le titre de la page dans le head
	            		request.setAttribute("title", "Erreur");
	            		rd.forward(request, response);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                request.setAttribute("errorMessage", "Utilisateur introuvable");
	        		//j'indique la page index qui est mon layout
	        		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
	        		//j'indique a mon layout qu'il s'agit de la page home
	        		request.setAttribute("page", "erreur");
	        		//j'indique le titre de la page dans le head
	        		request.setAttribute("title", "Erreur");
	        		rd.forward(request, response);
	            }
	        } else {
	            response.sendRedirect(request.getContextPath() + "/redirection?page=login");
	        }
	    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	        Utilisateur profilUtilisateur = (Utilisateur) session.getAttribute("utilisateur");

	        // Récupération des données du formulaire
	        String pseudo = request.getParameter("pseudo");
	        String nom = request.getParameter("nom");
	        String prenom = request.getParameter("prenom");
	        String email = request.getParameter("email");
	        String telephone = request.getParameter("telephone");
	        String rue = request.getParameter("rue");
	        String codePostal = request.getParameter("codePostal");
	        String ville = request.getParameter("ville");
	        String newPassword = request.getParameter("newPassword");

	        // Mettre à jour les informations du compte de l'utilisateur
	        profilUtilisateur.setPseudo(pseudo);
	        profilUtilisateur.setNom(nom);
	        profilUtilisateur.setPrenom(prenom);
	        profilUtilisateur.setEmail(email);
	        profilUtilisateur.setTelephone(telephone);
	        profilUtilisateur.setRue(rue);
	        profilUtilisateur.setCodePostal(codePostal);
	        profilUtilisateur.setVille(ville);

	       /* // Vérifier si un nouveau mot de passe a été fourni
	        if (newPassword != null && !newPassword.isEmpty()) {
	            profilUtilisateur.setMotDePasse(newPassword);
	        }*/

	        // Appeler la méthode appropriée pour effectuer la mise à jour du compte
	        UtilisateurDao utilisateurDao = new UtilisateurDaoJdbcImpl();
	        try {
	            utilisateurDao.update(profilUtilisateur);
	            request.setAttribute("successMessage", "Les modifications ont été enregistrées avec succès");
	    		//j'indique la page index qui est mon layout
	    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
	    		//j'indique a mon layout qu'il s'agit de la page home
	    		request.setAttribute("page", "succes");
	    		//j'indique le titre de la page dans le head
	    		request.setAttribute("title", "Succes");
	    		rd.forward(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "Une erreur s'est produite lors de la mise à jour du compte");
	    		//j'indique la page index qui est mon layout
	    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
	    		//j'indique a mon layout qu'il s'agit de la page home
	    		request.setAttribute("page", "erreur");
	    		//j'indique le titre de la page dans le head
	    		request.setAttribute("title", "Erreur");
	    		rd.forward(request, response);
	        }
	    }
}
		 


