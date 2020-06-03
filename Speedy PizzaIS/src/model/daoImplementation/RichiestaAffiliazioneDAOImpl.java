package model.daoImplementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import model.DriverManagerConnectionPool;
import model.beans.RichiestaAffiliazione;
import model.daoInterface.RichiestaAffiliazioneDAO;

public class RichiestaAffiliazioneDAOImpl implements RichiestaAffiliazioneDAO {
	public static final String INSERT= "insert into richiesta_affiliazione values(NULL,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE ="update richiesta_affiliazione set stato = ? where id_richiesta = ?";
	public static final String GET_ALL="select * from richiesta_affiliazione";
	public static final String RIMUOVI="delete from richiesta_affiliazione where id_richiesta=?";
	@Override
	public synchronized RichiestaAffiliazione inserisciRichiesta(RichiestaAffiliazione richiesta) {

		Connection connection = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(RichiestaAffiliazioneDAOImpl.INSERT);
			statement.setString(1, richiesta.getNomePizzeria());
			statement.setString(2, richiesta.getCommento());
			statement.setDate(3, new Date(System.currentTimeMillis()));
			statement.setString(4, richiesta.getStato());
			statement.setString(5,richiesta.getTelefono());
			statement.setString(6, richiesta.getPartitaIva());
			statement.setString(7,richiesta.getCognome());
			statement.setString(8, richiesta.getNome());
			statement.setString(9, richiesta.getEmail());
			statement.setString(10, richiesta.getPassword());
			return (statement.executeUpdate()>0) ? richiesta:null;


		}catch (SQLException e) {
			System.out.println("Errore durante la connessione." + e.getMessage());
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
	public synchronized boolean cambiaStato(String stato, int idRichiesta) {
		Connection connection = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(RichiestaAffiliazioneDAOImpl.UPDATE);
			statement.setString(1, stato);
			statement.setInt(2, idRichiesta);
			return (statement.executeUpdate()>0) ? true:false;

			
		}catch (SQLException e) {
			System.out.println("Errore durante la connessione." + e.getMessage());
			return false;
		}finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
	}

	@Override
	public synchronized Collection<RichiestaAffiliazione> getRichieste() {
		Connection connection = null;
		ResultSet result = null;
		HashSet<RichiestaAffiliazione> set = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(RichiestaAffiliazioneDAOImpl.GET_ALL);
			result = statement.executeQuery();
			if(result != null) {
				set = new HashSet<RichiestaAffiliazione>();
				while(result.next()) {
					RichiestaAffiliazione richiesta = new RichiestaAffiliazione();
					richiesta.setId(result.getInt(1));
					richiesta.setNomePizzeria(result.getString(2));
					richiesta.setCommento(result.getString(3));
					richiesta.setDate(result.getDate(4));
					richiesta.setStato(result.getString(5));
					richiesta.setTelefono(result.getString(6));
					richiesta.setPartitaIva(result.getString(7));
					richiesta.setCognome(result.getString(8));
					richiesta.setNome(result.getString(9));
					richiesta.setEmail(result.getString(10));
					richiesta.setPassword(result.getString(11));
					set.add(richiesta);
				}
				
			}
		}catch (SQLException e) {
			System.out.println("Errore durante la connessione." + e.getMessage());
			return null;
		}finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
		return set;
	}

	@Override
	public boolean rimuoviRichiesta(int id) {
		Connection connection = null;
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(RichiestaAffiliazioneDAOImpl.RIMUOVI);
			statement.setInt(1, id);
			return (statement.executeUpdate()>0) ? true:false;
			
		}catch (SQLException e) {
			System.out.println("Errore durante la connessione." + e.getMessage());
			return false;
		}finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
		
	}

}
