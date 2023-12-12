package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Retrait;

public interface RetraitDao {
    void insert(Retrait retrait) throws DALException;
    void update(Retrait retrait) throws DALException;
    void delete(int noArticle) throws DALException;
    Retrait selectByArticle(int noArticle) throws DALException;
}
