package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDao {
    public abstract void insert( Utilisateur utilisateur ) throws SQLException;
    public abstract void update( Utilisateur utilisateur ) throws SQLException;
    public abstract void deleteById( int id ) throws SQLException;
    public abstract Utilisateur selectById( int id ) throws SQLException;
    public abstract List<Utilisateur> selectAll() throws SQLException;
    public abstract Utilisateur selectByPseudo(String pseudo) throws SQLException;
    public abstract boolean existePseudo(String pseudo) throws DALException;
    public abstract boolean existeEmail(String email) throws DALException, SQLException;
    public abstract boolean checkForUniquePseudoAndMail(String pseudo, String mail) throws DALException, SQLException;
    public abstract Utilisateur selectByPseudoPassword(String pseudo, String password) throws SQLException;
    public abstract Utilisateur selectByEmailPassword(String email, String password) throws SQLException;
    public abstract Utilisateur selectByResetId(String resetId) throws SQLException;
}