package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public interface EnchereDao {

	public abstract void insert( Enchere enchere );
	public abstract void update( Enchere enchere );
	public abstract List<Enchere> selectByNoArticle( int noArticle );
	public abstract List <Enchere> selectByUtilisateur( Utilisateur utilisateur );
	
	
}
