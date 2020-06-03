	<%@page import="model.beans.Utente"%>
<%@page import="model.beans.Carrello" %>

<%
if (request.getSession().getAttribute("utente") != null) {
	Utente u = (Utente)request.getSession().getAttribute("utente");
	if(u.getTipo()==1){
	response.sendRedirect("pizzeria/indexPizzeria.jsp");
	

	}else if(u.getTipo()==3){
		response.sendRedirect("gestore/indexGestore.jsp");

	}
}
%>
<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light menubar">
			<a class="navbar-brand" href="index.jsp"><img src="img/logo.png" style="max-height: 40px;"></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav mr-auto">
					<li id="first-item" class="nav-item active">
						<a class="nav-link" href="ordina.jsp">Ordina Ora</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="tracker.jsp">Tracker</a>
					</li>
					
					<li class="nav-item">
						<a class="nav-link" href="contatti.jsp">Contatti</a>
					</li>
					<%if(request.getSession().getAttribute("utente") == null){ %>
					<li class="nav-item">
						<a class="nav-link" href="richiesta.jsp">Richiesta Affiliazione</a>
					</li>
					<%} %>
				</ul>
				
				<span class="navbar-text">
					<% if (request.getSession().getAttribute("utente") != null) {
						Utente u = (Utente) request.getSession().getAttribute("utente");
						if (u.getTipo()==0){
						%> 
						<i onclick="showSettings();" id="account-icon" class="material-icons" style="margin-right: 2vw;">settings</i>
						<i onclick="logout();" id="logout-icon" class="material-icons" style="margin-right: 2vw;">logout </i>
						<%
						}} else {
						%> <i onclick="openAccount('no');" id="account-icon" class="material-icons" style="margin-right: 2vw;">person</i> <%
					}
					%>
                    <i onclick="showCart();" id="cart-icon" class="material-icons" style="">shopping_cart</i>
                    <span value=0 id="numProdInCarrello" class="badge badge-notify" style=" position:relative; font-weight:100; font-size:1em; top:-10px; left:-13px;">
                    
       				</span>
				</span>
			</div>
			
		</nav>
	</header>