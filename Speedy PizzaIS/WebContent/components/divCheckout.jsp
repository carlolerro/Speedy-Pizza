
	<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="model.beans.Cliente"%>
<%@page import="model.beans.Utente" %>
	<%@page import="model.beans.Carta" %>
	
	<%@page import="model.beans.Indirizzo" %>

	<%@page import="java.util.ArrayList" %>
<div id="divCheckout" class='div-tracker w-100' style="padding-top: 3vh; padding-bottom: 3vh;">
	<%
	
	if (request.getSession().getAttribute("utente") != null) {
		
		Cliente x = (Cliente) request.getSession().getAttribute("utente");
		if(x.getTipo() !=0){ response.sendRedirect("index.jsp");}
		%> <h2>Bentornato <%=x.getNome()%> <%=x.getCognome() %></h2>
		<h3>A quale indirizzo consegniamo?</h3>
		<%
		Set<Indirizzo> set =(Set<Indirizzo>) x.getIndirizzi();
		if(set.isEmpty()){%>
		<div class="col-md-3" style="padding-top: 2vh;">
				<div  class="card card-interna carta-credito" onclick="showAddAddress();">
					<div class="card-body">
					Aggiungi indirizzo
						+
					</div>
					</div>
				</div>
				
			
		<%}else{
			for (Indirizzo i : set) {
			%> 
			<div class="col-md-3" style="padding-top: 2vh;">
				<div id="card-address-<%=i.getIdIndirizzo() %>" class="card card-interna indirizzo" onclick="setAddress(<%=i.getIdIndirizzo()%>)">
					<div class="card-body">
						<%=i.getCitta()%>, <%=i.getVia() %>
					</div>
				</div>
			</div>
		<%}
		}
		%>
		Se non selezioni alcun indirizzo, è sottointeso il ritiro in pizzeria.
		<h3>Come vuoi pagare?</h3>
		<%
		Set<Carta> carteSet = x.getCarte();
		if(carteSet.isEmpty()){%>
		<div class="col-md-3" style="padding-top: 2vh;">
				<div  class="card card-interna carta-credito" onclick="showAddPaymentMethod();">
					
					<div class="card-body">
					Aggiungi carta
						+
					</div>
					</div>
				</div>
			
		<%}else{
			for (Carta i : carteSet) {
			%> 
			<div class="col-md-3" style="padding-top: 2vh;">
				<div id="card-card-<%=i.getNumeroCarta()%>" class="card card-interna carta-credito" onclick="setPaymentMethod(<%=i.getNumeroCarta()%>)">
					<div class="card-body">
						<%=i.getNumeroCarta()%>
					</div>
				</div>
			</div>
		<%}
		}%>
	<button onclick="confermaOrdine();" class='btn btn-outline-warning mb-3 mt-5 is-invalid' style="width: 80vw; font-size: 1.2em;">Conferma ordine</button>
	<div id="cont-p-invalid" style="opacity: 0; transition: all 0.5s;"><p style="color: red;  id="p-invalid">Completare tutti i dati prima di procedere.</p></div>
		<% } else { %>
	<button onclick="openAccount('yes');" class='btn btn-outline-warning mb-3 mt-3' style="width: 80vw; font-size: 1.2em;">Accedi</button>
	<% } %>
</div>
