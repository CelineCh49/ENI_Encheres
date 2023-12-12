package fr.eni.encheres.bo;


public enum EtatVente {
    EN_ATTENTE("Enchère créée"),
    EN_COURS("Enchère en cours"),
    TERMINEE("Enchère terminée"),
    ANNULER("Enchère annulée"),
	RETRAIT("Retrait éffectué");

	
	//permet d'éviter de faire un switch case pour afficher selon la variable
	//on créé une variable
	//on fait un constructeur
	//on créé un getter

	
	//on créé une variable
    private String libelle;

    //on fait un constructeur
    private EtatVente(String libelle) {
        this.libelle = libelle;
    }

    
    //on créé un getter
    public String getLibelle() {
        return libelle;
    }
}

