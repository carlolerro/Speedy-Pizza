
<%@page import="model.daoFactory.UtenteDAOFactory"%>
<%@page import="model.daoFactory.PizzeriaDAOFactory"%>
<%@page import="model.beans.Pizzeria"%>
<%@page import="model.beans.Utente"%>
<%@page import="java.util.Set"%>
<% 
	Set<Utente> utenti = (Set<Utente>)UtenteDAOFactory.getUtenteDAO().getUtenti();
%>
<div class="row product-row first-row-cat">
		<div class="col-2 col-md-2 col-lg-2"> <p>Nome</p></div>
		<div class="col-2 col-md-2 col-lg-2">Cognome</div>
		<div class="col-2 col-md-2 col-lg-2">Email</div>
		<div class="col-2 col-md-2 col-lg-2">Telefono</div>
		<div class="col-2 col-md-2 col-lg-2">Ruolo</div>
</div>
<%for(Utente u: utenti){ 
	int tipo = u.getTipo();
	String ruolo="";
	switch(tipo){
		case 0:
			ruolo="cliente";
			break;
		case 1:
			ruolo="ristoratore";
			break;
		case 2:
			ruolo="fattorino";
			break;
		case 3:
			ruolo="gestore";
			break;
	}
	%>
<div class="row product-row nomeProdotto">
		<div class="col-2 col-md-2 col-lg-2" style=text-align:center> <p><%=u.getNome() %></p></div>
		<div class="col-2 col-md-2 col-lg-2" style="text-align:center"><p><%=u.getCognome()%></p></div>
		<div class="col-2 col-md-2 col-lg-2" style="text-align:center"><p><%=u.getEmail() %></p></div>
		<div class="col-2 col-md-2 col-lg-2" style="text-align:center"><p><%=u.getTelefono() %></p></div>
		<div class="col-2 col-md-2 col-lg-2" style="text-align:center"><p><%=ruolo %></p></div>
		
		<div class="col-1 col-md-2 col-lg-2" style="text-align:center">
		
			<i onClick="deleteUtente('<%=u.getEmail()%>')" class="material-icons" id="delete-cart" style="margin-top:10px;"> delete</i>
		</div>
	</div>
	<%} %>
	<div class="row w-100 mt-4 mb-3">
		<div class="col-xl-12">
			<button class="btn btn-success"onClick="visualizzaModalAggiungiFattorino();" style="width: 50%;  margin-left: 25%; font-size: 1.3em;
			 box-shadow:0px 10px 10px 1px rgb(0, 0, 0,1);" id="btnAddProd">Aggiungi un nuovo Fattorino</button>
		</div>
	</div>