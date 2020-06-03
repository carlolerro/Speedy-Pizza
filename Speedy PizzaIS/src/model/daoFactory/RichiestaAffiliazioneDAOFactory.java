package model.daoFactory;

import model.daoImplementation.CategoriaDAOImpl;
import model.daoImplementation.RichiestaAffiliazioneDAOImpl;
import model.daoInterface.CategoriaDAO;
import model.daoInterface.RichiestaAffiliazioneDAO;

public class RichiestaAffiliazioneDAOFactory {
	public static RichiestaAffiliazioneDAO getRichiestaAffiliazioneDAO() { 
        return new RichiestaAffiliazioneDAOImpl();
    }
}
