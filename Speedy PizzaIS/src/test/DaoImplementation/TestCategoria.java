package test.DaoImplementation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.beans.Categoria;
import model.daoImplementation.CategoriaDAOImpl;

public class TestCategoria {
	Collection<Categoria> collection;
	CategoriaDAOImpl manager = new CategoriaDAOImpl();
	Categoria cat;
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCategorie() {
		collection = manager.getCategorie();
		assertNotNull(collection);
	}

	@Test
	public void testGetCategoria() {
		cat=manager.getCategoria("Dolci");
		assertNotNull(cat);
	}

	@Test
	public void testGetCategorieByPizzeria() {
		ArrayList<String> categorie = manager.getCategorieByPizzeria("12345678910");
		assertNotNull(categorie);
	}

}
