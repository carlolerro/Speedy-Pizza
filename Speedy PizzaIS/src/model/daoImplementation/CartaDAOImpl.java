package model.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import model.DriverManagerConnectionPool;
import model.beans.Carta;
import model.beans.Cliente;
import model.beans.Utente;
import model.daoInterface.CartaDAO;

public class CartaDAOImpl implements CartaDAO{
	private static final String INSERT="insert into carta values(?,?,?,?,?)";
	private static final String DELETE="delete from carta where numero_carta = ?";
	private static final String GET_BY_CLIENTE="select * from carta where cliente =?";
	private static final String GET_BY_NUMERO="select * from carta where numero_carta =?";
	private static final String GET_UTENTE="select * from utente where email = ?";

	@Override
	public synchronized Carta inserisciCarta(Carta carta,String idCliente) {
		Connection connection = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(CartaDAOImpl.INSERT);
			statement.setString(1, carta.getNumeroCarta());
			statement.setString(2, carta.getScadenza());
			statement.setString(3,carta.getCvc());
			statement.setString(4,carta.getIntestatario());
			statement.setString(5, idCliente);
			return (statement.executeUpdate()>0) ? carta:null;
		}catch (Exception e) {
			System.out.println("Errore durante la connessione." + e.getMessage());
			return null;
		} finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
		
		
	}

	@Override
	public synchronized boolean rimuoviCarta(Carta carta) {
		Connection connection = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(CartaDAOImpl.DELETE);
			
			statement.setString(1, carta.getNumeroCarta());
			statement.execute("SET FOREIGN_KEY_CHECKS=0");
			return (statement.executeUpdate()>0) ? true:false;
		}catch (Exception e) {
			System.out.println("Errore durante la connessione." + e.getMessage());
			return false;
		} finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
	}

	@Override
	public synchronized Collection<Carta> getCarteByCliente(Cliente cliente) {
		Connection connection = null;
		HashSet<Carta> set = null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(CartaDAOImpl.GET_BY_CLIENTE);
			statement.setString(1, cliente.getEmail());
			result = statement.executeQuery();
			if(result != null) {
				set = new HashSet<>();
				while(result.next()) {
					Carta carta = new Carta();
					carta.setNumeroCarta(result.getString(1));
					carta.setScadenza(result.getString(2));
					carta.setCvc(result.getString(3));
					carta.setIntestatario(result.getString(4));
					carta.setUtente(CartaDAOImpl.getUtente(result.getString(5)));
				}
			}
			
			
		}catch (Exception e) {
			System.out.println("Errore durante la connessione." + e.getMessage());
			return null;
		} finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
		return set;
	}

	@Override
	public synchronized Carta getCartaByNumero(String numeroCarta) {
		Connection connection = null;
		Carta carta = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(CartaDAOImpl.GET_BY_NUMERO);
			statement.setString(1, numeroCarta);
			ResultSet result = statement.executeQuery();
			while (result.next()){
				carta = new Carta();
				carta.setCvc(result.getString(3));
				carta.setIntestatario(result.getString(4));
				carta.setNumeroCarta(result.getString(1));
				carta.setScadenza(result.getString(2));
				carta.setUtente(CartaDAOImpl.getUtente(result.getString(5)));
			}
		}catch (Exception e) {
			System.out.println("Errore durante la connessione." + e.getMessage());

			return null;
		} finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
		return carta;
	}
	private synchronized static Utente getUtente(String idCliente) {
		ResultSet result=null;
		Connection connection =null;
		Utente utente = null;
		try {				
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CartaDAOImpl.GET_UTENTE);
			preparedStatement.setString(1, idCliente);
			
			result = preparedStatement.executeQuery();
			while(result.next()) {
				utente = new Utente();
				utente.setNome(result.getString(1));
				utente.setCognome(result.getString(2));
				utente.setEmail(result.getString(3));
				utente.setPassword(result.getString(4));
				utente.setTelefono(result.getString(5));
				utente.setDataRegistrazione(result.getDate(6));
				utente.setTipo(result.getInt(7));
				utente.setPizzeriaFattorino(result.getString(8));
				
				}
			
		}catch (Exception e) {
			System.out.println("Errore durante la connessione." + e.getMessage());

			return null;
		}finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
				return null;
			}
		}
		return utente;
		
	}

}
