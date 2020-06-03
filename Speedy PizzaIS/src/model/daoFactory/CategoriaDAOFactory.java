package model.daoFactory;

import model.daoImplementation.CartaDAOImpl;
import model.daoImplementation.CategoriaDAOImpl;
import model.daoInterface.CartaDAO;
import model.daoInterface.CategoriaDAO;

public class CategoriaDAOFactory {

	
		public static CategoriaDAO getCategoriaDAO() { 
	        return new CategoriaDAOImpl();
	    }
		
}

