package controller.GestoreUtenti;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.util.HTTPInputSource;

import model.beans.Carta;
import model.beans.Cliente;
import model.beans.Indirizzo;
import model.beans.Utente;
import model.daoFactory.CartaDAOFactory;
import model.daoFactory.IndirizzoDAOFactory;
import model.daoFactory.UtenteDAOFactory;
import model.daoImplementation.UtenteDAOImpl;

/**
 * Servlet implementation class ImpostazioniServlet
 */
@WebServlet("/ImpostazioniServlet")
public class ImpostazioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImpostazioniServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("method") != null && request.getParameter("method").equals("aggiornaDati")) {
			
			String email = request.getParameter("email");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String telefono = request.getParameter("telefono");
			if (email.equals("") || nome.equals("")|| cognome.equals("") || telefono.equals("")) {
				
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);	
			} else {
				Utente utente = (Utente)request.getSession().getAttribute("utente");
				String vecchiaEmail = utente.getEmail();
				utente.setCognome(cognome); utente.setNome(nome); utente.setTelefono(telefono); utente.setEmail(email);
				if(UtenteDAOFactory.getUtenteDAO().modificaDati(utente, vecchiaEmail) != null) {
							
					request.getSession().setAttribute("utente", utente);
					response.setStatus(HttpServletResponse.SC_OK);
				}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);;
				}
			}
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("cambiaPassword")) {
			boolean flag = true;
			String vecchia = request.getParameter("vecchiaPassword");
			String nuova = request.getParameter("nuovaPassword");
			String confermaNuova = request.getParameter("confermaNuovaPassword");
			Utente utente = (Utente) request.getSession().getAttribute("utente");
			
			if (!(utente.getPassowrd().equals(vecchia))) flag = false;
			if (!(nuova.equals(confermaNuova))) flag = false;
			if (flag) {
				if(UtenteDAOFactory.getUtenteDAO().modificaPassword(utente.getEmail(), nuova) != null) {
					utente.setPassword(nuova);
					utente.setConfermaPassword(nuova);
					request.getSession().setAttribute("utente", utente);
					response.setStatus(HttpServletResponse.SC_OK);
					
				}
				
			} else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		
		
		
		
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("aggiungiIndirizzo")) {
			Gson gson = new Gson();
			Cliente cliente = (Cliente)request.getSession().getAttribute("utente");
			Indirizzo indirizzo = gson.fromJson(request.getParameter("indirizzo"), Indirizzo.class);
			indirizzo.setIdCliente(cliente.getEmail());
			Indirizzo nuovo =IndirizzoDAOFactory.getIndirizzoDAO().inserisciIndirizzo(indirizzo);
			if(nuovo != null) {
								
				cliente.setIndirizzi((Set) IndirizzoDAOFactory.getIndirizzoDAO().getIndirizziByCliente(cliente.getEmail()));
				if(cliente.getIndirizzi()!=null) {
				request.getSession().setAttribute("utente", cliente);
				response.setStatus(HttpServletResponse.SC_OK);
				}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}

			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			
			
		
		
		
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("aggiungiCarta")) {
			Gson gson = new Gson();
			Carta carta = gson.fromJson(request.getParameter("carta"), Carta.class);
			Cliente cliente = (Cliente) request.getSession().getAttribute("utente");
			carta.setUtente(cliente);
			if(CartaDAOFactory.getCartaDAO().inserisciCarta(carta,cliente.getEmail()) != null) {
				
				Set<Carta> set = cliente.getCarte();
				set.add(carta);
				cliente.setCarte(set);
				request.getSession().setAttribute("utente", cliente);

				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("rimuoviCarta")) {
				String numeroCarta = request.getParameter("numero");
				boolean flag = false;
				Cliente cliente = (Cliente) request.getSession().getAttribute("utente");
				Set<Carta> set = cliente.getCarte();
				Carta carta = null;
				Iterator<Carta> r =set.iterator();
				while(r.hasNext()) {
					carta = r.next();
					if(carta.getNumeroCarta().equals(numeroCarta)) {
						if(CartaDAOFactory.getCartaDAO().rimuoviCarta(carta)){
							set.remove(carta);
							cliente.setCarte(set);
							request.getSession().setAttribute("utente", cliente);
							flag = true;
							break;
						}
						
					}
				}
				if(flag) {
					response.setStatus(HttpServletResponse.SC_OK);
				}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
				
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("rimuoviIndirizzo")) {
					int id = Integer.parseInt(request.getParameter("numero"));
					boolean flag = false;
					Cliente cliente = (Cliente) request.getSession().getAttribute("utente");
					Set<Indirizzo> set = cliente.getIndirizzi();
					Indirizzo indirizzo = null;
					Iterator<Indirizzo> r =set.iterator();
					while(r.hasNext()) {
						indirizzo = r.next();
						if(indirizzo.getIdIndirizzo()==id) {
							if(IndirizzoDAOFactory.getIndirizzoDAO().eliminaIndirizzo(indirizzo)){
								set.remove(indirizzo);
								cliente.setIndirizzi(set);
								request.getSession().setAttribute("utente", cliente);
								flag = true;
								break;
							}
							
						}
					}
					if(flag) {
						response.setStatus(HttpServletResponse.SC_OK);
					}else {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					}
				
		}else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	
	

	}

}
