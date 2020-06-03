package model.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import model.DriverManagerConnectionPool;
import model.beans.Categoria;
import model.beans.Indirizzo;
import model.beans.Pizzeria;
import model.beans.Prodotto;
import model.beans.Utente;
import model.daoFactory.IndirizzoDAOFactory;
import model.daoInterface.PizzeriaDAO;

public class PizzeriaDAOImpl implements PizzeriaDAO {
	public static final String INSERT = "insert into pizzeria values(?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE = "update pizzeria set partita_iva = ?, nome = ?, orario_apertura = ?, "
			+ "orario_chiusura = ?, giorni_apertura = ?, indirizzo = ?, titolare = ?, iban = ?, descrizione = ?, telefono = ? where partita_iva=?";
	public static final String GET_BY_CITTA = "SELECT * FROM speedypizza.pizzeria inner join speedypizza.indirizzo on pizzeria.indirizzo = indirizzo.id where citta = ?";
	public static final String GET_PIZZERIA_BY_TITOLARE = "select * from  pizzeria where titolare = ?";
	public static final String GET_ALL = "select * from pizzeria inner join indirizzo on pizzeria.indirizzo = indirizzo.id";
	public static final String GET_FATTORINI="select utente.* from pizzeria inner join utente on pizzeria.partita_iva = utente.pizzeria_fattorino where pizzeria.partita_iva = ?";
	public static final String INSERT_BY_RICHIESTA = "insert into pizzeria values(?,?,null,null,null,null,?,null,null,?)";
	public static final String GET_INDIRIZZO="select * from indirizzo where id=?";
	public static final String GET_PIZZERIA_BY_IVA = "select * from  pizzeria where partita_iva = ?";

