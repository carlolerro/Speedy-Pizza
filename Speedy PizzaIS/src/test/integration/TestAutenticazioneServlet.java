package test.integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.GestoreUtenti.AutenticazioneServlet;
import model.beans.Utente;
import model.daoFactory.UtenteDAOFactory;

public class TestAutenticazioneServlet {
	Utente utente;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() {
		String email = "lerrocarlo98@gmail.cm";
		String password ="123456a";
		utente = UtenteDAOFactory.getUtenteDAO().autenticaUtente(email, password);
		assertNotNull(utente);
		assertEquals(utente.getTipo(), 0);
		
		email ="fiorentino.alfo@gmail.com"; password="123456a";
		utente = UtenteDAOFactory.getUtenteDAO().autenticaUtente(email, password);
		assertNotNull(utente);
		assertEquals(utente.getTipo(), 1);
		
		email ="fattorino@fattorino.com"; password="123456a";
		utente = UtenteDAOFactory.getUtenteDAO().autenticaUtente(email, password);
		assertNotNull(utente);
		assertEquals(utente.getTipo(), 2);
		
		email ="gestore@gestore.com"; password="123456a";
		utente = UtenteDAOFactory.getUtenteDAO().autenticaUtente(email, password);
		assertNotNull(utente);
		assertEquals(utente.getTipo(), 3);
		
		
	}

	

}
