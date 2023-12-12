package fr.eni.encheres.dal.jdbc;

/**
 * @author yohan
 *
 */
import java.sql.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.dal.ArticleDao;

public class ArticleDaoJdbcImpl implements ArticleDao {

	@Override
	public void insert(Article article) {
		String sqlInsert = "INSERT INTO ARTICLES (nom_article , description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_utilisateur_vendeur, no_utilisateur_acheteur, no_categorie)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(
				
			// 1.Connection à la base de données.
		Connection connection = ConnectionProvider.getConnection();
			// 2.Préparation à la requête sql.
		PreparedStatement statement = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);)
		{
			
		//3.Passage des paramètre si besoin.
		
		statement.setString(1, article.getNomArticle());
		statement.setString(2, article.getDescription());
		LocalDateTime dateDebut = article.getDateDebutEncheres();
		java.sql.Timestamp sqlDateDebut = java.sql.Timestamp.valueOf(dateDebut);
		statement.setTimestamp(3, sqlDateDebut);
		LocalDateTime dateFin = article.getDateFinEncheres();
		java.sql.Timestamp sqlDateFin = java.sql.Timestamp.valueOf(dateFin);
		statement.setTimestamp(4, sqlDateFin);
		statement.setInt(5, article.getPrixInitial());
		statement.setInt(6, article.getPrixVente());
		EtatVente etatVente = article.getEtatVente();
		// Utilisez etatVente.name() pour obtenir la valeur de l'énumération sous forme de chaîne
		statement.setString(7, etatVente.name());
		statement.setInt(8, article.getNoUtilisateurVendeur());
		statement.setInt(9, article.getNoUtilisateurAcheteur());
		statement.setInt(10,  article.getNoCategorie());
		/*Retrait lieuRetrait = article.getLieuRetrait();
		statement.setString(index++, lieuRetrait.getRue());
		statement.setString(index++, lieuRetrait.getCodePostal());
		statement.setString(index++, lieuRetrait.getVille());
		*/
		
		 // 4. Exécution de la requête
        statement.execute();
        
        int affectedRows = statement.executeUpdate();
		  // 5. Vérification des lignes affectées.
      if (affectedRows > 0) {
          ResultSet keys = statement.getGeneratedKeys();
          if (keys.next()) {
              int id = keys.getInt(1);
              article.setNoArticle(id);
          }
        }
      }catch(SQLException e) {
		// normalement pas besoin de tracer ici si on leve une exception
		e.printStackTrace();
		// TODO lever une exception plus précise si temps pour faire
		throw new RuntimeException(e);
	}
		
		
	}

	@Override
	public void update(Article article) {
		String sqlUpdate = "UPDATE  ARTICLES SET nom_article =? , description =?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, etat_vente=?, no_utilisateur_vendeur=?, no_utilisateur_acheteur=?, no_categorie=? WHERE no_article=? ";
		try(
				
			// 1.Connection à la base de données.
		Connection connection = ConnectionProvider.getConnection();
			// 2.Préparation à la requête sql.
		PreparedStatement statement = connection.prepareStatement(sqlUpdate);){
		
		//3.Passage des paramètre si besoin.
		
		statement.setString(1, article.getNomArticle());
		statement.setString(2, article.getDescription());
		LocalDateTime dateDebut = article.getDateDebutEncheres();
		java.sql.Timestamp sqlDateDebut = java.sql.Timestamp.valueOf(dateDebut);
		statement.setTimestamp(3, sqlDateDebut);
		LocalDateTime dateFin = article.getDateFinEncheres();
		java.sql.Timestamp sqlDateFin = java.sql.Timestamp.valueOf(dateFin);
		statement.setTimestamp(4, sqlDateFin);
        statement.setInt(5, article.getPrixInitial());
		statement.setInt(6, article.getPrixVente());
		EtatVente etatVente = article.getEtatVente();
		// Utilisez etatVente.name() pour obtenir la valeur de l'énumération sous forme de chaîne
		statement.setString(7, etatVente.name());
		statement.setInt(8, article.getNoUtilisateurVendeur());
		statement.setInt(9, article.getNoUtilisateurAcheteur());
		statement.setInt (10, article.getNoCategorie());
		/*Retrait lieuRetrait = article.getLieuRetrait();
		statement.setString(index++, lieuRetrait.getRue());
		statement.setString(index++, lieuRetrait.getCodePostal());
		statement.setString(index++, lieuRetrait.getVille());*/
		
		
		// 4. Execution de la requête sql.
		statement.execute();
		
		int affectedRows = statement.executeUpdate();
		  // 5. Vérification des lignes affectées.
        if (affectedRows > 0) {
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                article.setNoArticle(id);
            }
        }
	} catch ( SQLException ex ) {
		// normalement pas besoin de tracer ici si on leve une exception
		ex.printStackTrace();
		// TODO lever une exception plus précise si temps pour faire
		throw new RuntimeException(ex);
	}		
}

	@Override
	public void deletById(int id) {
			String sqlDeleteById = "DELETE FROM ARTICLES WHERE no_article = ? "; 
			try (
				// 1. Connexion à la base de données.
				Connection connection = ConnectionProvider.getConnection();
				// 2. Préparation de la requête sql.
				PreparedStatement statement = connection.prepareStatement(sqlDeleteById); ) {
				// 3. Passage de paramètres si besoin.
				statement.setInt( 1, id );
				// 4. Execution de la requête sql.
				statement.execute();
			} catch ( SQLException ex ) {
				// normalement pas besoin de tracer ici si on leve une exception
				ex.printStackTrace();
				// TODO lever une exception plus précise si temps pour faire
				throw new RuntimeException(ex);
			}
		}
	
	@Override
	public Article selectById(int id) {
		String sqlSelectById = "SELECT * FROM ARTICLES WHERE no_article = ?"; 
		try (
			// 1. Connexion à la base de données.
			Connection connection = ConnectionProvider.getConnection();
			// 2. Préparation de la requête sql.
			PreparedStatement statement = connection.prepareStatement(sqlSelectById); ) {
			// 3. Passage de paramètres si besoin.
			statement.setInt( 1, id );
			// 4. Execution de la requête sql.
			ResultSet result = statement.executeQuery();
			// 5. Exploitation des résultats.
			if (result.next()) {
				Article article = new Article(
						
						result.getInt("no_article"),
						result.getString("nom_article"),
						result.getString("description"),
						result.getTimestamp("date_debut_encheres").toLocalDateTime(),
		                result.getTimestamp("date_fin_encheres").toLocalDateTime(),
						result.getInt("prix_initial"),
						result.getInt("prix_vente"),
						EtatVente.valueOf(result.getString("etat_vente")),
						result.getInt("no_utilisateur_vendeur"),
						result.getInt("no_utilisateur_acheteur"),
						result.getInt("no_categorie")
						/*new Retrait(result.getString("rue"),
						 *result.getString("codePostal"),
						 *result.getString("ville"))
						*/
						);

				
						
				return article;
			} else {
				// ici faire un choix entre return null ou throw (non trouvé)
				return null;
				// si vous decider de faire return null l'appelant fera un if
				// si vous decider de faire un throw l'appelant fera un catch
			}
		} catch ( SQLException e ) {
			// normalement pas besoin de tracer ici si on leve une exception
			e.printStackTrace();
			// TODO lever une exception plus précise si temps pour faire
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Article> selectAll() {
		String sqlSelectAll = "SELECT * FROM ARTICLES"; 
		try (
				// 1. Connexion à la base de données.
				Connection connection = ConnectionProvider.getConnection();
				// 2. Préparation de la requête sql.
				PreparedStatement statement = connection.prepareStatement(sqlSelectAll); ) {
				// 3. Execution de la requête sql.
				ResultSet result = statement.executeQuery();
				// 4. Exploitation des résultats.
				List<Article> articles = new ArrayList<Article>();
				while (result.next())
				{
					Article article = new Article(
							result.getInt("no_article"),
							result.getString("nom_article"),
							result.getString("description"),
							result.getTimestamp("date_debut_encheres").toLocalDateTime(),
			                result.getTimestamp("date_fin_encheres").toLocalDateTime(),
							result.getInt("prix_initial"),
							result.getInt("prix_vente"),
							EtatVente.valueOf(result.getString("etat_vente")),
							result.getInt("no_utilisateur_vendeur"),
							result.getInt("no_utilisateur_acheteur"),
							result.getInt("no_categorie")
							/*new Retrait(result.getString("rue"),
									result.getString("codePostal"),
									result.getString("ville"))*/
							);
					articles.add(article);
	}
			return articles;	
	}catch ( SQLException e ) {
		// normalement pas besoin de tracer ici si on leve une exception
		e.printStackTrace();
		// TODO lever une exception plus précise si temps pour faire
		throw new RuntimeException(e);
	}

	}
}
