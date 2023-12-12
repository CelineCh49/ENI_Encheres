package fr.eni.encheres.bll;

 abstract class ErrorCodesBLL {
     static final int ERROR_LENGTH_PSEUDO_UTILISATEUR = 30000;
     static final int ERROR_LENGTH_NOM_UTILISATEUR = 30001;
     static final int ERROR_LENGTH_PRENOM_UTILISATEUR = 30002;
     static final int ERROR_LENGTH_EMAIL_UTILISATEUR = 30003;
     static final int ERROR_LENGTH_TELEPHONE_UTILISATEUR = 30004;
     static final int ERROR_LENGTH_RUE_UTILISATEUR = 30005;
     static final int ERROR_LENGTH_CODE_POSTAL_UTILISATEUR = 30006;
     static final int ERROR_LENGTH_VILLE_UTILISATEUR = 30007;
     static final int ERROR_FORMAT_EMAIL_UTILISATEUR = 30008;
     static final int ERROR_FORMAT_TELEPHONE_UTILISATEUR = 30009;
     static final int ERROR_PSEUDO_OR_MAIL_ALREADY_TAKEN = 30010;
     static final int ERROR_PSEUDO_NOT_ALPHANUMERIC = 30011;
     
     static final int ERROR_NO_RESULTS = 30050;
     
     static final int PSEUDO_EXISTANT = 30060;
     static final int EMAIL_EXISTANT = 30061;
     static final int ERREUR_ENREGISTREMENT_UTILISATEUR = 30062;
}
