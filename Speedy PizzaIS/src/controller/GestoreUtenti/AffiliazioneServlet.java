package controller.GestoreUtenti;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.Pizzeria;
import model.beans.RichiestaAffiliazione;
import model.beans.Utente;
import model.daoFactory.PizzeriaDAOFactory;
import model.daoFactory.RichiestaAffiliazioneDAOFactory;
import model.daoFactory.UtenteDAOFactory;

/**
 * Servlet implementation class AffiliazioneServlet
 */
@WebServlet("/AffiliazioneServlet")
public class AffiliazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffiliazioneServlet() {
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
		if (request.getParameter("method") != null && request.getParameter("method").equals("registra")) {
			Gson gson = new Gson();
			RichiestaAffiliazione richiesta = gson.fromJson(request.getParameter("richiesta"), RichiestaAffiliazione.class);
			if(RichiestaAffiliazioneDAOFactory.getRichiestaAffiliazioneDAO().inserisciRichiesta(richiesta)!= null) {
				HashSet<RichiestaAffiliazione> ri = (HashSet<RichiestaAffiliazione>) RichiestaAffiliazioneDAOFactory.getRichiestaAffiliazioneDAO().getRichieste();
				if(ri != null) {
					request.getSession().setAttribute("richieste", ri);
					response.setStatus(HttpServletResponse.SC_OK);
				}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("rifiutaRichiesta")) {
			int idRichiesta = Integer.parseInt(request.getParameter("idRichiesta"));
			if(RichiestaAffiliazioneDAOFactory.getRichiestaAffiliazioneDAO().rimuoviRichiesta(idRichiesta)) {
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("accettaRichiesta")) {
			int idRichiesta = Integer.parseInt(request.getParameter("idRichiesta"));
			RichiestaAffiliazione richiestaDaInserire = null;
			Set<RichiestaAffiliazione> richieste = (Set<RichiestaAffiliazione>) request.getSession().getAttribute("richieste");
			
			for(RichiestaAffiliazione r:richieste) {
				if(r.getId() == idRichiesta)
					richiestaDaInserire = r;
			}	
			
			Utente u = new Utente();
			u.setNome(richiestaDaInserire.getNome());
			u.setCognome(richiestaDaInserire.getCognome());
			u.setPassword(richiestaDaInserire.getPassword());
			u.setTipo(1);
			u.setPizzeriaFattorino("");
			u.setEmail(richiestaDaInserire.getEmail());
			u.setTelefono(richiestaDaInserire.getTelefono());
			Pizzeria pizzeriaDaInserire = new Pizzeria();
			pizzeriaDaInserire.setNome(richiestaDaInserire.getNomePizzeria());
			pizzeriaDaInserire.setPartitaIva(richiestaDaInserire.getPartitaIva());
			pizzeriaDaInserire.setTitolare(u);
			pizzeriaDaInserire.setTelefono(u.getTelefono());
			if(UtenteDAOFactory.getUtenteDAO().inserisciUtente(u)!=null) {
				if(PizzeriaDAOFactory.getPizzeriaDAO().inserisciPizzeriaRichiesta(pizzeriaDaInserire)!=null) {
					RichiestaAffiliazioneDAOFactory.getRichiestaAffiliazioneDAO().cambiaStato("Accettata", richiestaDaInserire.getId());
					response.setStatus(HttpServletResponse.SC_OK);
				}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			
		}else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

}
