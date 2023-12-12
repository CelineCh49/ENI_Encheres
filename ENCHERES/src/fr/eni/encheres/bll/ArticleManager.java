package fr.eni.encheres.bll;

import java.sql.SQLException;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleDao;
import fr.eni.encheres.dal.jdbc.ArticleDaoJdbcImpl;

public class ArticleManager {
	private ArticleDao articleDao;
	
	public ArticleManager () {
		articleDao = new ArticleDaoJdbcImpl();
	}
	
}
