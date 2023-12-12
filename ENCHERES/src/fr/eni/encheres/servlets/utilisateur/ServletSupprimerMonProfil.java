package fr.eni.encheres.servlets.utilisateur;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class ServletSurpimerCompte
 */
@WebServlet("/supprimermonprofil")
public class ServletSupprimerMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	 private UtilisateurDao utilisateurDao;

	    public void init() throws ServletException {
	        super.init();
	        utilisateurDao = new UtilisateurDaoJdbcImpl();
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getParameter("action");
	        if (action != null && action.equals("supprimermoncompte")) {
	            HttpSession session = request.getSession();
	            Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
	            if (utilisateur != null) {
	                try {
	                    utilisateurDao.deleteById(utilisateur.getNoUtilisateur());
	                    // Suppression réussie, vous pouvez rediriger l'utilisateur vers une page appropriée
	                    request.getRequestDispatcher("/WEB-INF/views/successuppresion.jsp").forward(request, response);
		        		//j'indique la page index qui est mon layout
		        		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		        		//j'indique a mon layout qu'il s'agit de la page home
		        		request.setAttribute("page", "successuppression");
		        		//j'indique le titre de la page dans le head
		        		request.setAttribute("title", "Succes Suppression");
		        		rd.forward(request, response);
	                    return;
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    // Gérer les erreurs de suppression du profil, vous pouvez rediriger l'utilisateur vers une page d'erreur
	                    response.sendRedirect(request.getContextPath() + "/redirection?page=login");
	                    return;
	                }
	            } else {
	                
	               
	// Utilisateur introuvable, vous pouvez rediriger l'utilisateur vers une page appropriée
	                response.sendRedirect(request.getContextPath() + "/redirection?page=login");
	                return;
	            }
	        } else {
	            // Action non valide, vous pouvez rediriger l'utilisateur vers une page appropriée
	            response.sendRedirect(request.getContextPath() + "/redirection?page=login");
	        }
	    }
	}
