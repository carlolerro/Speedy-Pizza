	

	<%@page import="java.util.ArrayList"%>
<%@page import="model.beans.Prodotto"%>
	<%@page import="model.daoFactory.ProdottoDAOFactory"%>
	<%@page import="model.daoFactory.CategoriaDAOFactory"%>
	<%@page import="model.beans.Pizzeria"%>
	<%@page import="model.beans.Categoria"%>
	<%@page import="java.util.Set"%>
	<%
	Pizzeria pizzeria= (Pizzeria) request.getSession().getAttribute("pizzeria");
	Set<Categoria>  categorie = (Set<Categoria>)CategoriaDAOFactory.getCategoriaDAO().getCategorie();
	
	for (Categoria cat : categorie) {
		ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>)ProdottoDAOFactory.getProdottoDAO().getProdottiByCategoria(pizzeria.getPartitaIva(), cat.getNome());
		
	%>
	Categoria: <%=cat.getNome() %><br> IVA : <%=cat.getIva() %> 

	
	<div class="row product-row first-row-cat">
		<div class="col-4 col-md-3 col-lg-3"> <p>Nome</p></div>
		<div class="col-2 col-md-3 col-lg-3">Disp</div>
		<div class="col-3 col-md-3 col-lg-3">Prezzo</div>
		<div class="col-3 col-md-3 col-lg-3"></div>
	</div>
	<%
	for (Prodotto prod: prodotti) {
		prod.setCategoria(cat);
	%>
	<div class="row product-row nomeProdotto">
		<div class="col-4 col-md-3 col-lg-3" style=text-align:center> <p><%=prod.getNome() %></p></div>
		<div class="col-2 col-md-3 col-lg-3" style="text-align:center"><%=prod.getDisponibilita() %></div>
		<div class="col-3 col-md-3 col-lg-3" style="text-align:center"> &euro;  <%=prod.getPrezzo() %></div>
		<div class="col-3 col-md-3 col-lg-3" style="text-align:center">
		<i onClick="settingsProduct('<%=prod.getNome()%>','<%=prod.getIngredienti()%>','<%=prod.getPrezzo()%>','<%=prod.getDisponibilita()%>','<%=prod.getCategoria().getNome()%>');"		
			id="account-icon" class="material-icons" style="margin-top:10px;">settings</i>
			<i onClick="deleteProduct('<%=prod.getNome()%>')" class="material-icons" id="delete-cart" style="margin-top:10px;"> delete</i>
		</div>
	</div>
	<%} %>
	<%}%>
	<div class="row w-100 mt-4 mb-3">
		<div class="col-xl-12">
			<button class="btn btn-success"onClick="visualizzaModalAggiungiProdotto();" style="width: 50%;  margin-left: 25%; font-size: 1.3em;
			 box-shadow:0px 10px 10px 1px rgb(0, 0, 0,1);" id="btnAddProd">Aggiungi un nuovo prodotto</button>
		</div>
	</div>