
<%@page import="model.daoFactory.RichiestaAffiliazioneDAOFactory"%>
<%@page import="model.beans.RichiestaAffiliazione"%>
<%@page import="model.daoFactory.UtenteDAOFactory"%>
<%@page import="model.daoFactory.PizzeriaDAOFactory"%>
<%@page import="model.beans.Pizzeria"%>
<%@page import="model.beans.Utente"%>
<%@page import="java.util.Set"%>
<% 
	Set<RichiestaAffiliazione> richieste = (Set<RichiestaAffiliazione>)RichiestaAffiliazioneDAOFactory.getRichiestaAffiliazioneDAO().getRichieste();
	request.getSession().setAttribute("richieste", richieste);
%>
<div class="row product-row first-row-cat">
		<div class="col-2 col-md-2 col-lg-2"> <p>Nome Pizzeria</p></div>
		<div class="col-2 col-md-1 col-lg-2">Nome Ristoratore</div>
		<div class="col-2 col-md-2 col-lg-2">Cognome Ristoratore</div>
		<div class="col-2 col-md-2 col-lg-2">Indirizzo</div>
		<div class="col-2 col-md-2 col-lg-2">Stato</div>
		
</div>
<%for(RichiestaAffiliazione u: richieste){ 
	
	%>
<div class="row product-row nomeProdotto">
		<div class="col-2 col-md-2 col-lg-2" style=text-align:center> <p><%=u.getNomePizzeria() %></p></div>
		<div class="col-1 col-md-1 col-lg-2" style="text-align:center"><p><%=u.getNome()%></p></div>
		<div class="col-2 col-md-2 col-lg-2" style="text-align:center"><p><%=u.getCognome() %></p></div>
		<div class="col-2 col-md-2 col-lg-2" style="text-align:center"><p>DA INSERIRE</p></div>
		<div class="col-2 col-md-1 col-lg-2" style="text-align:center"><p><%=u.getStato() %></p></div>
		<%if(!u.getStato().equals("Accettata")){ %>
		<div class="col-2 col-md-2 col-lg-2" style="text-align:center">
			<i onClick="accettaRichiesta('<%=u.getId()%>')" class="material-icons" id="accept-icon" style="margin-top:10px;">done</i>
			
			<i onClick="rifiutaRichiesta('<%=u.getId()%>')" class="material-icons" id="remove-icon" style="margin-top:10px;">delete</i>
		</div>
	</div>
	<%}} %>
	