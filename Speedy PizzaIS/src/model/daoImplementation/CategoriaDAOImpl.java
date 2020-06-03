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
import model.beans.Categoria;
import model.daoInterface.CategoriaDAO;

public class CategoriaDAOImpl implements CategoriaDAO {
	public static final String GET_ALL="select * from categoria";
	public static final String GET_BY_NAME="select * from categoria where nome=?";
	public static final String GET_BY_PIZZERIA="SELECT id_categoria from speedypizza.prodotto where id_pizzeria = ? group by id_categoria";
	@Override
	public synchronized Collection<Categoria> getCategorie() {
		Connection connection = null;
		Set<Categoria> set = new HashSet<Categoria>();
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(CategoriaDAOImpl.GET_ALL);
			result = statement.executeQuery();
			if(result != null) {
				
				while(result.next()) {
					set.add(new Categoria(result.getString(1), result.getInt(2)));
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

	@Override
	public Categoria getCategoria(String nome) {
		
		Connection connection = null;
		Categoria categoria = null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(CategoriaDAOImpl.GET_BY_NAME);
			statement.setString(1, nome);
			result = statement.executeQuery();
			if(result != null) {
				
				while(result.next()) {
					categoria = new Categoria();
					categoria.setNome(result.getString(1));
					categoria.setIva(result.getInt(2));
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
		return categoria;
	}

	@Override
	public ArrayList<String> getCategorieByPizzeria(String partitaIva) {
		Connection connection = null;
		ArrayList<String> set = null;
		ResultSet result = null;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(CategoriaDAOImpl.GET_BY_PIZZERIA);
			statement.setString(1, partitaIva);
			result = statement.executeQuery();
			
				set = new ArrayList<String>();
				while(result.next()) {
					
					set.add(result.getString(1));
				
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


