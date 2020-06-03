package model.beans;

import java.util.Set;

import com.sun.tools.javac.util.StringUtils;



public class Prodotto {
	private Categoria Categoria;
	private String nome,ingredienti;
	private float prezzo;
	private String disponibilita;
	private String idPizzeria;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(String ingredienti) {
		this.ingredienti = ingredienti;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	
	public Prodotto(String categoria, String nome, String ingredienti, float prezzo, String disponibilita,
			String pizzeria) {
		super();
		
		this.nome = nome;
		this.ingredienti = ingredienti;
		this.prezzo = prezzo;
		this.setDisponibilita(disponibilita);
		this.idPizzeria = pizzeria;
	}
	public Prodotto() {}
	public String getIdPizzeria() {
		return idPizzeria;
	}
	public void setIdPizzeria(String idPizzeria) {
		this.idPizzeria = idPizzeria;
	}
	public String getDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(String disponibilita) {
		this.disponibilita = disponibilita;
	}
	
	public Categoria getCategoria() {
		return Categoria;
	}
	public void setCategoria(Categoria categoria) {
		Categoria = categoria;
	}
	@Override
	public boolean equals(Object obj) {
		if(this.nome.equals(((Prodotto) obj).getNome())) {
			return true;
		}
		return false;
	}
	@Override
    public int hashCode() {
        return 1;
    }
}
