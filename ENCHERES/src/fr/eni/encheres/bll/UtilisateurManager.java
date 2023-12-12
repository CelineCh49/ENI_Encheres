package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDao;
import fr.eni.encheres.dal.jdbc.UtilisateurDaoJdbcImpl;
import fr.eni.encheres.dal.DALException;

import java.sql.SQLException;
import java.util.regex.Pattern;


public class UtilisateurManager {
    private UtilisateurDao utilisateurDao;

    public UtilisateurManager() {
        utilisateurDao = new UtilisateurDaoJdbcImpl();
    
    }

    public void enregistrerUtilisateur(Utilisateur utilisateur) throws BLLException, SQLException {
        BLLException bllException = new BLLException();
        
     // Effectuez la validation de l'utilisateur
        BLLException validationErrors = validationUtilisateur(utilisateur);
        

     // Si des erreurs de validation ont été trouvées, les ajouter à bllException
        if (validationErrors.hasErrors()) {
            for(int errorCode : validationErrors.getListErrorCodes()) {
                bllException.addError(errorCode);
            }
        }
        try {
            // Vérifier si le pseudo ou l'email existe déjà dans la base de données
            if (utilisateurDao.existePseudo(utilisateur.getPseudo())) {
            	bllException.addError(ErrorCodesBLL.PSEUDO_EXISTANT);
            }
            if (utilisateurDao.existeEmail(utilisateur.getEmail())) {
                bllException.addError(ErrorCodesBLL.EMAIL_EXISTANT);
            }
            // S'il y a des erreurs jusqu'à présent, lancez une exception
            if (bllException.hasErrors()) {
                throw bllException;
            }

            // Enregistrer l'utilisateur
            utilisateurDao.insert(utilisateur);
        } catch (DALException e) {
            bllException.addError(ErrorCodesBLL.ERREUR_ENREGISTREMENT_UTILISATEUR);
            throw bllException;
        }
    }
    

    private BLLException validationUtilisateur(Utilisateur utilisateur) throws BLLException {
        String pseudoValidationRegEx = "[A-Za-z0-9]+";
        // This regexp is made from the RFC 5322 : http://www.ietf.org/rfc/rfc5322.txt
        // and has been taken from here : https://emailregex.com/
        String emailValidationRegEx = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
        // This regexp is made for matching only french telephone numbers.
        // format : 0101010101, no space and no special characters
        String telephoneNumberValidationRegEx = "^0[1-9][0-9]{8}$";
        BLLException bllException = new BLLException();

        if (utilisateur.getPseudo().length() > 30) {
            bllException.addError(ErrorCodesBLL.ERROR_LENGTH_PSEUDO_UTILISATEUR);
        }
        if (!Pattern.matches(pseudoValidationRegEx, utilisateur.getPseudo())) {
            bllException.addError(ErrorCodesBLL.ERROR_PSEUDO_NOT_ALPHANUMERIC);
        }
        if (utilisateur.getNom().length() > 30) {
            bllException.addError(ErrorCodesBLL.ERROR_LENGTH_NOM_UTILISATEUR);
        }
        if (utilisateur.getPrenom().length() > 30) {
            bllException.addError(ErrorCodesBLL.ERROR_LENGTH_PRENOM_UTILISATEUR);
        }
        if (utilisateur.getEmail().length() > 40) {
            bllException.addError(ErrorCodesBLL.ERROR_LENGTH_EMAIL_UTILISATEUR);
        }
        if (!Pattern.matches(emailValidationRegEx, utilisateur.getEmail())) {
            bllException.addError(ErrorCodesBLL.ERROR_FORMAT_EMAIL_UTILISATEUR);
        }
        if (utilisateur.getTelephone().length() > 15) {
            bllException.addError(ErrorCodesBLL.ERROR_LENGTH_TELEPHONE_UTILISATEUR);
        }
        if (!Pattern.matches(telephoneNumberValidationRegEx, utilisateur.getTelephone())) {
            bllException.addError(ErrorCodesBLL.ERROR_FORMAT_TELEPHONE_UTILISATEUR);
        }
        if (utilisateur.getRue().length() > 30) {
            bllException.addError(ErrorCodesBLL.ERROR_LENGTH_RUE_UTILISATEUR);
        }
        if (utilisateur.getCodePostal().length() > 30) {
            bllException.addError(ErrorCodesBLL.ERROR_LENGTH_CODE_POSTAL_UTILISATEUR);
        }
        if (utilisateur.getVille().length() > 30) {
            bllException.addError(ErrorCodesBLL.ERROR_LENGTH_VILLE_UTILISATEUR);
        }

        return bllException;
    }
    public Utilisateur selectByPseudoPassword(String identifiant,String motDePasse) {
    	Utilisateur utilisateur = null;
		
			try {
				utilisateur = utilisateurDao.selectByPseudoPassword(identifiant, motDePasse);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return utilisateur;
	}
    
    
    
    public Utilisateur selectByEmailPassword(String identifiant,String motDePasse) {
        Utilisateur utilisateur = null;

            try {
                utilisateur = utilisateurDao.selectByEmailPassword(identifiant, motDePasse);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        return utilisateur;
    }
    
    

}