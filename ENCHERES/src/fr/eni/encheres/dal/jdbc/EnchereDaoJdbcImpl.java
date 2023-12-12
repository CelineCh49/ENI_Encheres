package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleDao;
import fr.eni.encheres.dal.EnchereDao;
import fr.eni.encheres.dal.UtilisateurDao;

public class EnchereDaoJdbcImpl implements EnchereDao {

	private final ArticleDao articleDao = null;
	private final UtilisateurDao utilisateurDao = null;
	
	@Override
	public void insert(Enchere enchere) {
		String sqlInsert = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere  ) VALUES (?,?,?,?) "; 
		try (
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement( sqlInsert ); ) {
			statement.setInt(1,enchere.getUtilisateur().getNoUtilisateur());
			statement.setInt(2,enchere.getArticle().getNoArticle());
			java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(enchere.getDateEnchere());
			statement.setTimestamp(3, sqlTimestamp);
			statement.setInt(4, enchere.getMontantEnchere());
			int nbRows = statement.executeUpdate();
			if (nbRows == 1) {
				System.out.println("Insertion réalisée avec succès");
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	

	@Override
	public void update(Enchere enchere) {
		String sqlUpdate = "UPDATE ENCHERES SET  date_enchere = ?, montant_enchere = ? WHERE no_article = ? and no_utilisateur = ? "; 
		try (
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlUpdate); ) {
			java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(enchere.getDateEnchere());
			statement.setTimestamp(1, sqlTimestamp);
			statement.setInt(2, enchere.getMontantEnchere());
			statement.setInt(3, enchere.getArticle().getNoArticle());
			statement.setInt(4, enchere.getUtilisateur().getNoUtilisateur());
			int nbRows = statement.executeUpdate();
			if (nbRows == 1) {
				System.out.println("Modification réalisée avec succès");
			}
		} catch ( SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	

	@Override
	public List<Enchere> selectByNoArticle( int noArticle ) {  
		String sqlSelectByNoArticle = "SELECT * FROM ENCHERES WHERE no_article = ? "; 
		try (
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlSelectByNoArticle); ) {
				statement.setInt( 1, noArticle );
				ResultSet result = statement.executeQuery();
				List<Enchere> encheres = new ArrayList<>();
				while (result.next()) {
					java.sql.Timestamp sqlDateEnchere = result.getTimestamp("e.date_enchere");
					LocalDateTime dateEnchere = sqlDateEnchere != null ? sqlDateEnchere.toLocalDateTime() : null;
					Article a = articleDao.selectById(noArticle);
					Utilisateur utilisateur = utilisateurDao.selectById(result.getInt("no_utilisateur"));
					Enchere enchere = new Enchere(
							utilisateur,
							a,
							dateEnchere,
							result.getInt("montant_enchere")
							);				
				encheres.add(enchere);
				}
					return encheres;				
				}
			 catch ( SQLException e ) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
	}		
				

	@Override
	public List <Enchere> selectByUtilisateur( Utilisateur utilisateur ) {
		String sqlSelectByUtilisateur = "SELECT a.*, e.*  FROM ARTICLES a inner join ENCHERES e  ON a.no_article  = e.no_article WHERE e.no_utilisateur = ?  "; 
		try (
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlSelectByUtilisateur); ) {
			statement.setInt( 1, utilisateur.getNoUtilisateur() );
			ResultSet result = statement.executeQuery();
			List<Enchere> encheres = new ArrayList<>();
			while (result.next()) {
				java.sql.Timestamp sqlDateEnchere = result.getTimestamp("e.date_enchere");
				LocalDateTime dateEnchere = sqlDateEnchere != null ? sqlDateEnchere.toLocalDateTime() : null;
				Article a = articleDao.selectById(result.getInt("a.no_article"));	
				Enchere enchere = new Enchere(
					utilisateur,
					a,
					dateEnchere,
					result.getInt("montant_enchere")
					);
				encheres.add(enchere);
			}
			return encheres;
				
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}





}

