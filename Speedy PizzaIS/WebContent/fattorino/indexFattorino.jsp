<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="../style/style.css">
	<link rel="stylesheet" href="style/style.css">

	
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
	
	
	<div class="container-fluid">
	<%@ include file = "headerFattorino.jsp" %>
		<div id="contenutoPagina">
			<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="model.beans.Prodotto"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.beans.Carrello"%>
<%@page import="model.daoFactory.OrdineDAOFactory"%>
<%@page import="model.beans.Ordine"%>
<%@page import="java.util.Set"%>
<%@page import="model.beans.Pizzeria"%>
<div class="row mb-3 align-center w-100 ">
<div class="col-6">
	<%
	String what = "";
	if (request.getParameter("show") != null && request.getParameter("show").equals("sospesi")) {
		what = "sospesi";%>
		Mostra: <select id="whatShow"><option value="tutti">tutti</option><option value="sospesi" selected>sospesi</option></select>
	<%} else if ((request.getParameter("show") != null && request.getParameter("show").equals("tutti")) || request.getParameter("show") == null) { 
		what = "tutti";%>
		Mostra: <select id="whatShow"><option value="tutti" selected>tutti</option><option value="sospesi" >sospesi</option></select>	
	<%} else {
		 response.sendRedirect("../indexPizzeria.jsp");
	}
	%>
	
</div>
<div class="col-6">
Dal:<input type="date" id="ordiniDal">
Al: <input type="date" id="ordiniAl">
<button class="btn btn-outline-success" id="btnCercaOrdiniDate">Cerca</button>
</div>
</div>

<div class="row product-row first-row">
	<div class="col-1 col-md-1 col-lg-1" style="padding-left:5px !important;">Ordine N.</div>
	<div class="col-1 col-md-1 col-lg-1">Stato</div>
	<div class="col-3 col-md-3 col-lg-3">Cliente</div>
	<div class="col-1 col-md-1 col-lg-1" style="padding-left:0px !important;">Prezzo</div>
	<div class="col-1 col-md-1 col-lg-1">Pagamento</div>
	<div class="col-3 col-md-4 col-lg-4" style="padding-left:30px !important; padding-right:0px !important;">Data</div>
	<div class="col-1 col-md-1 col-lg-1" style="padding-left:5px !important;">Tipo</div>
</div>

<%

Utente utente = null;
if (request.getSession().getAttribute("utente") != null) {
	utente = (Utente) request.getSession().getAttribute("utente");


	Set<Ordine> ordini = (Set<Ordine>)OrdineDAOFactory.getOrdineDAO().getOrdiniByFattorino(utente.getEmail());
	

	
for (Ordine or : ordini) {
	
	String state = or.getStato();
	String toPrintt="";
	 switch(state) {
	 case "Elaborazione ordine":
		 toPrintt="<option value='Ricevuto'>Ricevuto</option><option value='Preso in carico'>Preso in carico</option><option value='In lavorazione'>In lavorazione</option><option value='In consegna'>In Consegna</option><option value='Consegnato'>Consegnato</option>";
		 break;
	 case "Preso in carico":
		 toPrintt="<option value='Preso in carico'>Preso in carico</option><option value='In preparazione'>In preparazione</option><option value='In consegna'>In Consegna</option><option value='Consegnato'>Consegnato</option>";
		 break;
	 case "In preparazione":
		 toPrintt="<option value='In '>In lavorazione</option><option value='In consegna'>In Consegna</option><option value='Consegnato'>Consegnato</option>";
		 break;
	 case "In consegna":
		 toPrintt="<option value='In consegna'>In Consegna</option><option value='Consegnato'>Consegnato</option>";
		 break;
	 case "Consegnato":
		 toPrintt="<option value='Consegnato'>Consegnato</option>";
		 break;
	 }
	 int tipo = or.getTipoPagamento();
	String tipoPagamento="";
	switch(tipo){
	case 0:
		tipoPagamento="Carta";
		break;
	case 1:
		tipoPagamento="Contanti";
		break;
	}
	String tipoOrdine="";
	int tipoOr=or.getTipoOrdine();
	switch(tipoOr){
	case 0:
		tipoOrdine="Consegna a domicilio";
		break;
	case 1:
		tipoOrdine="Ritiro in pizzeria";
		break;
	}
	%>
	
<div class="row order-row product-row <%=or.getId() %>" data-toggle="collapse" href="#div<%=or.getId()%>" role="button" aria-expanded="false" aria-controls="collapseExample">
	<div class="col-1 col-md-1 col-lg-1"> <p><%=or.getNumeroOrdine()%> </p></div>
	<div class="col-1 col-md-1 col-lg-1">
		<select class="orderStateSelect" style="z-index: +2;" ordine="<%=or.getId()%>">
			<%=toPrintt%>
		</select>
	</div>
	<div class="col-3 col-md-3 col-lg-3"> <p><%=or.getCliente().getNome()%> <%=or.getCliente().getCognome()%></p></div>
	<div class="col-1 col-md-1 col-lg-1"> <p>&euro;  <%=or.getTotale()%></p></div>
	<div class="col-1 col-md-1 col-lg-1"> <p><%=tipoPagamento%></p></div>
	<div class="col-3 col-md-4 col-lg-4"> <p><%=or.getData()%></p></div>
	<div class="col-1 col-md-1 col-lg-1" style="padding-left:0px !important;" > <p><%=tipoOrdine%></p></div>
	
	<div class="collapse row row-p" id="div<%=or.getId()%>">
		
		<%
		String ref="";
		Carrello c = or.getCarrello();
		HashMap<Prodotto,Integer> map= (HashMap<Prodotto,Integer>) c.getProdotti();
		Iterator<Map.Entry<Prodotto, Integer>> itr = c.getProdotti().entrySet().iterator();
		
		while(itr.hasNext()) {
			Map.Entry<Prodotto, Integer> entry = itr.next();
			Prodotto p = entry.getKey();
			
			int value = entry.getValue();
			%>
		
			
			
			<div class="row row-p">
	 			<div class="col-5 col-md-2 col-lg-2"> <p><%=value%>  <%=p.getNome()%></p></div>
				<div class="col-3 col-md-2 col-lg-2"> <p><%=p.getCategoria().getNome()%></p></div>
				<div class="col-1 col-md-1 col-lg-3"> <p><%=p.getCategoria().getIva()%>  &percnt;</p></div>
				<div class="col-2 col-md-2 col-lg-3"> <p>&euro;<%=value*p.getPrezzo()%></p></div>				
			</div>
			
		
	
		<% } %>

	<div class="row">
		<div class="col-12" style="color: red;">
		Consegnare a: <%=or.getIndirizzo().toString() %>
		</div>
	</div>	
</div>
<%}} %>
</div>
<div class="w-100 mb-5"></div>
			
			
	
		
		</div>
	
	</div>
	<footer>
		Speedy Pizza
	</footer>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://momentjs.com/downloads/moment-with-locales.min.js"></script>
	<script src="script.js"></script>
	
	
</body>
</html>