package model.daoInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import model.beans.Pizzeria;
import model.beans.Utente;

public interface PizzeriaDAO {
	public Pizzeria inserisciPizzeria(Pizzeria pizzeria);
	public Pizzeria updatePizzeria(Pizzeria pizzeria,String vecchiaPartita);
	public ArrayList<Pizzeria> getPizzerieByCitta(String citta);
	public Pizzeria getPizzeria(String titolare);
	public Pizzeria getPizzeriaByIva(String iva);

	public Collection<Pizzeria> getPizzerie();
	public Collection<Utente> getFattorini(String idPizzeria);
	public Pizzeria inserisciPizzeriaRichiesta(Pizzeria pizzeria);
}
