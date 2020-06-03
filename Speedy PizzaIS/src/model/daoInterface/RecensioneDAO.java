package model.daoInterface;

import java.util.Collection;
import java.util.Set;

import model.beans.Recensione;

public interface RecensioneDAO {
	public Recensione inserisciRecensione(int idOrdine,Recensione recensione);
	public Collection<Recensione> getRecensioni();
	public Recensione getRecensione(int idOrdine);
	public Collection<Recensione> getRecensioniCliente(String idCliente);
}
