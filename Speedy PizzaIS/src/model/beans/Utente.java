package model.beans;

import java.sql.Date;

public class Utente {
	private String nome,cognome,email,telefono,password,pizzeriaFattorino,confermaPassword;
	private Date dataRegistrazione;
	private int tipo;
	
	public Utente() {}
	public Utente(String nome, String cognome, String email, String telefono, int tipo,String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.telefono = telefono;
		this.tipo = tipo;
		this.password=password;
		
	}
	public String getPassowrd() {return this.password;}
	public void setPassword(String password) {this.password=password;}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}
	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}
	public String getPizzeriaFattorino() {
		return pizzeriaFattorino;
	}
	public void setPizzeriaFattorino(String pizzeriaFattorino) {
		this.pizzeriaFattorino = pizzeriaFattorino;
	}
	public String getConfermaPassword() {
		return confermaPassword;
	}
	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}
	
	
	
}
