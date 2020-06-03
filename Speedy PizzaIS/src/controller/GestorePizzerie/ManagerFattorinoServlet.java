package controller.GestorePizzerie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.Pizzeria;
import model.beans.Utente;
import model.daoFactory.UtenteDAOFactory;

/**
 * Servlet implementation class ManagerFattorinoServlet
 */
@WebServlet("/ManagerFattorinoServlet")
public class ManagerFattorinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerFattorinoServlet() {
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
		if (request.getParameter("method") != null && request.getParameter("method").equals("aggiornaFattorino")) {
			Gson gson = new Gson();
			Utente fattorino = gson.fromJson(request.getParameter("fattorino"), Utente.class);
			String oldEmail = request.getParameter("vecchio");
			if(UtenteDAOFactory.getUtenteDAO().modificaDati(fattorino, oldEmail)!=null) {
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("eliminaFattorino")) {
			String email = request.getParameter("email");
			
			if(UtenteDAOFactory.getUtenteDAO().eliminaUtente(email)) {
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("aggiungiFattorino")) {
			String partitaIva = "";
			
			Pizzeria pizzeria = (Pizzeria)request.getSession().getAttribute("pizzeria");
			
			if(pizzeria == null) {
				partitaIva = request.getParameter("pizzeria");
			}else {
				partitaIva = pizzeria.getPartitaIva();
			}
			
			Gson gson = new Gson();
			Utente utente = gson.fromJson(request.getParameter("fattorino"), Utente.class);
			utente.setPizzeriaFattorino(partitaIva);
			
			if(UtenteDAOFactory.getUtenteDAO().inserisciUtente(utente)!=null) {
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

}
