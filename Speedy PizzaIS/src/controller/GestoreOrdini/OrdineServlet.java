package controller.GestoreOrdini;

import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.Carrello;
import model.beans.Carta;
import model.beans.Cliente;
import model.beans.Indirizzo;
import model.beans.Ordine;
import model.beans.Pizzeria;
import model.beans.Recensione;
import model.daoFactory.CartaDAOFactory;
import model.daoFactory.IndirizzoDAOFactory;
import model.daoFactory.OrdineDAOFactory;
import model.daoFactory.PizzeriaDAOFactory;
import model.daoFactory.RecensioneDAOFactory;

/**
 * Servlet implementation class OrdineServlet
 */
@WebServlet("/OrdineServlet")
public class OrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdineServlet() {
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
	if (request.getParameter("method") != null && request.getParameter("method").equals("aggiornaStato")) {
		String stato = request.getParameter("stato");
		int idOrdine = Integer.parseInt(request.getParameter("idOrdine"));
		if(OrdineDAOFactory.getOrdineDAO().updateOrdine(idOrdine, stato)) {
			response.setStatus(HttpServletResponse.SC_OK);
		}
		
	}else if(request.getParameter("method") != null && request.getParameter("method").equals("setAddress")) {
		int id = Integer.parseInt((String)request.getParameter("ind"));
		Indirizzo indirizzo = IndirizzoDAOFactory.getIndirizzoDAO().getIndirizzoById(id);
		request.getSession().setAttribute("indirizzoScelto", indirizzo);
		response.setStatus(HttpServletResponse.SC_OK);
	}else if(request.getParameter("method") != null && request.getParameter("method").equals("setPaymentMethod")) {
		String numeroCarta= (String) request.getParameter("carta");
		Carta carta = CartaDAOFactory.getCartaDAO().getCartaByNumero(numeroCarta);
		request.getSession().setAttribute("cartaScelta", carta);
		response.setStatus(HttpServletResponse.SC_OK);
	}else if(request.getParameter("method") != null && request.getParameter("method").equals("confirmOrder")) {
		Ordine  ordine = new Ordine();
		ordine.setCarta((Carta)request.getSession().getAttribute("cartaScelta"));
		ordine.setIndirizzo((Indirizzo)request.getSession().getAttribute("indirizzoScelto"));
		ordine.setCliente((Cliente)request.getSession().getAttribute("utente"));
		ordine.setCarrello((Carrello)request.getSession().getAttribute("carrello"));
		String partitaIva = (String) request.getSession().getAttribute("ristoranteScelto");
		Pizzeria pizzeria = PizzeriaDAOFactory.getPizzeriaDAO().getPizzeriaByIva(partitaIva);
		ordine.setPizzeria(pizzeria);
		
		HashSet<String> set = (HashSet<String>)OrdineDAOFactory.getOrdineDAO().getAllTracker();
		String random = "SP"+(int)(Math.random()*((999-100)+1))+1;
		while(set.contains(random)) {
			random = "SP"+(int)(Math.random()*((999-100)+1))+1;
		}
		
		ordine.setTracking(random);
		if((Carta)request.getSession().getAttribute("cartaScelta") ==null) {
			ordine.setTipoPagamento(0); //pagamento contanti
			ordine.setCarta(null);
			
		}else {ordine.setTipoPagamento(1);} //pagamento con carta
		if((Indirizzo)request.getSession().getAttribute("indirizzoScelto")==null) {
			ordine.setTipoOrdine(0); //asporto
			ordine.setIndirizzo(null);
		}else {
			ordine.setTipoOrdine(1); //consegna a casa
		}
		ordine.setData(new Date(System.currentTimeMillis()));
		ordine.setStato("Elaborazione ordine");
		ordine.setTotale(ordine.getCarrello().getTotale());
		
		if(OrdineDAOFactory.getOrdineDAO().inserisciOrdine(ordine)!=null) {
			
			request.getSession().removeAttribute("cartaScelta");
			request.getSession().removeAttribute("indirizzoScelto");
			request.getSession().removeAttribute("carrello");
			request.getSession().removeAttribute("ristoranteScelto");
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}else if(request.getParameter("method") != null && request.getParameter("method").equals("getByTracker")) {
		String tracker = (String) request.getParameter("tracker");
		Ordine o = OrdineDAOFactory.getOrdineDAO().getOrdineByTracker(tracker);
		System.out.println(new Gson().toJson(o));
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(o));
		response.setStatus(HttpServletResponse.SC_OK);
	

	}else {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}
	}
}
