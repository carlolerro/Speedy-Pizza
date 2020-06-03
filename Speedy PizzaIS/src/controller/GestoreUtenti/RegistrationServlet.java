package controller.GestoreUtenti;

import java.io.IOException;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.beans.Cliente;
import model.beans.Utente;
import model.daoFactory.UtenteDAOFactory;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        
    }

	
	protected void  doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Gson gson = new Gson();
		Utente utente =gson.fromJson((String) request.getParameter("user"), Utente.class);
		if(UtenteDAOFactory.getUtenteDAO().inserisciUtente(utente) != null) {
			Cliente cliente = (Cliente) UtenteDAOFactory.getUtenteDAO().autenticaUtente(utente.getEmail(), utente.getPassowrd());
			request.getSession().setAttribute("utente", cliente);
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}


}
