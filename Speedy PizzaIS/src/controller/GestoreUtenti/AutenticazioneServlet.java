package controller.GestoreUtenti;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Cliente;
import model.beans.Ordine;
import model.beans.Pizzeria;
import model.beans.Utente;
import model.daoFactory.OrdineDAOFactory;
import model.daoFactory.PizzeriaDAOFactory;
import model.daoFactory.UtenteDAOFactory;

/**
 * Servlet implementation class AutenticazioneServlet
 */
@WebServlet("/AutenticazioneServlet")
public class AutenticazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutenticazioneServlet() {
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
	/*
	 * tipo = 0 cliente
	 * tipo = 1 ristoratore
	 * tipo = 2 fattorino
	 * tipo = 3 gestore utenti
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("method") != null && request.getParameter("method").equals("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Utente utente= (Utente) UtenteDAOFactory.getUtenteDAO().autenticaUtente(email, password);
			
			if (utente != null) {	
				
				if (utente.getTipo() == 1) {
					Pizzeria pizzeria = PizzeriaDAOFactory.getPizzeriaDAO().getPizzeria(utente.getEmail());
					
					if(pizzeria != null) {
						request.getSession().setAttribute("utente", utente);
						request.getSession().setAttribute("pizzeria", pizzeria);
						response.setStatus(HttpServletResponse.SC_OK);
						response.getWriter().write("pizzeria");

						
					}else {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					}
				}else if(utente.getTipo()==0) {
					
					request.getSession().setAttribute("utente", utente);
					response.setStatus(HttpServletResponse.SC_OK);
					response.getWriter().write("cliente");
					
				}else if(utente.getTipo()==2) {
					request.getSession().setAttribute("utente", utente);
					response.setStatus(HttpServletResponse.SC_OK);
					response.getWriter().write("fattorino");
					
				}else if(utente.getTipo()==3) {
					request.getSession().setAttribute("utente", utente);
					response.setStatus(HttpServletResponse.SC_OK);
					response.getWriter().write("gestore");
				}
				

			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("logout")) {
			request.getSession().invalidate();
			
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
			
	}

}
