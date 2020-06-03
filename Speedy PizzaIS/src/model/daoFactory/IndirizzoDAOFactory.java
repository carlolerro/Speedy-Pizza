package model.daoFactory;

import model.daoImplementation.CategoriaDAOImpl;
import model.daoImplementation.IndirizzoDAOImpl;
import model.daoInterface.CategoriaDAO;
import model.daoInterface.IndirizzoDAO;

public class IndirizzoDAOFactory {
	public static IndirizzoDAO getIndirizzoDAO() { 
        return new IndirizzoDAOImpl();
    }
}
