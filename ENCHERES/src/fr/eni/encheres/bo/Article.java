package fr.eni.encheres.bo;

import java.time.LocalDateTime;

public class Article {
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private EtatVente etatVente;
    private int noUtilisateurVendeur;
    private int noUtilisateurAcheteur;
    private int noCategorie;

    
    
    
	/**
	 * Constructeur vide
	 */
	public Article() {
		super();
	}


	/**
	 * Constructeur avec noArticle
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param prixInitial
	 * @param prixVente
	 * @param etatVente
	 * @param noUtilisateurVendeur
	 * @param noUtilisateurAcheteur
	 * @param noCategorie
	 */
	public Article(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int prixInitial, int prixVente, EtatVente etatVente, int noUtilisateurVendeur,
			int noUtilisateurAcheteur, int noCategorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.noUtilisateurVendeur = noUtilisateurVendeur;
		this.noUtilisateurAcheteur = noUtilisateurAcheteur;
		this.noCategorie = noCategorie;

	}


	/**
	 * Constructeur sans noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param prixInitial
	 * @param prixVente
	 * @param etatVente
	 * @param noUtilisateurVendeur
	 * @param noUtilisateurAcheteur
	 * @param noCategorie
	 */
	public Article(String nomArticle, String description, LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres,
			int prixInitial, int prixVente, EtatVente etatVente, int noUtilisateurVendeur, int noUtilisateurAcheteur,
			int noCategorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.noUtilisateurVendeur = noUtilisateurVendeur;
		this.noUtilisateurAcheteur = noUtilisateurAcheteur;
		this.noCategorie = noCategorie;
	}


	public int getNoArticle() {
		return noArticle;
	}


	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}


	public String getNomArticle() {
		return nomArticle;
	}


	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}


	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}


	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}


	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}


	public int getPrixInitial() {
		return prixInitial;
	}


	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}


	public int getPrixVente() {
		return prixVente;
	}


	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}


	public EtatVente getEtatVente() {
		return etatVente;
	}


	public void setEtatVente(EtatVente etatVente) {
		this.etatVente = etatVente;
	}


	public int getNoUtilisateurVendeur() {
		return noUtilisateurVendeur;
	}


	public void setNoUtilisateurVendeur(int noUtilisateurVendeur) {
		this.noUtilisateurVendeur = noUtilisateurVendeur;
	}


	public int getNoUtilisateurAcheteur() {
		return noUtilisateurAcheteur;
	}


	public void setNoUtilisateurAcheteur(int noUtilisateurAcheteur) {
		this.noUtilisateurAcheteur = noUtilisateurAcheteur;
	}


	public int getNoCategorie() {
		return noCategorie;
	}


	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}



	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", prixInitial="
				+ prixInitial + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", noUtilisateurVendeur="
				+ noUtilisateurVendeur + ", noUtilisateurAcheteur=" + noUtilisateurAcheteur + ", noCategorie="
				+ noCategorie + "]";
	}
	
	
	
	
    

   
	
	
	

}
