package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Article;

/**
 * @author yohan
 *
 */
public interface ArticleDao {
	public abstract void insert (Article article);
	public abstract void update (Article article);
	public abstract void deletById (int id);
	public abstract Article selectById (int id);
	public abstract List<Article> selectAll();

}
