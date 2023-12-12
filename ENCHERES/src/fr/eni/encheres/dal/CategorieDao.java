package fr.eni.encheres.dal;


import java.util.List;

import fr.eni.encheres.bo.Categorie;

public interface CategorieDao {

	void insert(Categorie categorie) throws DALException;

	void update(Categorie categorie) throws DALException;

	void delete(int id) throws DALException;

	Categorie selectById(int id) throws DALException;

	List<Categorie> selectAll() throws DALException;
	
	
}