	private static Indirizzo getIndirizzo(int id) {
		ResultSet result=null;
		Connection connection =null;
		Indirizzo indirizzo= null;
		try {				
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(PizzeriaDAOImpl.GET_INDIRIZZO);
			preparedStatement.setInt(1, id);
			
			result = preparedStatement.executeQuery();
			while(result.next()) {
				indirizzo = new Indirizzo();
				indirizzo.setIdIndirizzo(result.getInt(1));
				indirizzo.setVia(result.getString(2));
				indirizzo.setCitta(result.getString(3));
				indirizzo.setCap(result.getString(4));
				indirizzo.setCivico(result.getInt(5));
				indirizzo.setIdCliente("");
				
			}
			
		}catch (Exception e) {
			System.out.println("Errore durante la connessio indirizzo." + e.getMessage());

			return null;
		}finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione indirizzo 2." + e.getMessage());
				return null;
			}
		}
		return indirizzo;
	}
	@Override
	public Pizzeria inserisciPizzeriaRichiesta(Pizzeria pizzeria) {
		Connection connection = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(PizzeriaDAOImpl.INSERT_BY_RICHIESTA);
			statement.setString(1, pizzeria.getPartitaIva());
			statement.setString(2, pizzeria.getNome());
			statement.setString(3, pizzeria.getTitolare().getEmail());
			statement.setString(4, pizzeria.getTelefono());
			
			
			return (statement.executeUpdate()>0) ? pizzeria:null;
			
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
	public Pizzeria inserisciPizzeria(Pizzeria pizzeria) {
		Connection connection = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(PizzeriaDAOImpl.INSERT);
			statement.setString(1, pizzeria.getPartitaIva());
			statement.setString(2, pizzeria.getNome());
			statement.setTime(3, pizzeria.getOrarioApertura());
			statement.setTime(4, pizzeria.getOrarioChiusura());
			statement.setString(5, pizzeria.getGiorniApertura());
			statement.setInt(6, pizzeria.getIndirizzo().getIdIndirizzo());
			statement.setString(7, pizzeria.getTitolare().getEmail());
			statement.setString(8, pizzeria.getIban());
			statement.setString(9, pizzeria.getDescrizione());
			statement.setString(10, pizzeria.getTelefono());
			
			return (statement.executeUpdate()>0) ? pizzeria:null;
			
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
	public Pizzeria updatePizzeria(Pizzeria pizzeria,String vecchiaPartita) {
		Connection connection = null;
		Pizzeria pizz = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(PizzeriaDAOImpl.UPDATE);
			statement.setString(1, pizzeria.getPartitaIva());
			statement.setString(2, pizzeria.getNome());
			statement.setTime(3, pizzeria.getOrarioApertura());
			statement.setTime(4, pizzeria.getOrarioChiusura());
			statement.setString(5, pizzeria.getGiorniApertura());
			System.out.println(pizzeria.getIndirizzo().getIdIndirizzo());
			if(pizzeria.getIndirizzo().getIdIndirizzo()==-1) {
				
				IndirizzoDAOFactory.getIndirizzoDAO().inserisciIndirizzo(pizzeria.getIndirizzo());
				
			}else {
				IndirizzoDAOFactory.getIndirizzoDAO().updateIndirizzo(pizzeria.getIndirizzo());
			}
			statement.setInt(6, pizzeria.getIndirizzo().getIdIndirizzo());
			statement.setString(7, pizzeria.getTitolare().getEmail());
			statement.setString(8, pizzeria.getIban());
			statement.setString(9, pizzeria.getDescrizione());
			statement.setString(10, pizzeria.getTelefono());
			statement.setString(11, vecchiaPartita);
			if (statement.executeUpdate()>0) {
				statement = connection.prepareStatement("select * from pizzeria inner join indirizzo  where partita_iva = ?");
				statement.setString(1, pizzeria.getPartitaIva());
				ResultSet result = statement.executeQuery();
				pizz = new Pizzeria();
				while(result.next()) {
					pizz.setPartitaIva(result.getString(1));
					pizz.setNome(result.getString(2));
					pizz.setOrarioApertura(result.getTime(3));
					pizz.setOrarioChiusura(result.getTime(4));
					pizz.setGiorniApertura(result.getString(5));
					pizz.setIndirizzo(new Indirizzo(result.getInt("civico"), result.getInt("id"), result.getString("via"), result.getString("cap"), result.getString("citta"), result.getString("utente")));
					pizz.setTitolare(PizzeriaDAOImpl.getTitolare(result.getString("titolare")));
					pizz.setIban(result.getString(8));
					pizz.setDescrizione(result.getString(9));
					pizz.setTelefono(result.getString(10));
					pizz.setProdotti(PizzeriaDAOImpl.getProdotti(result.getString(1)));

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
		return pizz;
	}

	@Override
	public ArrayList<Pizzeria> getPizzerieByCitta(String citta) {
		Connection connection = null;
		ArrayList<Pizzeria> pizzerie = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(PizzeriaDAOImpl.GET_BY_CITTA);
			statement.setString(1, citta);
			ResultSet result = statement.executeQuery();
			pizzerie = new ArrayList<Pizzeria>();
			while(result.next()) {
					
					Pizzeria pizz = new Pizzeria();
					pizz.setPartitaIva(result.getString(1));
					pizz.setNome(result.getString(2));
					pizz.setOrarioApertura(result.getTime(3));
					pizz.setOrarioChiusura(result.getTime(4));
					pizz.setGiorniApertura(result.getString(5));
					pizz.setIndirizzo(new Indirizzo(result.getInt("civico"), result.getInt("id"), result.getString("via"), result.getString("cap"), result.getString("citta"), result.getString("utente")));
					pizz.setTitolare(PizzeriaDAOImpl.getTitolare(result.getString("titolare")));
					pizz.setIban(result.getString(8));
					pizz.setDescrizione(result.getString(9));
					pizz.setTelefono(result.getString(10));
					pizz.setProdotti(PizzeriaDAOImpl.getProdotti(result.getString(1)));
					pizzerie.add(pizz);
					System.out.println(pizz.getPartitaIva());
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
		return pizzerie;
	}

	

	@Override
	public Pizzeria getPizzeria(String titolare) {
		Connection connection = null;
		Pizzeria pizz= null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(PizzeriaDAOImpl.GET_PIZZERIA_BY_TITOLARE);
			statement.setString(1, titolare);
			result = statement.executeQuery();
			
				while(result.next()){
					
					pizz = new Pizzeria();
					pizz.setPartitaIva(result.getString(1));
					pizz.setNome(result.getString(2));
					pizz.setOrarioApertura(result.getTime(3));
					pizz.setOrarioChiusura(result.getTime(4));
					pizz.setGiorniApertura(result.getString(5));
					pizz.setTitolare(PizzeriaDAOImpl.getTitolare(titolare));
					pizz.setIban(result.getString(8));
					pizz.setDescrizione(result.getString(9));
					pizz.setTelefono(result.getString(10));
					pizz.setProdotti(PizzeriaDAOImpl.getProdotti(pizz.getPartitaIva()));
					
					pizz.setIndirizzo(PizzeriaDAOImpl.getIndirizzo(result.getInt(6)));


				}
				
			
			
		}catch (SQLException e) {
			System.out.println("Errore durante la connessionedd." + e.getMessage());
			return null;
		}finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				System.out.println("Errore durante la connessione." + e.getMessage());
			}
		}
		return pizz;
	}

	@Override
	public Set<Pizzeria> getPizzerie() {
		Connection connection = null;
		Set<Pizzeria> pizzerie = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(PizzeriaDAOImpl.GET_ALL);
			
			ResultSet result = statement.executeQuery();
				if(result != null) {
					pizzerie = new HashSet<>();
					while(result.next()) {
						Pizzeria pizz = new Pizzeria();
						pizz.setPartitaIva(result.getString(1));
						pizz.setNome(result.getString(2));
						pizz.setOrarioApertura(result.getTime(3));
						pizz.setOrarioChiusura(result.getTime(4));
						pizz.setGiorniApertura(result.getString(5));
						pizz.setIndirizzo(new Indirizzo(result.getInt("civico"), result.getInt("id"), result.getString("via"), result.getString("cap"), result.getString("citta"), result.getString("utente")));
						pizz.setTitolare(PizzeriaDAOImpl.getTitolare(result.getString("titolare")));
						pizz.setIban(result.getString(8));
						pizz.setDescrizione(result.getString(9));
						pizz.setTelefono(result.getString(10));
						pizz.setProdotti(PizzeriaDAOImpl.getProdotti(result.getString(1)));
						pizzerie.add(pizz);
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
		return pizzerie;
	}
	public Collection<Utente> getFattorini(String idPizzeria){
		ResultSet result = null;
		Set<Utente> set = null;
		Connection connection = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(PizzeriaDAOImpl.GET_FATTORINI);
			statement.setString(1, idPizzeria);
			result = statement.executeQuery();
			set = new HashSet<Utente>();
			while(result.next()) {
				Utente u = new Utente();
				u.setNome(result.getString(1));
				u.setCognome(result.getString(2));
				u.setEmail(result.getString(3));
				u.setTelefono(result.getString(5));
				set.add(u);
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
	private static Utente getTitolare(String email) {
		ResultSet result=null;
		Connection connection =null;
		Utente utente = null;
		try {				
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from utente where email=?");
			preparedStatement.setString(1, email);
			
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
				
			}
			
		}catch (Exception e) {
			System.out.println("Errore durante la connessioneee." + e.getMessage());

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
	
	private static Set<Prodotto> getProdotti(String partitaIva) {
		ResultSet result=null;
		Connection connection =null;
		Set<Prodotto> set =null;
		ArrayList<Categoria> categorie = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from categoria");
			result = preparedStatement.executeQuery();
			if(result != null) {
				categorie = new ArrayList<Categoria>();
				while(result.next()) {
					Categoria c = new Categoria();
					c.setNome(result.getString(1));
					c.setIva(result.getInt(2));
					categorie.add(c);
				}
			}
	
			preparedStatement = connection.prepareStatement("select * from prodotto where id_pizzeria = ?");
			preparedStatement.setString(1, partitaIva);
			
			result = preparedStatement.executeQuery();
			if(result != null && categorie!=null) {
				set = new HashSet<Prodotto>();
				while(result.next()) {
					Prodotto prodotto = new Prodotto();
					prodotto.setNome(result.getString(1));
					prodotto.setPrezzo(result.getFloat(2));
					prodotto.setDisponibilita(result.getString(3));
					String cat = result.getString(4);
					for (Categoria categoria : categorie) {
						if (categoria.getNome().equals(cat)) {
							prodotto.setCategoria(categoria);
						}
					}
					set.add(prodotto);
				}				
			}
		}catch (Exception e) {
			System.out.println("Errore durante la connessioneiii." + e.getMessage());

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
	@Override
	public Pizzeria getPizzeriaByIva(String iva) {
		
			Connection connection = null;
			Pizzeria pizz= null;
			ResultSet result = null;
			try {
				connection = DriverManagerConnectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(PizzeriaDAOImpl.GET_PIZZERIA_BY_IVA);
				statement.setString(1, iva);
				result = statement.executeQuery();
				
					while(result.next()){
						
						pizz = new Pizzeria();
						pizz.setPartitaIva(iva);
						pizz.setNome(result.getString(2));
						pizz.setOrarioApertura(result.getTime(3));
						pizz.setOrarioChiusura(result.getTime(4));
						pizz.setGiorniApertura(result.getString(5));
						pizz.setIndirizzo(PizzeriaDAOImpl.getIndirizzo(result.getInt(6)));
						pizz.setTitolare(PizzeriaDAOImpl.getTitolare(result.getString(7)));
						pizz.setIban(result.getString(8));
						pizz.setDescrizione(result.getString(9));
						pizz.setTelefono(result.getString(10));
						pizz.setProdotti(PizzeriaDAOImpl.getProdotti(pizz.getPartitaIva()));
						
						


					}
					
				
				
			}catch (SQLException e) {
				System.out.println("Errore durante la connessionedd." + e.getMessage());
				return null;
			}finally {
				try {
					DriverManagerConnectionPool.releaseConnection(connection);
				} catch (SQLException e) {
					System.out.println("Errore durante la connessioneiii." + e.getMessage());
				}
			}
			return pizz;
		}

	
}
