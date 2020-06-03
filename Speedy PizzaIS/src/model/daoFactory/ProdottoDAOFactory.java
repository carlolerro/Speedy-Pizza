package model.daoFactory;

import model.daoImplementation.CategoriaDAOImpl;
import model.daoImplementation.ProdottoDAOImpl;
import model.daoInterface.CategoriaDAO;
import model.daoInterface.ProdottoDAO;

public class ProdottoDAOFactory {
	public static ProdottoDAO getProdottoDAO() { 
        return new ProdottoDAOImpl();
    }
}	
