package controller.GestoreOrdini;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.Carrello;
import model.beans.Prodotto;
import model.daoFactory.ProdottoDAOFactory;

/**
 * Servlet implementation class CarrelloServlet
 */
@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrelloServlet() {
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
		if (request.getParameter("method") != null && request.getParameter("method").equals("addProdotto")) {
			Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
			String nomeProdotto = request.getParameter("nomeProdotto");
			String partitaIva = (String)request.getSession().getAttribute("ristoranteScelto");
			
			Prodotto p = (Prodotto) ProdottoDAOFactory.getProdottoDAO().getProdotto(partitaIva, nomeProdotto);
			int tot =0;
			if(p != null) {
				if(cart == null) {
					cart = new Carrello();
					HashMap<Prodotto, Integer> map = new HashMap<>();
					map.put(p, 1);
					cart.setProdotti(map);
					
				}else {
					if(cart.getProdotti().containsKey(p)) {
						int x = cart.getProdotti().get(p)+1;
						cart.getProdotti().remove(p);
						cart.getProdotti().put(p, x);
						cart.setTotale(cart.getTotale()+p.getPrezzo());
					}else {
						cart.getProdotti().put(p, 1);
					}
				}
				for(int i :cart.getProdotti().values()) {
					tot+=i;
				}
				request.getSession().setAttribute("carrello", cart);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				response.getWriter().write(new Gson().toJson(tot));			
				response.setStatus(HttpServletResponse.SC_OK);
			}
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("decreaseProdotto")) {
				Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
				String nomeProdotto = request.getParameter("nomeProdotto");
				String partitaIva = (String)request.getSession().getAttribute("ristoranteScelto");
				
				Prodotto p = (Prodotto) ProdottoDAOFactory.getProdottoDAO().getProdotto(partitaIva, nomeProdotto);
				int tot =0;
				if(p != null) {

					int x = cart.getProdotti().get(p);
					cart.getProdotti().remove(p);
					if(x >1) {
					x -=1;
					cart.getProdotti().put(p, x);
					cart.setTotale(cart.getTotale()-p.getPrezzo());

					}
				for(int i :cart.getProdotti().values()) {
					tot+=i;
				}
				request.getSession().setAttribute("numProdotti", tot);
				request.getSession().setAttribute("carrello", cart);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(new Gson().toJson(tot));			
				response.setStatus(HttpServletResponse.SC_OK);
				}
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("deleteProduct")) {
					Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
					String nomeProdotto = request.getParameter("nomeProdotto");
					String partitaIva = (String)request.getSession().getAttribute("ristoranteScelto");
					
					Prodotto p = (Prodotto) ProdottoDAOFactory.getProdottoDAO().getProdotto(partitaIva, nomeProdotto);
					cart.setTotale(cart.getTotale()-(p.getPrezzo()*cart.getProdotti().get(p)));
					cart.getProdotti().remove(p);
					

					int tot=0;
					for(int i :cart.getProdotti().values()) {
						tot+=i;
					}
					request.getSession().setAttribute("numProdotti", tot);
					request.getSession().setAttribute("carrello", cart);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(new Gson().toJson(tot));			
					response.setStatus(HttpServletResponse.SC_OK);
				
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("showCartProduct")) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(request.getSession().getAttribute("carrello")));			
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

			
	}

}
