package model.beans;

import java.sql.Date;
import java.util.Set;

public class Carta {
	private String numeroCarta,intestatario,cvc;
	private String scadenza;
	private Utente utente;
	
	public String getNumeroCarta() {
		return numeroCarta;
	}
	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	public String getIntestatario() {
		return intestatario;
	}
	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
	

	
	public Carta(String numeroCarta, String intestatario, String cvc, String scadenza,String cliente ) {
		super();
		this.numeroCarta = numeroCarta;
		this.intestatario = intestatario;
		this.cvc = cvc;
		this.scadenza=scadenza;
		
		
	}
	public Carta() {}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public String getScadenza() {
		return scadenza;
	}
	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}
	
}
