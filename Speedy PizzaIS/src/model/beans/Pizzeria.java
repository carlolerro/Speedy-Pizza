package model.beans;

import java.sql.Time;
import java.util.Set;

public class Pizzeria {
	private String nome,descrizione,telefono,partitaIva,iban,giorniApertura;
	private Indirizzo indirizzo;
	private Set<Prodotto> prodotti;
	private Utente titolare;
	private Time orarioApertura,orarioChiusura;
	public String getNome() {
		
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public Indirizzo getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}
	public Set<Prodotto> getProdotti() {
		return prodotti;
	}
	public void setProdotti(Set<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
	public Utente getTitolare() {
		return titolare;
	}
	public void setTitolare(Utente titolare) {
		this.titolare = titolare;
	}
	public Pizzeria(String nome, String descrizione, String telefono, String partitaIva, String iban,
			Indirizzo indirizzo, Set<Prodotto> prodotti, Utente titolare) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.telefono = telefono;
		this.partitaIva = partitaIva;
		this.iban = iban;
		this.indirizzo = indirizzo;
		this.prodotti = prodotti;
		this.titolare = titolare;
	}
	public Pizzeria() {}
	public Time getOrarioChiusura() {
		return orarioChiusura;
	}
	public void setOrarioChiusura(Time orarioChiusura) {
		this.orarioChiusura = orarioChiusura;
	}
	public Time getOrarioApertura() {
		return orarioApertura;
	}
	public void setOrarioApertura(Time orarioApertura) {
		this.orarioApertura = orarioApertura;
	}
	public String getGiorniApertura() {
		return giorniApertura;
	}
	public void setGiorniApertura(String giorniApertura) {
		this.giorniApertura = giorniApertura;
	}
}
