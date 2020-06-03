package test.DaoImplementation;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.beans.RichiestaAffiliazione;
import model.daoImplementation.RichiestaAffiliazioneDAOImpl;

public class TestRichiesta {
	RichiestaAffiliazioneDAOImpl manager;
	@Before
	public void setUp() throws Exception {
		manager = new RichiestaAffiliazioneDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInserisciRichiesta() {
		for(int x = 0; x<10;x++) {
		RichiestaAffiliazione r = new RichiestaAffiliazione();
		r.setCognome("lerro");
		r.setCommento("prova");
		r.setConfermaPassword("123");
		r.setDate(new Date(System.currentTimeMillis()));
		r.setNome("carlo");
		r.setNomePizzeria("carlo's pizza");
		r.setPartitaIva("132456");
		r.setPassword("123");
		r.setStato("In sospeso");
		r.setTelefono("34512");
		r.setEmail("lerro@gmail.com"+x);
		RichiestaAffiliazione c = manager.inserisciRichiesta(r);
		assertNotNull(c);
		}
	}

	@Test
	public void testCambiaStato() {
		
		Collection<RichiestaAffiliazione> c = manager.getRichieste();
		Boolean result;
		for (RichiestaAffiliazione x: c) {
			result= manager.cambiaStato("Accettata", x.getId());
			assertTrue(result);
		}
	}

	@Test
	public void testGetRichieste() {
		Collection<RichiestaAffiliazione> c;
		c = manager.getRichieste();
		assertNotNull(c);
	}

	@Test
	public void testRimuoviRichiesta() {
		Collection<RichiestaAffiliazione> c = manager.getRichieste();
		Boolean result;
		for (RichiestaAffiliazione x: c) {
			result= manager.rimuoviRichiesta(x.getId());
			assertTrue(result);
		}
	}

}
