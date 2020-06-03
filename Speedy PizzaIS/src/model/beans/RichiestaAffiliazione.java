package model.beans;

import java.sql.Date;

public class RichiestaAffiliazione {
	private int id;
	private Date date;
	private String confermaPassword,nomePizzeria,nome,cognome,commento,stato,telefono,partitaIva,email,password;
	public Date getDate() {
		return date;
	}
	public int getId() {return this.id;}
	public void setId(int id) {this.id = id;}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNomePizzeria() {
		return nomePizzeria;
	}
	public void setNomePizzeria(String nomePizzeria) {
		this.nomePizzeria = nomePizzeria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCommento() {
		return commento;
	}
	public void setCommento(String commento) {
		this.commento = commento;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RichiestaAffiliazione(Date date, String nomePizzeria, String nome, String cognome, String commento,
			String stato, String telefono, String partitaIva, String email, String password) {
		super();
		this.date = date;
		this.nomePizzeria = nomePizzeria;
		this.nome = nome;
		this.cognome = cognome;
		this.commento = commento;
		this.stato = stato;
		this.telefono = telefono;
		this.partitaIva = partitaIva;
		this.email = email;
		this.password = password;
	}
	public RichiestaAffiliazione() {}
	public String getConfermaPassword() {
		return confermaPassword;
	}
	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
}
