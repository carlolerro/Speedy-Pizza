package controller.GestoreOrdini;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.Recensione;
import model.daoFactory.RecensioneDAOFactory;

/**
 * Servlet implementation class RecensioneServlet
 */
@WebServlet("/RecensioneServlet")
public class RecensioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecensioneServlet() {
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
	if(request.getParameter("method") != null && request.getParameter("method").equals("getDetailReview")) {
		int idOrdine = Integer.parseInt(request.getParameter("ordine"));
		Recensione r = RecensioneDAOFactory.getRecensioneDAO().getRecensione(idOrdine);
		System.out.println(new Gson().toJson(r));
		if(r!=null) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(r));
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}else if(request.getParameter("method") != null && request.getParameter("method").equals("sendReview")) {
		int idOrdine = Integer.parseInt(request.getParameter("ordine"));
		String commento = (String) request.getParameter("commento");
		int stelle = Integer.parseInt(request.getParameter("stelle"));
		Recensione r = new Recensione(commento, stelle, idOrdine, new Date(System.currentTimeMillis()));
		if(RecensioneDAOFactory.getRecensioneDAO().inserisciRecensione(idOrdine, r) !=null){
			response.setStatus(HttpServletResponse.SC_OK);
			
		}else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	}
}
