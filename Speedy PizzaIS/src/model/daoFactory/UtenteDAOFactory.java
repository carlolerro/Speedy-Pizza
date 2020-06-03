package model.daoFactory;

import model.daoImplementation.CategoriaDAOImpl;
import model.daoImplementation.UtenteDAOImpl;
import model.daoInterface.CategoriaDAO;
import model.daoInterface.UtenteDAO;

public class UtenteDAOFactory {
	public static UtenteDAO getUtenteDAO() { 
        return new UtenteDAOImpl();
    }
}
