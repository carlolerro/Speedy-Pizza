package model.daoFactory;

import model.daoImplementation.CartaDAOImpl;
import model.daoInterface.CartaDAO;

public class CartaDAOFactory {
	public static CartaDAO getCartaDAO() { 
        return new CartaDAOImpl();
    }
}	
