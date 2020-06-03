package model.beans;

public class Indirizzo {
	private int civico,idIndirizzo;
	private String via,cap,citta;
	private String idCliente;
	public int getCivico() {
		return civico;
	}
	public void setCivico(int civico) {
		this.civico = civico;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	
	public int getIdIndirizzo() {
		return idIndirizzo;
	}
	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}
	
	
	
	public Indirizzo(int civico, int idIndirizzo, String via, String cap, String citta, String idCliente) {
		super();
		this.civico = civico;
		this.idIndirizzo = idIndirizzo;
		this.via = via;
		this.cap = cap;
		this.citta = citta;
		this.idCliente = idCliente;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public Indirizzo() {}
	@Override
	public String toString() {
		
		return this.via+" "+this.civico+" "+this.cap+" " + this.citta;
	}
}

