package test.DaoImplementation;

import static org.junit.Assert.*;

import java.io.Console;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.beans.Indirizzo;
import model.daoImplementation.IndirizzoDAOImpl;

public class TestIndirizzo {
	Collection<String> collection;
	Collection<Indirizzo> collection1;
	IndirizzoDAOImpl manager ;
	Indirizzo i1,i2,i3,i4,i5,i6,i7;
	@Before
	public void setUp() throws Exception {
		manager = new IndirizzoDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCitta() {
		collection = manager.getCitta();
		assertNotNull(collection);
	}
	@Test
	public void testInserisciIndirizzo() {
		i1 = new Indirizzo();
		i1.setVia("via l2931");
		i1.setCap("80100");
		i1.setCitta("Napoli");
		i1.setCivico(17);
		i1.setIdCliente("lerrocarlo98@gmail.com");
		i1 = manager.inserisciIndirizzo(i1);
		assertNotNull(i1);
		
		i2 = new Indirizzo();
		i2.setVia("Via dei Presepi Napoletani");
		i2.setCap("84a26");
		i2.setCitta("Napoli");
		i2.setCivico(17);
		i2.setIdCliente("lerrocarlo98@gmail.com");
		i2 = manager.inserisciIndirizzo(i2);
		assertNotNull(i2);
		
		i3 = new Indirizzo();
		i3.setVia("Via dei Presepi  Napoletani");
		i3.setCap("80100");
		i3.setCitta("Nol87s");
		i3.setCivico(17);
		i3.setIdCliente("lerrocarlo98@gmail.com");
		i3 = manager.inserisciIndirizzo(i3);
		assertNotNull(i3);
		
		i4 = new Indirizzo();
		i4.setVia("Via dei Presepi Napoletani");
		i4.setCap("80100");
		i4.setCitta("Napoli");
		i4.setIdCliente("lerrocarlo98@gmail.com");
		i4.setCivico(4);
		i4 = manager.inserisciIndirizzo(i4);
		assertNotNull(i4);
		
		i5 = new Indirizzo();
		i5.setVia("Via dei Presepi  Napoletani");
		i5.setCap("80100");
		i5.setCitta("Napoli");
		i5.setCivico(1234568);
		i5.setIdCliente("lerrocarlo98@gmail.com");
		i5 = manager.inserisciIndirizzo(i5);
		assertNotNull(i5);
		
		i6 = new Indirizzo();
		i6.setVia("Via dei Presepi  Napoletani");
		i6.setCap("80100");
		i6.setCitta("Napoli");
		i6.setCivico(1);
		i6.setIdCliente("lerrocarlo98@gmail.com");
		i6 = manager.inserisciIndirizzo(i6);
		assertNotNull(i6);
		
		i7 = new Indirizzo();
		i7.setVia("Via dei Presepi  Napoletani");
		i7.setCap("80100");
		i7.setCitta("Napoli");
		i7.setCivico(17);
		i7.setIdCliente("lerrocarlo98@gmail.com");
		i7 = manager.inserisciIndirizzo(i7);
		assertNotNull(i7);
	
	}
	
	
	@Test
	public void testGetIndirizziByCliente() {
		
		collection1 = manager.getIndirizziByCliente("lerrocarlo98@gmail.com");
		assertNotNull(collection1);
	}
	
	@Test
	public void testUpdateIndirizzo() {
		collection1 = manager.getIndirizziByCliente("lerrocarlo98@gmail.com");

		for (Indirizzo i :collection1 ) {
		
			Boolean result;
			i.setVia("Via dei Presepi  Napoletani");
			i.setCap("80100");
			i.setCitta("Napoli");
			i.setCivico(17);
			i.setIdCliente("lerrocarlo98@gmail.com");
			
			result= manager.updateIndirizzo(i);
			assertFalse(result);
		
	}
}

	

	@Test
	public void testGetIndirizziByPizzeria() {
		Indirizzo indirizzo;
		indirizzo = manager.getIndirizziByPizzeria("12345678910");
		assertNotNull(indirizzo);
	}

	@Test
	public void testGetIndirizzoById() {
		collection1 = manager.getIndirizziByCliente("lerrocarlo98@gmail.com");
		for (Indirizzo i :collection1 ) {
			
			Indirizzo indirizzo;
			
			indirizzo = manager.getIndirizzoById(i.getIdIndirizzo());		
			assertNotNull(indirizzo);
		
	}
		
		
		
	}
	
	
	@Test
	public void testEliminaIndirizzo() {
		collection1 = manager.getIndirizziByCliente("lerrocarlo98@gmail.com");

		for (Indirizzo i :collection1 ) {
			
			Boolean result;
			result = manager.eliminaIndirizzo(i);
			assertTrue(result);

		}
		
		
		
		
	}

}
