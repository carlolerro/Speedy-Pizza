	package controller.GestorePizzerie;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import model.beans.Indirizzo;
import model.beans.Pizzeria;
import model.beans.Utente;
import model.daoFactory.IndirizzoDAOFactory;
import model.daoFactory.PizzeriaDAOFactory;

/**
 * Servlet implementation class InformazioniServlet
 */
@WebServlet("/InformazioniServlet")
public class InformazioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformazioniServlet() {
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
		if (request.getParameter("method") != null && request.getParameter("method").equals("aggiornaImpostazioni")) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Time.class, new TimeDeserializer());
			Gson gson = gsonBuilder.create();
			Pizzeria pizz = gson.fromJson(request.getParameter("pizzeria"), Pizzeria.class);
			Pizzeria vecchiaPizzeria = (Pizzeria) request.getSession().getAttribute("pizzeria");
			String vecchiaPartita = vecchiaPizzeria.getPartitaIva();
			vecchiaPizzeria.setPartitaIva(pizz.getPartitaIva());
			vecchiaPizzeria.setNome(pizz.getNome());
			vecchiaPizzeria.setOrarioApertura(pizz.getOrarioApertura());
			vecchiaPizzeria.setOrarioChiusura(pizz.getOrarioChiusura());
			vecchiaPizzeria.setGiorniApertura(pizz.getGiorniApertura());
			vecchiaPizzeria.setIban(pizz.getIban());
			vecchiaPizzeria.setIndirizzo(pizz.getIndirizzo());
		
			Pizzeria pizzeria = PizzeriaDAOFactory.getPizzeriaDAO().updatePizzeria(vecchiaPizzeria,vecchiaPartita);
			if(pizzeria !=null) {
				request.getSession().setAttribute("pizzeria", pizzeria);
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("getPizzerieByCitta")) {
			String citta = request.getParameter("citta");
			ArrayList<Pizzeria> pizzerie = (ArrayList<Pizzeria>) PizzeriaDAOFactory.getPizzeriaDAO().getPizzerieByCitta(citta);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(pizzerie));
			response.setStatus(HttpServletResponse.SC_OK);
		}else if (request.getParameter("method") != null && request.getParameter("method").equals("getAllCity")) {
			ArrayList<String> citta = (ArrayList<String>) IndirizzoDAOFactory.getIndirizzoDAO().getCitta();
			Gson gson = new Gson();
			String allCitta = gson.toJson(citta);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(allCitta);
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		
	}
	private class TimeDeserializer implements JsonDeserializer<Time> {

        

		@Override
		public Time deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
				throws JsonParseException {
			try {

                String s = arg0.getAsString();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
                sdf.parse(s);
                long ms = sdf.parse(s).getTime();
                Time t = new Time(ms);
                return t;
            } catch (ParseException e) {
            }
        throw new JsonParseException("Unparseable time: \"" + arg0.getAsString()
                + "\". Supported formats: " + "HH:mm");
		}
    }
	}


