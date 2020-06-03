package model.beans;

import java.util.Set;

public class Cliente extends Utente {
	private Set indirizzi,carte,ordini,recensioni;

	public Set getIndirizzi() {
		return indirizzi;
	}

	public void setIndirizzi(Set indirizzi) {
		this.indirizzi = indirizzi;
	}

	public Set getCarte() {
		return carte;
	}

	public void setCarte(Set carte) {
		this.carte = carte;
	}

	public Set getOrdini() {
		return ordini;
	}

	public void setOrdini(Set ordini) {
		this.ordini = ordini;
	}

	public Set getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(Set recensioni) {
		this.recensioni = recensioni;
	}

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nome, String cognome, String email, String telefono, int tipo,String password) {
		super(nome, cognome, email, telefono, tipo,password);
		// TODO Auto-generated constructor stub
	}

	public Cliente(Set indirizzi, Set carte, Set ordini, Set recensioni) {
		super();
		this.indirizzi = indirizzi;
		this.carte = carte;
		this.ordini = ordini;
		this.recensioni = recensioni;
	}

	public Cliente(String nome, String cognome, String email, String telefono, int tipo,String password,Set indirizzi, Set carte, Set ordini, Set recensioni) {
		super(nome, cognome, email, telefono, tipo,password);
		this.indirizzi = indirizzi;
		this.carte = carte;
		this.ordini = ordini;
		this.recensioni = recensioni;
	}

	
	
}
