package model.daoInterface;

import java.util.Collection;

import model.beans.Carta;
import model.beans.Cliente;

public interface CartaDAO {
	public Carta inserisciCarta(Carta carta,String idCliente);
	public boolean rimuoviCarta(Carta carta);
	public Collection<Carta> getCarteByCliente(Cliente idCliente);
	public Carta getCartaByNumero(String numeroCarta);
}
