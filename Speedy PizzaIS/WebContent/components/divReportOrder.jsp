
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.beans.Prodotto"%>
<%@page import="model.beans.Carrello"%>
<%@page import="model.beans.Pizzeria"%>
<%@page import="model.beans.Ordine"%>
<%@page import="java.util.Set"%>
<%@page import="model.daoFactory.OrdineDAOFactory"%>
<%@page import="model.beans.Utente"%>
<%@page import="model.beans.Cliente"%>
<%@page import="java.util.ArrayList" %>

<%@page import="java.time.LocalDateTime" %>
<%@page import="java.time.Instant" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="java.time.ZoneId" %>
<% 
Cliente x = new Cliente();
if (request.getSession().getAttribute("utente") != null) {
	x = (Cliente) request.getSession().getAttribute("utente");
	if(x.getTipo()!=0){
		response.sendRedirect("../index.jsp");
	}
} else {
	response.sendRedirect("../index.jsp");
}
Set<Ordine> setOrdini = (Set<Ordine>)OrdineDAOFactory.getOrdineDAO().getOrdiniByCliente(x.getEmail());
%>
<%
String toPrint = "";
for (Ordine i : setOrdini) {

	 LocalDateTime date = Instant.ofEpochMilli(i.getData().getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	 String dataafin = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	 	Pizzeria pizzeria = i.getPizzeria();
	 	Carrello carrello = i.getCarrello();
%>

<div class="row w-100" style="padding-bottom: 2vh;">
<div class="col-md-11 <%=i.getId() %>" style="padding-top: 2vh;" data-toggle="collapse" href="#div<%=i.getId()%>" role="button" aria-expanded="false" aria-controls="collapseExample">
	<div class="card card-interna">
		<div class="card-body w-100">
			<span style="color: green;">
				<%=pizzeria.getNome() %> - n. <%=i.getId() %> del <%=dataafin %>
				Tracker: <%=i.getTracking() %>
				</span>
			<div class="collapse row" id="div<%=i.getId()%>">
			<div class="row col-xl-12" style="color: green;">
		 			<div class="col-md-4"> <p>Nome</p></div>
					<div class="col-md-4"> <p>Categoria</p></div>
					<div class="col-md-2"> <p>Quantit&agrave;</p></div>
					<div class="col-md-2"> <p>&euro; Unitario.</p></div>				
				</div>
					<%
		String ref="";
		
		Iterator<Map.Entry<Prodotto, Integer>> itr = carrello.getProdotti().entrySet().iterator();
		System.out.print(carrello.getProdotti());
		while(itr.hasNext()) {
			
			Map.Entry<Prodotto, Integer> entry = itr.next();
			
			%>
				<div class="row col-xl-12">
		 			<div class="col-md-4"> <p><%=entry.getKey().getNome() %></p></div>
					<div class="col-md-4"> <p><%=entry.getKey().getCategoria().getNome()%></p></div>
					<div class="col-md-2"> <p><%=entry.getValue() %> pz.</p></div>
					<div class="col-md-2"> <p>&euro;  <%=entry.getKey().getPrezzo()*entry.getValue() %></p></div>				
				</div>
		
		<%} %>

</div>
		</div>
	</div>
</div>
<div class="col-md-1" style="padding-top: 2vh;" onclick="showReview(<%=i.getId()%>, '<%=i.getPizzeria().getPartitaIva()%>')">
	<i class="material-icons">textsms</i>
	

</div>
<% } %>
</div>


<div class="w-100 mb-5"></div>