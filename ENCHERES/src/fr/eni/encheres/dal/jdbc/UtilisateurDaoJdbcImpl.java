package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.ErrorCodesDAL;
import fr.eni.encheres.dal.UtilisateurDao;

public class UtilisateurDaoJdbcImpl implements UtilisateurDao {
    private static final String INSERT_QUERY = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ? WHERE no_utilisateur = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM UTILISATEURS";
    private static final String SELECT_BY_PSEUDO_QUERY = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
    private static final String DELETE_BY_ID = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
    private static final String EXISTE_PSEUDO = "SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = ?";
    private static final String EXISTE_EMAIL = "SELECT COUNT(*) FROM UTILISATEURS WHERE email = ?";
    private static final String CHECK_IF_PSEUDO_AND_MAIL_ALREADY_EXIST = "SELECT * FROM UTILISATEURS " + "WHERE pseudo LIKE ? OR email LIKE ?;";
    private static final String SELECT_BY_PSEUDO_PASSWORD = "SELECT * FROM UTILISATEURS WHERE pseudo=? AND mot_de_passe =?";
    private static final String SELECT_BY_EMAIL_PASSWORD = "SELECT * FROM UTILISATEURS WHERE email=? AND mot_de_passe =?";
    private static final String SELECT_BY_RESET_ID = "SELECT * FROM UTILISATEURS WHERE reset_id = ?";
    
    
    @Override
    public void insert(Utilisateur utilisateur) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, utilisateur.getPseudo());
            statement.setString(2, utilisateur.getNom());
            statement.setString(3, utilisateur.getPrenom());
            statement.setString(4, utilisateur.getEmail());
            statement.setString(5, utilisateur.getTelephone());
            statement.setString(6, utilisateur.getRue());
            statement.setString(7, utilisateur.getCodePostal());
            statement.setString(8, utilisateur.getVille());
            statement.setString(9, utilisateur.getMotDePasse());
            statement.setInt(10, utilisateur.getCredit());
            statement.setBoolean(11, utilisateur.isAdministrateur());

            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void update(Utilisateur utilisateur) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, utilisateur.getPseudo());
            statement.setString(2, utilisateur.getNom());
            statement.setString(3, utilisateur.getPrenom());
            statement.setString(4, utilisateur.getEmail());
            statement.setString(5, utilisateur.getTelephone());
            statement.setString(6, utilisateur.getRue());
            statement.setString(7, utilisateur.getCodePostal());
            statement.setString(8, utilisateur.getVille());
            statement.setString(9, utilisateur.getMotDePasse());
            statement.setInt(10, utilisateur.getCredit());
            statement.setBoolean(11, utilisateur.isAdministrateur());
            statement.setInt(12, utilisateur.getNoUtilisateur());

            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Utilisateur selectById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                utilisateur = resultSetToUtilisateur(resultSet);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return utilisateur;
    }

    @Override
    public List<Utilisateur> selectAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Utilisateur> utilisateurs = new ArrayList<>();

        try {

            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(SELECT_ALL_QUERY);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Utilisateur utilisateur = resultSetToUtilisateur(resultSet);
                utilisateurs.add(utilisateur);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return utilisateurs;
    }

    @Override
    public Utilisateur selectByPseudo(String pseudo) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(SELECT_BY_PSEUDO_QUERY);
            statement.setString(1, pseudo);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                utilisateur = resultSetToUtilisateur(resultSet);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return utilisateur;
    }
    
	@Override
	public void deleteById(int id) throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	        connection = ConnectionProvider.getConnection();
	        statement = connection.prepareStatement(DELETE_BY_ID);
	        statement.setInt(1, id);
	        statement.executeUpdate();
	    } finally {
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	}

	
	
    private Utilisateur resultSetToUtilisateur(ResultSet resultSet) throws SQLException {
        int noUtilisateur = resultSet.getInt("no_utilisateur");
        String pseudo = resultSet.getString("pseudo");
        String nom = resultSet.getString("nom");
        String prenom = resultSet.getString("prenom");
        String email = resultSet.getString("email");
        String telephone = resultSet.getString("telephone");
        String rue = resultSet.getString("rue");
        String codePostal = resultSet.getString("code_postal");
        String ville = resultSet.getString("ville");
        String motDePasse = resultSet.getString("mot_de_passe");
        int credit = resultSet.getInt("credit");
        boolean administrateur = resultSet.getBoolean("administrateur");

        return new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
    }

    
    public boolean existePseudo(String pseudo) throws DALException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(EXISTE_PSEUDO);
            statement.setString(1, pseudo);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

            return false;
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la vérification de l'existence du pseudo", e);
        } finally {
            // Fermer les ressources JDBC (ResultSet, Statement, Connection) dans un bloc finally
            // ...
        }
    }

    public boolean existeEmail(String email) throws DALException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(EXISTE_EMAIL);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

            return false;
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la vérification de l'existence de l'email", e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    @Override
    public boolean checkForUniquePseudoAndMail(String pseudo, String mail) throws DALException, SQLException {
        Connection connection = null;

       
        connection = ConnectionProvider.getConnection();
        boolean isUnique = true;
        try {
            PreparedStatement stmt = connection.prepareStatement(CHECK_IF_PSEUDO_AND_MAIL_ALREADY_EXIST);
            stmt.setString(1, pseudo);
            stmt.setString(2, mail);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                isUnique = false;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException(pseudo, mail, e);
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return isUnique;
    }
    
    public Utilisateur selectByPseudoPassword(String pseudo, String password)throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(SELECT_BY_PSEUDO_PASSWORD);
            statement.setString(1, pseudo);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                utilisateur = resultSetToUtilisateur(resultSet);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return utilisateur;
    }

	@Override
	public Utilisateur selectByEmailPassword(String email, String password)throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(SELECT_BY_EMAIL_PASSWORD);
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                utilisateur = resultSetToUtilisateur(resultSet);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return utilisateur;
    }
	
	@Override
    public Utilisateur selectByResetId(String resetId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(SELECT_BY_RESET_ID);
            statement.setString(1, resetId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                utilisateur = resultSetToUtilisateur(resultSet);
            }
        } finally {
            // Close resources in the finally block
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return utilisateur;
    }


}



