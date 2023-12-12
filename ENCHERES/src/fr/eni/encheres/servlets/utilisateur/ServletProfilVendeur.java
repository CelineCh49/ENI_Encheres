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

/*import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDao;*/
/**
 * @author yohan
 *
 */
/**
 * Servlet implementation class ServletProfil
 */
@WebServlet("/profilvendeur")
public class ServletProfilVendeur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	        
	        // Vérifier si l'utilisateur est connecté
	        if (session.getAttribute("utilisateur") != null) {
	            Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
	            String pseudo = utilisateur.getPseudo();
	            
	            UtilisateurDao utilisateurDao = new UtilisateurDaoJdbcImpl();
	            try {
	                Utilisateur profilUtilisateur = utilisateurDao.selectByPseudo(pseudo);
	                
	                if (profilUtilisateur != null) {
	                    request.setAttribute("profilUtilisateur", profilUtilisateur);

		        		//j'indique la page index qui est mon layout
		        		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		        		//j'indique a mon layout qu'il s'agit de la page home
		        		request.setAttribute("page", "profil");
		        		//j'indique le titre de la page dans le head
		        		request.setAttribute("title", "Profil");
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

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Aucune action requise pour la méthode POST
	    }
	}
