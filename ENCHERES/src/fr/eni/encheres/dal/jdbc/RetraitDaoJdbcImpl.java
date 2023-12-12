package fr.eni.encheres.dal.jdbc;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.RetraitDao;
import fr.eni.encheres.dal.DALException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetraitDaoJdbcImpl implements RetraitDao {
    private static final String INSERT_QUERY = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article = ?";
    private static final String SELECT_BY_ARTICLE_QUERY = "SELECT * FROM RETRAITS WHERE no_article = ?";
    private static final String DELETE_BY_ARTICLE = "DELETE FROM RETRAITS WHERE no_article = ?";

    @Override
    public void insert(Retrait retrait) throws DALException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(INSERT_QUERY);
            statement.setInt(1, retrait.getNoArticle());
            statement.setString(2, retrait.getRue());
            statement.setString(3, retrait.getCodePostal());
            statement.setString(4, retrait.getVille());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Failed to insert Retrait", null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close statement", null, e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close connection", null, e);
                }
            }
        }
    }

    @Override
    public void update(Retrait retrait) throws DALException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, retrait.getRue());
            statement.setString(2, retrait.getCodePostal());
            statement.setString(3, retrait.getVille());
            statement.setInt(4, retrait.getNoArticle());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Failed to update Retrait", null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close statement", null, e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close connection", null, e);
                }
            }
        }
    }

    @Override
    public void delete(int no_article) throws DALException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(DELETE_BY_ARTICLE);
            statement.setInt(1, no_article);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Failed to delete Retrait", null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close statement", null, e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close connection", null, e);
                }
            }
        }
    }

    @Override
    public Retrait selectByArticle(int no_article) throws DALException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Retrait retrait = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(SELECT_BY_ARTICLE_QUERY);
            statement.setInt(1, no_article);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String rue = resultSet.getString("rue");
                String codePostal = resultSet.getString("code_postal");
                String ville = resultSet.getString("ville");
                retrait = new Retrait(no_article, rue, codePostal, ville);
            }
        } catch (SQLException e) {
            throw new DALException("Failed to retrieve Retrait by Article", null, e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close result set", null, e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close statement", null, e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close connection", null, e);
                }
            }
        }

        return retrait;
    }
}


