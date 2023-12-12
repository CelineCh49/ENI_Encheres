package fr.eni.encheres.servlets.utilisateur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletRedirection
 */
@WebServlet("/redirection")
public class ServletRedirection extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String nextPage;

        switch(page) {

            case "accueil":
                nextPage = "/WEB-INF/view/pages/accueil.jspf";
                break;
            case "enregistrer":
                nextPage="/WEB-INF/view/pages/enregistrer.jspf";
                break;
            case "login":
                nextPage="/WEB-INF/view/pages/login.jspf";
                break;
            case "accueilconnecte":
                nextPage="/WEB-INF/view/pages/accueilconnecte.jspf";
                break;
            case "monprofil":
                nextPage="/WEB-INF/view/pages/monprofil.jspf";
                break;
            case "profil":
                nextPage="/WEB-INF/view/pages/profil.jspf";
                break;
            case "modifiermonprofil":
                nextPage="/WEB-INF/view/pages/modifiermonprofil.jspf";
                break;
            case "articleenvente":
                nextPage="/WEB-INF/view/pages/articleenvente.jspf";
                break;
            case "vendreunarticle":
                nextPage="/WEB-INF/view/pages/vendreunarticle.jspf";
                break;
            case "erreur":
                nextPage="/WEB-INF/view/pages/erreur.jspf";
                break;
            case "succes":
                nextPage="/WEB-INF/view/pages/succes.jspf";
                break;
            case "successuppression":
                nextPage="/WEB-INF/view/pages/successuppression.jspf";
                break;
            default:
                nextPage ="/WEB-INF/view/pages/accueil.jspf";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }
	
	//
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}
	
	
}