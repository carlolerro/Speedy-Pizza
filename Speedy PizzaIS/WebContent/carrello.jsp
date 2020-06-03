<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.beans.Prodotto"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.beans.Carrello"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="style/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

</head>
<body>
<%@ include file = "modals.html" %>
<div class="container-fluid">
<%@ include file = "header.jsp" %>
<div id="contenutoPagina">
	<div class="div-cart">
	
		<div class="row product-row nome-prodotto">
				<div class="col-4 col-md-3 col-lg-3">Nome</div>
				<div class="col-1 col-md-2 col-lg-2">Qnt</div>
				<div class="col-2 col-md-2 col-lg-2">Prezzo</div>
				<div class="col-2 col-md-3 col-lg-3">
				</div>
				<div class="col-1 col-md-2 col-lg-2">Tot</div>
			</div>
			<%
		Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
			float totaleSpesa =0;
		if(cart != null){
		Iterator<Map.Entry<Prodotto, Integer>> itr = cart.getProdotti().entrySet().iterator();
		
		while(itr.hasNext()){
			Map.Entry<Prodotto, Integer> entry = itr.next();
			String nome = entry.getKey().getNome();
			float prezzo = entry.getKey().getPrezzo();
			float totale = entry.getKey().getPrezzo()*entry.getValue();
			int quantita = entry.getValue();
			totaleSpesa+=totale;
			
		%>
		
			<div class="row product-row "+"<%=nome%>+">
				<div class="col-4 col-md-3 col-lg-3" id="<%=nome%>"> <p><%=nome%></p></div>
				<div class="col-1 col-md-2 col-lg-2" id="quantita"><%=quantita%></div>
				<div class="col-2 col-md-2 col-lg-2" id="prezzo"><%=prezzo%></div>
				<div class="col-2 col-md-3 col-lg-3">
					<i class="material-icons material-cart" onclick="addProduct('<%=nome%>', 'carrello');" id="add-cart">add</i>
					<i class="material-icons material-cart" id="remove-cart" onclick="decreaseProduct('<%=nome%>');" >remove</i>
					<i class="material-icons material-cart" id="delete-cart" onclick="deleteProduct('<%=nome%>');"> delete</i>
				</div>
				<div class="col-1 col-md-2 col-lg-2" id="totale"><%=totale%></div>
				
			</div>
			
			<%} }%>
			
			<div class="row checkout-row" >
				<div class="col-6">
					<center><p id="spesa" style="color:white; margin-bottom:0px; padding-top: 7px;"> Spesa totale: <%=totaleSpesa %> &euro; </p></center>
				</div>
				<div class="col-6 checkout-col" style="text-align: center;">
					<button type="button" class="btn btn-success btn-lg btn-checkout mx-auto w-100" onclick="checkOut();">Checkout</button>
				</div>
			</div>
	</div>
	</div>
	
</div>
	<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="script.js"></script>
</body>
</html>