package model.daoImplementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import model.DriverManagerConnectionPool;
import model.beans.Carta;
import model.beans.Cliente;
import model.beans.Indirizzo;
import model.beans.Ordine;
import model.beans.Pizzeria;
import model.beans.Recensione;
import model.beans.Utente;

import model.daoInterface.UtenteDAO;

public class UtenteDAOImpl implements UtenteDAO{
	private static final String AUTENTICA ="select * from utente where email = ? and pass = ?";
	private static final String GET_UTENTI ="select * from utente";
	private static final String GET_UTENTE ="selct * from utente where email = ?";
	private static final String MODIFICA_EMAIL ="update utente set email = ? where email = ?";
	private static final String MODIFICA_PASSWORD ="update utente set pass = ? where email = ?";
	private static final String INSERT ="insert into utente values(?,?,?,?,?,?,?,?)";
	private static final String DELETE ="delete from utente where email = ?";
	private static final String UPDATE_DATI="update utente set nome =?, cognome = ?, email= ?,telefono=? where email=?";
	/*query che riguardano il cliente*/
	private static final String GET_CARTE ="select * from carta where cliente = ?";
	private static final String GET_ORDINI ="select * from ordine where id_cliente =?";
	private static final String GET_RECENSIONI ="select recensione.id,recensione.starring,recensione.recensione,recensione.date,recensione.id_ordine from ordine inner join recensione where id_cliente = ?";
	private static final String GET_INDIRIZZI ="select * from indirizzo where utente = ?";

