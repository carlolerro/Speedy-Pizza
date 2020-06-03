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
	<%@ page import="java.util.ArrayList" %>
	
	<div class="container-fluid">
		<%@ include file = "header.jsp" %>
		<div id="contenutoPagina">
		
	<div class="row div-ordinazione-ora">
		<div class="div-ordinazione-ora-interno">
			<h2 style="margin-top: 2vh;">Cosa aspetti..</h2>
			<h2>Ordina online la tua cena in pochi passi</h2>
			<button onclick="location.href='ordina.jsp'" type="button" class="btn btn-outline-success btn-prosegui" >Ordina ora</button>
		</div>
	</div>
	<div class="row motivazioni w-100">
		<div class="col-xl-12"><h1>Ma perch&egrave; dovresti sceglierci?</h1></div>
		<div class="col-xl-1"></div>
		<div class="col-xl-2 el-preferenza" >
			<i class="material-icons" style="font-size: 1.2em !important">timer</i>
			<p>Consegna <b>veloce</b> su tutti i prodotti</p>
		</div>
		<div class="col-xl-1"></div>
		<div id="vantaggiose-offerte" class="col-md-4 el-preferenza">
			<i class="material-icons" style="font-size: 1.2em !important">euro_symbol</i>
            <p>Vantaggiose <b>offerte</b> per tutti i clienti iscritti</p>
		</div>
		<div class="col-xl-1"></div>
		<div class="col-xl-2 el-preferenza">
			<i class="material-icons" style="font-size: 1.2em !important">local_pizza</i>
			<p>Scegliamo solo pizzerie che utilizzano prodotti di <b>alta qualit&agrave;</b></p>
		</div>
		<div class="col-xl-1"></div>
	</div>
	
	
	</div>
	
	<%@ include file = "footer.jsp" %>
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="script.js"></script>
</body>
</html>