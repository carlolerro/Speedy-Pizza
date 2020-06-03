package model.daoFactory;

import model.daoImplementation.CategoriaDAOImpl;
import model.daoImplementation.OrdineDAOImpl;
import model.daoInterface.CategoriaDAO;
import model.daoInterface.OrdineDAO;

public class OrdineDAOFactory {
	public static OrdineDAO getOrdineDAO() { 
        return new OrdineDAOImpl();
    }
}