	@Override
	public Utente autenticaUtente(String email, String password) {
		/*
		 * tipo = 0 cliente
		 * tipo = 1 ristoratore
		 * tipo = 2 fattorino
		 * tipo = 3 gestore utenti
		 */
		ResultSet result=null;
		Connection connection =null;
		Utente utente = null;
		try {				
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UtenteDAOImpl.AUTENTICA);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			result = preparedStatement.executeQuery();
			
				
				
				
				while (result.next()) {
					utente = new Cliente();
					utente.setNome(result.getString(1));
					utente.setCognome(result.getString(2));
					utente.setEmail(result.getString(3));
					utente.setPassword(result.getString(4));
					utente.setTelefono(result.getString(5));
					utente.setDataRegistrazione(result.getDate(6));
					utente.setTipo(result.getInt(7));
					utente.setPizzeriaFattorino(result.getString(8));
					
				}
				if (utente.getTipo() == 0) {
					
					((Cliente) utente).setCarte(UtenteDAOImpl.getCarte(utente.getEmail()));
					System.out.println("dopo");
					//((Cliente) utente).setOrdini(UtenteDAOImpl.getOrdini(utente.getEmail()));
					System.out.println("prima");
					((Cliente) utente).setRecensioni(UtenteDAOImpl.getRecensioni(utente.getEmail()));
					((Cliente) utente).setIndirizzi(UtenteDAOImpl.getIndirizzi(utente.getEmail()));
					
				}
			
		}catch (Exception e) {
			System.out.println("Errore durante la connessione. " + e.getMessage());

			return null;
		}finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione. " + e.getMessage());
				return null;
			}
		}
		
		return utente;
			
		
	}

	@Override
	public Utente inserisciUtente(Utente utente) {
		Connection connection = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtenteDAOImpl.INSERT);
			statement.setString(1, utente.getNome());
			statement.setString(2, utente.getCognome());
			statement.setString(3, utente.getEmail());
			statement.setString(4, utente.getPassowrd());
			statement.setString(5, utente.getTelefono());
			Date date = new Date(System.currentTimeMillis());
			statement.setDate(6, date);
			statement.setInt(7, utente.getTipo());
			if(utente.getPizzeriaFattorino().equals("")) {
				statement.setNull(8, Types.VARCHAR);
			}else {
				statement.setString(8, utente.getPizzeriaFattorino());
			}

		


			return (statement.executeUpdate()>0) ? utente:null;


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
	public boolean eliminaUtente(String email) {
		Connection connection = null;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtenteDAOImpl.DELETE);
			statement.setString(1, email);
			return (statement.executeUpdate() >0) ? true:false;
			
			
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
	public Utente modificaPassword(String email, String password) {
		Connection connection = null;
		Utente utente = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtenteDAOImpl.MODIFICA_PASSWORD);
			statement.setString(1, password);
			statement.setString(2, email);
			if(statement.executeUpdate()>0) {
				statement = connection.prepareStatement("select * from utente where email = ?");
				statement.setString(1, email);
				ResultSet result = statement.executeQuery();
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
			}

			
		}catch (SQLException e) {
			System.out.println("Errore durante la connessione." + e.getMessage());
			return utente;
		}finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
		return utente;
		
	}
	public Utente modificaDati(Utente utente, String vecchiaEmail) {
		Connection connection = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtenteDAOImpl.UPDATE_DATI);
			statement.setString(1, utente.getNome());
			statement.setString(2, utente.getCognome());
			statement.setString(3,	utente.getEmail());
			statement.setString(4,utente.getTelefono());
			statement.setString(5, vecchiaEmail);
			return (statement.executeUpdate()>0) ? utente:null ;
				
					

			
		}catch (SQLException e) {
			System.out.println("Errore durante la connessione." + e.getMessage());
			return utente;
		}finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
		
		
	}

	@Override
	public Utente modificaEmail(String nuovaEmail, String vecchiaEmail) {
		Connection connection = null;
		Utente utente = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtenteDAOImpl.MODIFICA_EMAIL);
			statement.setString(1, nuovaEmail);
			statement.setString(2, vecchiaEmail);
			if(statement.executeUpdate()>0) {
				utente = new Utente();
				statement = connection.prepareStatement("select * from utente where email = ?");
				statement.setString(1, nuovaEmail);
				ResultSet result = statement.executeQuery();
				if(result != null) {
					utente.setNome(result.getString(1));
					utente.setCognome(result.getString(2));
					utente.setEmail(result.getString(3));
					utente.setPassword(result.getString(4));
					utente.setTelefono(result.getString(5));
					utente.setDataRegistrazione(result.getDate(6));
					utente.setTipo(result.getInt(7));
					utente.setPizzeriaFattorino(result.getString(8));
				}
			}

			
		}catch (SQLException e) {
			System.out.println("Errore durante la connessione." + e.getMessage());
			return utente;
		}finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
		return utente;
	}

	@Override
	public Utente getUtente(String email) {
		Connection connection = null;
		Utente utente= null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtenteDAOImpl.GET_UTENTE);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while (result.next()){
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
		} finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
		return utente;
	
	}

	@Override
	public Set<Utente> getUtenti() {
		Connection connection = null;
		HashSet<Utente> set = null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(UtenteDAOImpl.GET_UTENTI);
			
			result = statement.executeQuery();
			
				set = new HashSet<>();
				while(result.next()) {
					Utente utente = new Utente();
					utente.setNome(result.getString(1));
					utente.setCognome(result.getString(2));
					utente.setEmail(result.getString(3));
					utente.setPassword(result.getString(4));
					utente.setTelefono(result.getString(5));
					utente.setDataRegistrazione(result.getDate(6));
					utente.setTipo(result.getInt(7));
					utente.setPizzeriaFattorino(result.getString(8));
					set.add(utente);
					
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
	private static Set<Carta> getCarte(String email) {
		ResultSet result=null;
		Connection connection =null;
		Set<Carta> set = null;
		try {				
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UtenteDAOImpl.GET_CARTE);
			preparedStatement.setString(1, email);
			
			result = preparedStatement.executeQuery();
			if(result != null) {
				set = new HashSet<Carta>();
				while(result.next()) {
					Carta c = new Carta();
					c.setNumeroCarta(result.getString(1));
					c.setScadenza(result.getString(2));
					c.setCvc(result.getString(3));
					c.setIntestatario(result.getString(4));
					set.add(c);
				}
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
		return set;
		
	}
	
	private static Set<Recensione> getRecensioni(String email) {
		ResultSet result=null;
		Connection connection =null;
		Set<Recensione> set = null;
		try {				
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UtenteDAOImpl.GET_RECENSIONI);
			preparedStatement.setString(1, email);
			
			result = preparedStatement.executeQuery();
			if(result != null) {
				set = new HashSet<Recensione>();
				while(result.next()) {
					set.add(new Recensione(result.getString(3), result.getInt(2), result.getInt(5),result.getDate(4)));
					
				}
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
		return set;
		
	}
	/*LA FUNZIONE PRENDE IN INPUT L'IDENTIFICATIVO DEL CLIENTE E PRENDE TUTTI GLI ORDINI AD ESSO ASSOCIATO
	 * PER OGNI ORDINE PRENDE:
	 * 	NOME PIZZERIA; NUMERO CARTA UTILIZZATA PER IL PAGAMENTO; NOME E COGNOME DEL FATTORINO; INDIRIZZO DI CONSEGNA*/
	/*private static Set<Ordine> getOrdini(String email) {
		ResultSet result=null;
		Connection connection =null;
		Set<Ordine> set = null;
		try {				
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UtenteDAOImpl.GET_ORDINI);
			preparedStatement.setString(1, email);
			
			result = preparedStatement.executeQuery();
			
				set = new HashSet<Ordine>();
				while(result.next()) {
					Ordine ordine = new Ordine();
					ordine.setId(result.getInt(1));
					ordine.setTracking(result.getString(10));
					ordine.setTipoOrdine(result.getInt(6));
					ordine.setStato(result.getString(3));
					ordine.setTotale(result.getFloat(4));
					ordine.setNumeroOrdine(result.getInt(11));
					ordine.setTipoPagamento(result.getInt(2));
					PreparedStatement preparedStatement2 = connection.prepareStatement("select nome from pizzeria inner join ordine where ordine.id = ?");
					preparedStatement2.setInt(1, ordine.getId());
					ResultSet result2 = preparedStatement2.executeQuery();
					Pizzeria pizzeria = new Pizzeria();
					if(result2 != null) {
						
						pizzeria.setNome(result2.getString(1));
								
					}
					ordine.setPizzeria(pizzeria);
					preparedStatement2 = connection.prepareStatement("select numero_carta from ordine natural join carta where ordine.id = ?");
					preparedStatement2.setInt(1, ordine.getId());
					result2 = preparedStatement2.executeQuery();
					Carta carta = new Carta();
					if(result2 != null) {
						
						carta.setNumeroCarta(result2.getString(1));
								
					}
					ordine.setCarta(carta);
					preparedStatement2 = connection.prepareStatement("select nome,cognome from ordine inner join utente where ordine.id = ?");
					preparedStatement2.setInt(1, ordine.getId());
					result2 = preparedStatement2.executeQuery();
					Utente utente = new Utente();
					if(result2 != null) {
						
						utente.setNome(result2.getString(1));
						utente.setCognome(result2.getString(2));
								
					}
					ordine.setFattorino(utente);
					preparedStatement2 = connection.prepareStatement("select indirizzo.id,via,civico,citta,cap from ordine inner join indirizzo where ordine.id = ?");
					preparedStatement2.setInt(1, ordine.getId());
					result2 = preparedStatement2.executeQuery();
					Indirizzo indirizzo= new Indirizzo();
					if(result2 != null) {
						
						indirizzo.setIdIndirizzo(result2.getInt(1));
						indirizzo.setVia(result2.getString(2));
						indirizzo.setCivico(result2.getInt(3));
						indirizzo.setCitta(result2.getString(4));
						indirizzo.setCap(result2.getString(5));

								
					}
					ordine.setIndirizzo(indirizzo);
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
		return set;
		
	}*/
	
	private static Set<Indirizzo> getIndirizzi(String email) {
		ResultSet result=null;
		Connection connection =null;
		Set<Indirizzo> set = null;
		try {				
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UtenteDAOImpl.GET_INDIRIZZI);
			preparedStatement.setString(1, email);
			
			result = preparedStatement.executeQuery();
			if(result != null) {
				set = new HashSet<Indirizzo>();
				while(result.next()) {
					set.add(new Indirizzo(result.getInt(5),result.getInt(1),result.getString(2),result.getString(4),result.getString(3),result.getString(6)));
					
				}
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
		return set;
		
	}


}
