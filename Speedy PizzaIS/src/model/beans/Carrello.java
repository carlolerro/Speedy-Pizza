package model.beans;

import java.util.HashMap;
import java.util.Map;

public class Carrello {
	private HashMap<Prodotto, Integer> prodotti;
	private float totale;
	
	public HashMap<Prodotto, Integer> getProdotti() {
		return prodotti;
	}
	public void setProdotti(HashMap<Prodotto, Integer> prodotti) {
		this.prodotti = prodotti;
	}
	public float getTotale() {
		return totale;
	}
	public void setTotale(float totale) {
		this.totale = totale;
	}
	
	public Carrello() {}
	public Carrello(HashMap<Prodotto, Integer> prodotti, float totale) {
		super();
		this.prodotti = prodotti;
		this.totale = totale;
	}
	
}
