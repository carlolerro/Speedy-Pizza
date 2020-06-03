package model.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.sun.swing.internal.plaf.synth.resources.synth;

import model.DriverManagerConnectionPool;
import model.beans.Pizzeria;
import model.beans.Prodotto;
import model.daoInterface.ProdottoDAO;

public class ProdottoDAOImpl implements ProdottoDAO {
	private static final String INSERT="insert into prodotto values(?,?,?,?,?,?)";
	private static final String GET_BY_PIZZERIA="select * from prodotto where id_pizzeria=?";
	private static final String GET_BY_CATEGORIA="select * from prodotto where id_categoria=? and id_pizzeria = ?";
	private static final String DELETE="delete from prodotto where nome = ? and id_pizzeria = ?";
	private static final String UPDATE="update prodotto set nome = ?, prezzo = ?, disponibilita = ?, id_categoria = ?, ingredienti = ?, id_pizzeria = ? where nome = ? and id_pizzeria = ?";
	private static final String GET="select * from prodotto where nome = ? and id_pizzeria = ?";

	@Override
	public synchronized Prodotto inserisciProdotto(Prodotto prodotto) {
		Connection connection = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(ProdottoDAOImpl.INSERT);
			statement.setString(1, prodotto.getNome());
			statement.setFloat(2, prodotto.getPrezzo());
			statement.setString(3,prodotto.getDisponibilita());
			statement.setString(4, prodotto.getCategoria().getNome());
			statement.setString(5, prodotto.getIngredienti());
			statement.setString(6, prodotto.getIdPizzeria());


			return (statement.executeUpdate()>0) ? prodotto:null;


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
	public synchronized boolean eliminaProdotto(Prodotto prodotto, Pizzeria pizzeria) {
		Connection connection = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(ProdottoDAOImpl.DELETE);
			statement.setString(1, prodotto.getNome());
			statement.setString(2, pizzeria.getPartitaIva());
			
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
	public synchronized Prodotto modificaProdotto(Prodotto prodotto,String nome) {
		Connection connection = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(ProdottoDAOImpl.UPDATE);
			statement.setString(1, prodotto.getNome());
			statement.setFloat(2, prodotto.getPrezzo());
			statement.setString(3,prodotto.getDisponibilita());
			statement.setString(4, prodotto.getCategoria().getNome());
			statement.setString(5, prodotto.getIngredienti());
			statement.setString(6, prodotto.getIdPizzeria());
			statement.setString(7, nome);
			statement.setString(8, prodotto.getIdPizzeria());


			return (statement.executeUpdate()>0) ? prodotto:null;


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
	public Prodotto getProdotto(String idPizzeria, String nomeProdotto) {
		Connection connection = null;
		Prodotto prodotto = null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(ProdottoDAOImpl.GET);
			statement.setString(1, nomeProdotto);
			statement.setString(2, idPizzeria);
			result = statement.executeQuery();
			while(result.next()) {
				prodotto= new Prodotto(result.getString(4), result.getString(1), result.getString(5), result.getFloat(2), result.getString(3), result.getString(6));
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
		return prodotto;
	}

	@Override
	public Set<Prodotto> getProdotti(String idPizzeria) {
		Connection connection = null;
		Set<Prodotto> prodotti = null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(ProdottoDAOImpl.GET_BY_PIZZERIA);
			
			statement.setString(1, idPizzeria);
			result = statement.executeQuery();
			if (result!=null) {
				prodotti = new HashSet<Prodotto>();
				while(result.next()) {
					prodotti.add(new Prodotto(result.getString(4), result.getString(1), result.getString(5), result.getFloat(2), result.getString(3), result.getString(6)));
			
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
		return prodotti;
	}

	@Override
	public ArrayList<Prodotto> getProdottiByCategoria(String idPizzeria, String idCategoria) {
		Connection connection = null;
		ArrayList<Prodotto> prodotti = null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(ProdottoDAOImpl.GET_BY_CATEGORIA);
			
			statement.setString(1, idCategoria);
			
			statement.setString(2, idPizzeria);
			result = statement.executeQuery();
			if (result!=null) {
				prodotti = new ArrayList<Prodotto>();
				while(result.next()) {
					Prodotto p = new Prodotto();
					p.setNome(result.getString(1));
					p.setPrezzo(result.getFloat(2));
					p.setDisponibilita(result.getString(3));
					p.setCategoria(null);
					p.setIngredienti(result.getString(5));
					p.setIdPizzeria(result.getString(6));
					prodotti.add(p);
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
		return prodotti;
	}

}
