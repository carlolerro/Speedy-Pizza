package controller.GestorePizzerie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.Categoria;
import model.beans.Pizzeria;
import model.beans.Prodotto;
import model.daoFactory.CategoriaDAOFactory;
import model.daoFactory.PizzeriaDAOFactory;
import model.daoFactory.ProdottoDAOFactory;

/**
 * Servlet implementation class CatalogoServlet
 */
@WebServlet("/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogoServlet() {
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
		if (request.getParameter("method") != null && request.getParameter("method").equals("eliminaProdotto")) {
			String nome = request.getParameter("nome");
			Pizzeria pizzeria = (Pizzeria) request.getSession().getAttribute("pizzeria");
			Set<Prodotto> prodotti = pizzeria.getProdotti();
			Prodotto daEliminare = null;
			for(Prodotto p : prodotti) {
				if(p.getNome().equals(nome)) {
					daEliminare = p;
				}
			}
			if (daEliminare != null) {
				if(ProdottoDAOFactory.getProdottoDAO().eliminaProdotto(daEliminare, pizzeria)) {
					prodotti.remove(daEliminare);
					pizzeria.setProdotti(prodotti);
					request.setAttribute("pizzeria", pizzeria);
					response.setStatus(HttpServletResponse.SC_OK);
				}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			

			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("aggiungiProdotto")) {
			
			Gson gson = new Gson();
			Pizzeria pizzeria =(Pizzeria) request.getSession().getAttribute("pizzeria");
			Prodotto nuovoProdtto = gson.fromJson(request.getParameter("prodotto"), Prodotto.class);
			Categoria categoria = gson.fromJson(request.getParameter("categoria"), Categoria.class);
			nuovoProdtto.setCategoria(categoria);
			
			
			nuovoProdtto.setIdPizzeria(pizzeria.getPartitaIva());
			if(ProdottoDAOFactory.getProdottoDAO().inserisciProdotto(nuovoProdtto)!=null) {
				Set<Prodotto> set = pizzeria.getProdotti();
				set.add(nuovoProdtto);
				pizzeria.setProdotti(set);
				request.getSession().setAttribute("pizzeria", pizzeria);
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("aggiornaProdotto")) {
			String old = request.getParameter("vecchio");
			Gson gson = new Gson();
			Pizzeria pizzeria =(Pizzeria) request.getSession().getAttribute("pizzeria");
			
			Prodotto nuovoProdotto = gson.fromJson(request.getParameter("prodotto"), Prodotto.class);
			Categoria categoria = gson.fromJson(request.getParameter("categoria"), Categoria.class);
			nuovoProdotto.setCategoria(categoria);		
			nuovoProdotto.setIdPizzeria(pizzeria.getPartitaIva());
			
			
				if(ProdottoDAOFactory.getProdottoDAO().modificaProdotto(nuovoProdotto, old)!=null) {
					Set<Prodotto> set = pizzeria.getProdotti();
					set.add(nuovoProdotto);
					pizzeria.setProdotti(set);
					request.getSession().setAttribute("pizzeria", pizzeria);
					response.setStatus(HttpServletResponse.SC_OK);
				}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("getCategorieRistorante")) {
			String partitaIva = request.getParameter("partita");
			if(request.getSession().getAttribute("ristoranteScelto") == null) {
				
				request.getSession().setAttribute("ristoranteScelto", partitaIva);
			} else {
				String res = (String) request.getSession().getAttribute("ristoranteScelto");
				if (!(res.equals(partitaIva))) {
					request.getSession().setAttribute("ristoranteScelto", partitaIva);
					request.getSession().removeAttribute("cart");
				}
			}
			ArrayList<String> categorie = CategoriaDAOFactory.getCategoriaDAO().getCategorieByPizzeria(partitaIva);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(categorie));
			response.setStatus(HttpServletResponse.SC_OK);
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("getProdottiCategoria")) {
			String partitaIva =(String) request.getSession().getAttribute("ristoranteScelto");
			String categoria = request.getParameter("categoria");
			ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>)ProdottoDAOFactory.getProdottoDAO().getProdottiByCategoria(partitaIva, categoria);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(prodotti));
			
			response.setStatus(HttpServletResponse.SC_OK);

		}else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
