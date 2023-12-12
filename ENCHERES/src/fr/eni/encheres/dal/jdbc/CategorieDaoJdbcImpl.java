package fr.eni.encheres.dal.jdbc;

import fr.eni.encheres.dal.CategorieDao;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.bo.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDaoJdbcImpl implements CategorieDao {
    private static final String INSERT_QUERY = "INSERT INTO CATEGORIES (libelle) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE CATEGORIES SET libelle = ? WHERE no_categorie = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM CATEGORIES";
    private static final String DELETE_BY_ID = "DELETE FROM CATEGORIES WHERE no_categorie = ?";

    @Override
    public void insert(Categorie categorie) throws DALException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, categorie.getLibelle());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Failed to insert Categorie", null, e);
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
    public void update(Categorie categorie) throws DALException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, categorie.getLibelle());
            statement.setInt(2, categorie.getNoCategorie());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Failed to update Categorie", null, e);
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
                    throw new DALException("Failed to close connection", e);
                }
            }
        }
    }

    @Override
    public void delete(int id) throws DALException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Failed to delete Categorie", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close connection", e);
                }
            }
        }
    }

    @Override
    public Categorie selectById(int id) throws DALException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Categorie categorie = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int noCategorie = resultSet.getInt("noCategorie");
                String libelle = resultSet.getString("libelle");
                categorie = new Categorie(noCategorie, libelle);
            }
        } catch (SQLException e) {
            throw new DALException("Failed to retrieve Categorie by ID", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close result set", e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close connection", e);
                }
            }
        }

        return categorie;
    }

    @Override
    public List<Categorie> selectAll() throws DALException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Categorie> categories = new ArrayList<>();

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.prepareStatement(SELECT_ALL_QUERY);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int noCategorie = resultSet.getInt("noCategorie");
                String libelle = resultSet.getString("libelle");
                Categorie categorie = new Categorie(noCategorie, libelle);
                categories.add(categorie);
            }
        } catch (SQLException e) {
            throw new DALException("Failed to retrieve all Categories", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close result set", e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DALException("Failed to close connection", e);
                }
            }
        }

        return categories;
    }
}



