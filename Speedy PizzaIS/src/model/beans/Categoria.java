package model.beans;

import java.util.Set;

public class Categoria {
	private String nome;
	private int iva;
	
	public Categoria(String nome, int iva) {
		super();
		this.nome = nome;
		this.iva = iva;
		
	}
	
	public Categoria() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	
	
}
