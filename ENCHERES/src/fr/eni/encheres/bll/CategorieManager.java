package fr.eni.encheres.bll;


import fr.eni.encheres.dal.UtilisateurDao;
import fr.eni.encheres.dal.jdbc.CategorieDaoJdbcImpl;
import fr.eni.encheres.dal.jdbc.UtilisateurDaoJdbcImpl;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDao;
import fr.eni.encheres.dal.DALException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class CategorieManager {
    private CategorieDao categorieDao;

    public CategorieManager() {
    	categorieDao = new CategorieDaoJdbcImpl();
    
    }
    
    public List<Categorie> selectAll() {
    	List<Categorie> categories = new ArrayList<>();
					
		try {
			categories = categorieDao.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
    }

}