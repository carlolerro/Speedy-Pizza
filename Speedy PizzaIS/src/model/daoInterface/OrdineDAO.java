package model.daoInterface;

import java.util.Collection;
import java.util.Set;

import model.beans.Ordine;

public interface OrdineDAO {
	public Ordine inserisciOrdine(Ordine ordine);
	public boolean updateOrdine(int idOrdine, String stato);
	public Collection<Ordine> getOrdiniByCliente(String idCliente);
	public Collection<Ordine> getOrdiniByPizzeria(String idPizzeria);
	public Ordine getOrdineByTracker(String tracker);
	public Collection<Ordine> getOrdiniByFattorino(String idFattorino);
	public Collection<String> getAllTracker();
}
