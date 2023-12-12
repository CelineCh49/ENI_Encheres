package fr.eni.encheres.dal;

import fr.eni.encheres.dal.jdbc.ArticleDaoJdbcImpl;
import fr.eni.encheres.dal.jdbc.CategorieDaoJdbcImpl;
import fr.eni.encheres.dal.jdbc.EnchereDaoJdbcImpl;
import fr.eni.encheres.dal.jdbc.UtilisateurDaoJdbcImpl;

public class DaoFactory {
	
	public static UtilisateurDao getUtilisateurDao() {
		return new UtilisateurDaoJdbcImpl();
	}
	
	public static ArticleDao getArticleDao() {
		return new ArticleDaoJdbcImpl();
	}
	
	public static EnchereDao getEnchereDao() {
		return new EnchereDaoJdbcImpl();
	}
	
	public static CategorieDao getCategorieDao() {
		return new CategorieDaoJdbcImpl();
	}

}
