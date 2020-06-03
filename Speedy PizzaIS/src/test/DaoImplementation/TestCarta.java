package test.DaoImplementation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.beans.Carta;
import model.daoImplementation.CartaDAOImpl;

public class TestCarta {
	private CartaDAOImpl managerCarta;
	@Before
	public void setUp() throws Exception {
		managerCarta = new CartaDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testInserisciCarta() {
		Carta carta = managerCarta.inserisciCarta(new Carta("60000aCF20", "Al Bano", "672", "06/22", "lerrocarlo98@gmail.com"),"lerrocarlo98@gmail.com" );
		assertNotNull(carta);
		
		carta = managerCarta.inserisciCarta(new Carta("2221000000000000", "Al Bano", "672", "CA/22", "lerrocarlo98@gmail.com"),"lerrocarlo98@gmail.com" );
		assertNotNull(carta);
		
		carta = managerCarta.inserisciCarta(new Carta("2221000000000001", "Al Bano", "672", "22/05", "lerrocarlo98@gmail.com"),"lerrocarlo98@gmail.com" );
		assertNotNull(carta);
		
		carta = managerCarta.inserisciCarta(new Carta("2221000000000002", "Alf", "672", "06/22", "lerrocarlo98@gmail.com"),"lerrocarlo98@gmail.com" );
		assertNotNull(carta);
		
		carta = managerCarta.inserisciCarta(new Carta("2221000000000003", "AlfonsoAlfonsoAlfonsoAlfonsoAlfonso", "672", "06/22", "lerrocarlo98@gmail.com"),"lerrocarlo98@gmail.com" );
		assertNotNull(carta);
		
		carta = managerCarta.inserisciCarta(new Carta("2221000000000004", "22Alfonso2", "672", "06/22", "lerrocarlo98@gmail.com"),"lerrocarlo98@gmail.com" );
		assertNotNull(carta);
		
		carta = managerCarta.inserisciCarta(new Carta("2221000000000005", "Alfonso Fiorentino", "67", "06/22", "lerrocarlo98@gmail.com"),"lerrocarlo98@gmail.com" );
		assertNotNull(carta);
		
		carta = managerCarta.inserisciCarta(new Carta("2221000000000006", "Alfonso Fiorentino", "AA2", "06/22", "lerrocarlo98@gmail.com"),"lerrocarlo98@gmail.com" );
		assertNotNull(carta);
		
		carta = managerCarta.inserisciCarta(new Carta("2221000000000007", "Alfonso Fiorentino", "AA2", "06/22", "lerrocarlo98@gmail.com"),"lerrocarlo98@gmail.com" );
		assertNotNull(carta);
	}
	
	@Test
	public void testRimuoviCarta() {
		Boolean value = managerCarta.rimuoviCarta(new Carta("60000aCF20", "Al Bano", "672", "06/22", "lerrocarlo98@gmail.com"));
		assertFalse(value);
		
		value = managerCarta.rimuoviCarta(new Carta("2221000000000000", "Al Bano", "672", "CA/22", "lerrocarlo98@gmail.com"));
		assertFalse(value);
		
		value = managerCarta.rimuoviCarta(new Carta("2221000000000001", "Al Bano", "672", "22/05", "lerrocarlo98@gmail.com"));
		assertFalse(value);
		
		value = managerCarta.rimuoviCarta(new Carta("2221000000000002", "Alf", "672", "06/22", "lerrocarlo98@gmail.com"));
		assertFalse(value);
		
		value = managerCarta.rimuoviCarta(new Carta("2221000000000003", "AlfonsoAlfonsoAlfonsoAlfonsoAlfonso", "672", "06/22", "lerrocarlo98@gmail.com"));
		assertFalse(value);
		
		value = managerCarta.rimuoviCarta(new Carta("2221000000000004", "22Alfonso2", "672", "06/22", "lerrocarlo98@gmail.com"));
		assertFalse(value);
		
		value = managerCarta.rimuoviCarta(new Carta("2221000000000005", "Alfonso Fiorentino", "67", "06/22", "lerrocarlo98@gmail.com"));
		assertFalse(value);
		
		value = managerCarta.rimuoviCarta(new Carta("2221000000000006", "Alfonso Fiorentino", "AA2", "06/22", "lerrocarlo98@gmail.com"));
		assertFalse(value);
		
		value = managerCarta.rimuoviCarta(new Carta("2221000000000007", "Alfonso Fiorentino", "AA2", "06/22", "lerrocarlo98@gmail.com"));
		assertFalse(value);
	}

	

}
