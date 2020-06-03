package model.daoInterface;

import java.util.Collection;
import java.util.Set;

import model.beans.Utente;

public interface UtenteDAO {
	public Utente autenticaUtente(String email,String password);
	public Utente inserisciUtente(Utente utente);
	public boolean eliminaUtente(String email);
	public Utente modificaPassword(String email,String password);
	public Utente modificaEmail(String nuovaEmail,String vecchiaEmail);
	public Utente getUtente(String email);
	public Collection<Utente> getUtenti();
	public Utente modificaDati(Utente utente,String vecchiaEmail);
	
}
