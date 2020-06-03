package model.beans;

import java.sql.Date;

public class Recensione {
	private String commento;
	private int starring;
	private int idOrdine;
	private Date data;
	public String getCommento() {
		return commento;
	}
	public void setCommento(String commento) {
		this.commento = commento;
	}
	public int getStarring() {
		return starring;
	}
	public void setStarring(int starring) {
		this.starring = starring;
	}
	public Recensione(String commento, int starring, int idOrdine, Date data) {
		super();
		this.commento = commento;
		this.starring = starring;
		this.idOrdine = idOrdine;
		this.data = data;
	}
	private Recensione() {}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
}
