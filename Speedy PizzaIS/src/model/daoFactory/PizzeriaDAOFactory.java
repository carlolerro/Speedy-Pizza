package model.daoFactory;

import model.daoImplementation.CategoriaDAOImpl;
import model.daoImplementation.PizzeriaDAOImpl;
import model.daoInterface.CategoriaDAO;
import model.daoInterface.PizzeriaDAO;

public class PizzeriaDAOFactory {
	public static PizzeriaDAO getPizzeriaDAO() { 
        return new PizzeriaDAOImpl();
    }
}
