package fr.eni.encheres.servlets.utilisateur;

import java.io.IOException;
import java.sql.SQLException;

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
 * @yohan
 */
@WebServlet("/motdepasseoublie")
public class ServletMotDePasseOublie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private UtilisateurDao utilisateurDao;

    public void init() throws ServletException {
        super.init();
        utilisateurDao = new UtilisateurDaoJdbcImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier si l'identifiant de réinitialisation est présent dans l'URL
        String resetId = request.getParameter("resetId");
        if (resetId != null) {
            try {
                // Rechercher l'utilisateur correspondant à l'identifiant de réinitialisation
                Utilisateur utilisateur = utilisateurDao.selectByResetId(resetId);
                if (utilisateur != null) {
                    // Stocker l'identifiant de réinitialisation dans la session pour l'utiliser lors de la soumission du formulaire
                    HttpSession session = request.getSession();
                    session.setAttribute("resetId", resetId);
                    session.setAttribute("pseudo", utilisateur.getPseudo());

                    // Rediriger vers l'écran de saisie du nouveau mot de passe
                    response.sendRedirect("redirection?page=motdepasseoublie");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Rediriger vers une page d'erreur si l'identifiant de réinitialisation est invalide ou manquant
        response.sendRedirect("redirection?page=erreur");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer le nouveau mot de passe depuis le formulaire
        String nouveauMotDePasse = request.getParameter("nouveauMotDePasse");

        // Vérifier si l'identifiant de réinitialisation est présent dans la session
        HttpSession session = request.getSession();
        String resetId = (String) session.getAttribute("resetId");
        String pseudo = (String) session.getAttribute("pseudo");
        if (resetId != null && pseudo != null) {
            try {
                // Rechercher l'utilisateur correspondant à l'identifiant de réinitialisation
                Utilisateur utilisateur = utilisateurDao.selectByPseudo(pseudo);
                if (utilisateur != null) {
                    // Mettre à jour le mot de passe de l'utilisateur avec le nouveau mot de passe
                    utilisateur.setMotDePasse(nouveauMotDePasse);
                    utilisateurDao.update(utilisateur);

                    // Supprimer l'identifiant de réinitialisation de la session
                    session.removeAttribute("resetId");
                    session.removeAttribute("pseudo");

                    // Rediriger vers une page de succès
                    response.sendRedirect("redirection?page=succes");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Rediriger vers une page d'erreur si l'identifiant de réinitialisation est invalide ou manquant
        response.sendRedirect("redirection?page=erreur");
    }
}