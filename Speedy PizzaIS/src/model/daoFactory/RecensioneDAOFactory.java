package model.daoFactory;

import model.daoImplementation.CategoriaDAOImpl;
import model.daoImplementation.RecensioneDAOImpl;
import model.daoInterface.CategoriaDAO;
import model.daoInterface.RecensioneDAO;

public class RecensioneDAOFactory {
	public static RecensioneDAO getRecensioneDAO() { 
        return new RecensioneDAOImpl();
    }
}
