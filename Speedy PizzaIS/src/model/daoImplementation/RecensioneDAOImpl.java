package model.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import model.DriverManagerConnectionPool;
import model.beans.Carta;
import model.beans.Recensione;
import model.daoInterface.RecensioneDAO;

public class RecensioneDAOImpl implements RecensioneDAO {
	private static final String INSERT="insert into recensione values(NULL,?,?,?,?)";
	private static final String GET_ALL="select * from recensione";
	private static final String GET_BY_ORDINE="select * from recensione where id_ordine=?";
	private static final String GET_BY_CLIENTE="SELECT * FROM ordine inner join recensione on ordine.id = recensione.id_ordine where id_cliente = ?";
	@Override
	public Recensione inserisciRecensione(int idOrdine, Recensione recensione) {
		Connection connection = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(RecensioneDAOImpl.INSERT);
			statement.setInt(1, recensione.getStarring());
			statement.setString(2, recensione.getCommento());
			statement.setDate(3, recensione.getData());
			statement.setInt(4, idOrdine);

			return (statement.executeUpdate()>0) ? recensione:null;


		}catch (SQLException e) {
			System.out.println("Errore durante la connessione." + e.getMessage());
			e.printStackTrace();
			return null;
		}finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
	}

	@Override
	public synchronized Set<Recensione> getRecensioni() {
		Connection connection = null;
		Set<Recensione> set = null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(RecensioneDAOImpl.GET_ALL);
			
			result = statement.executeQuery();
			
				set = new HashSet<Recensione>();
				while(result.next()) {
					set.add(new Recensione(result.getString(3), result.getInt(2), result.getInt(5),result.getDate(4)));
					
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
	public Recensione getRecensione(int idOrdine) {
		Connection connection = null;
		Recensione recensione = null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(RecensioneDAOImpl.GET_BY_ORDINE);
			statement.setInt(1, idOrdine);
			result = statement.executeQuery();
			while(result.next()) {
				recensione = new Recensione(result.getString(3), result.getInt(2), result.getInt(5),result.getDate(4));
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
		return recensione;
		
	}
	@Override
	public Set<Recensione> getRecensioniCliente(String idCliente) {
		Connection connection = null;
		Set<Recensione> set = null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(RecensioneDAOImpl.GET_BY_CLIENTE);
			statement.setString(1, idCliente);
			result = statement.executeQuery();
			
				set = new HashSet<Recensione>();
				while(result.next()) {
					set.add(new Recensione(result.getString(3), result.getInt(2), result.getInt(5),result.getDate(4)));
					
				
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

}
