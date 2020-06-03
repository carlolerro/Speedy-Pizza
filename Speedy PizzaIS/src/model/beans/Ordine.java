package model.beans;

import java.sql.Date;

public class Ordine {
	
	private Indirizzo indirizzo;
	private Carta carta;
	private Cliente cliente;
	private Utente fattorino;
	private Pizzeria pizzeria;
	private Recensione recensione;
	private String stato,tracking;
	 
	private float totale;	
	private int id,tipoOrdine,tipoPagamento,numeroOrdine;
	private Carrello carrello;
	private Date data;
	public Indirizzo getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}
	public Carta getCarta() {
		return carta;
	}
	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Utente getFattorino() {
		return fattorino;
	}
	public void setFattorino(Utente fattorino) {
		this.fattorino = fattorino;
	}
	public Pizzeria getPizzeria() {
		return pizzeria;
	}
	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}
	public Recensione getRecensione() {
		return recensione;
	}
	public void setRecensione(Recensione recensione) {
		this.recensione = recensione;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public int getNumeroOrdine() {
		return numeroOrdine;
	}
	public void setNumeroOrdine(int numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}
	public String getTracking() {
		return tracking;
	}
	public void setTracking(String tracking) {
		this.tracking = tracking;
	}
	public float getTotale() {
		return totale;
	}
	public void setTotale(float totale) {
		this.totale = totale;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTipoOrdine() {
		return tipoOrdine;
	}
	public void setTipoOrdine(int tipoOrdine) {
		this.tipoOrdine = tipoOrdine;
	}
	public Carrello getCarrello() {
		return carrello;
	}
	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}
	public Ordine(Indirizzo indirizzo, Carta carta, Cliente cliente, Utente fattorino, Pizzeria pizzeria,
			Recensione recensione, String stato, int numeroOrdine, String tracking, float totale, int id,
			int tipoOrdine, Carrello carrello) {
		super();
		this.indirizzo = indirizzo;
		this.carta = carta;
		this.cliente = cliente;
		this.fattorino = fattorino;
		this.pizzeria = pizzeria;
		this.recensione = recensione;
		this.stato = stato;
		this.numeroOrdine = numeroOrdine;
		this.tracking = tracking;
		this.totale = totale;
		this.id = id;
		this.tipoOrdine = tipoOrdine;
		this.carrello = carrello;
	}
	public Ordine() {}
	public int getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(int tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
