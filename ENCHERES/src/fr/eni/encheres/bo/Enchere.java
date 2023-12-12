package fr.eni.encheres.bo;

import java.time.LocalDateTime;

public class Enchere {
	private Utilisateur utilisateur;
	private Article article;
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	
	
	/**
	 * @param noUtilisateurAcheteur
	 * @param noArticle
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Enchere(Utilisateur utilisateur, Article article, LocalDateTime dateEnchere, int montantEnchere) {
		super();
		this.utilisateur = utilisateur;
		this.article = article;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	/**
	 * @param noArticle
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Enchere(Article article, LocalDateTime dateEnchere, int montantEnchere) {
		super();
		this.article = article;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	/**
	 * 
	 */
	public Enchere() {
		super();
	}
	
	
	
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	
	
	
}
