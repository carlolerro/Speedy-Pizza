package model.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import model.DriverManagerConnectionPool;

import model.beans.Indirizzo;
import model.daoInterface.IndirizzoDAO;

public class IndirizzoDAOImpl  implements IndirizzoDAO{
	private static final String INSERT="insert into indirizzo values(NULL,?,?,?,?,?);";
	private static final String GETID="Select  LAST_INSERT_ID();";
	private static final String GET_BY_ID="SELECT * from speedypizza.indirizzo where id = ?";
	private static final String GET_BY_PIZZERIA="select indirizzo.* from indirizzo natural join pizzeria where partita_iva=?";
	private static final String DELETE="delete from indirizzo where id = ?";
	private static final String GET_BY_CLIENTE="select * from speedypizza.indirizzo where utente = ?";
	private static final String UPDATE="update indirizzo set via = ?, cap=?,civico=?,citta=? where id=?";
	private static final String GET_CITTA="SELECT citta from speedypizza.indirizzo natural join speedypizza.pizzeria";
	@Override
	public Collection<String> getCitta(){
		Connection connection = null;
		ArrayList<String> citta = new ArrayList<String>();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(IndirizzoDAOImpl.GET_CITTA);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				citta.add(result.getString(1));
			}

			return citta;
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
	public boolean updateIndirizzo(Indirizzo indirizzo) {
		Connection connection = null;
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(IndirizzoDAOImpl.UPDATE);
			
			statement.setString(1, indirizzo.getVia());
			statement.setString(2, indirizzo.getCap());
			statement.setInt(3, indirizzo.getCivico());
			statement.setString(4, indirizzo.getCitta());
			statement.setInt(5, indirizzo.getIdIndirizzo());

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
	public synchronized Indirizzo inserisciIndirizzo(Indirizzo indirizzo) {

		Connection connection = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(IndirizzoDAOImpl.INSERT);
			statement.setString(1, indirizzo.getVia());
			statement.setString(2, indirizzo.getCitta());
			statement.setString(3, indirizzo.getCap());
			statement.setInt(4, indirizzo.getCivico());
			statement.setString(5, indirizzo.getIdCliente());
			
			if(statement.executeUpdate()>0) {
				statement= connection.prepareStatement(IndirizzoDAOImpl.GETID);
				ResultSet result = statement.executeQuery();
				while(result.next()) {
					indirizzo.setIdIndirizzo(result.getInt(1));
					return indirizzo;
				}
			}
			
			return null;
			
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
	public boolean eliminaIndirizzo(Indirizzo indirizzo) {
		Connection connection = null;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(IndirizzoDAOImpl.DELETE);
			statement.setInt(1, indirizzo.getIdIndirizzo());
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
	public synchronized Collection<Indirizzo> getIndirizziByCliente(String idCliente) {
		Connection connection = null;
		Set<Indirizzo> set = null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(IndirizzoDAOImpl.GET_BY_CLIENTE);
	
			statement.setString(1, idCliente);
			result = statement.executeQuery();
			
				set = new HashSet<>();
				while(result.next()) {
					set.add(new Indirizzo(result.getInt(5),result.getInt(1),result.getString(2),result.getString(4),result.getString(3),idCliente));
				}
			
			return set;
		}catch (Exception e) {
			System.out.println("Errore durante la connessione." + e.getMessage());
			return set;
			
		} finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
	}
	@Override
	public Indirizzo getIndirizziByPizzeria(String idCliente) {
		Connection connection = null;
		Indirizzo indirizzo = null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(IndirizzoDAOImpl.GET_BY_PIZZERIA);
	
			statement.setString(1, idCliente);
			result = statement.executeQuery();
			
				while(result.next()) {
					indirizzo=new Indirizzo(result.getInt(5),result.getInt(1),result.getString(2),result.getString(4),result.getString(3),idCliente);
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
		return indirizzo;
	}
	@Override
	public Indirizzo getIndirizzoById(int id) {
		Connection connection = null;
		Indirizzo indirizzo = null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(IndirizzoDAOImpl.GET_BY_ID);
			
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			while(result.next()) {
				indirizzo=new Indirizzo(result.getInt(5),result.getInt(1),result.getString(2),result.getString(4),result.getString(3),"");
			}
		
			return indirizzo;
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
}
	
	

