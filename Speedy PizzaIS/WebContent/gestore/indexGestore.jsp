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
	<%@ include file = "headerGestore.jsp" %>
	<%@ include file = "components/modals.jsp" %>
	
		<div id="contenutoPagina">
		<div class="row">
		
			<div class="col-12 col-md-4">
				<div class="card card-index" onClick="mostraUtenti();">
					<div class="card-text card-text-index">
						<p>Utenti</p>
					</div>
				</div>
			</div>
			<div class="col-12 col-md-4">
				<div class="card card-index" onClick="mostraRichieste();">
					<div class="card-text card-text-index">
						<p>Richieste</p>
					</div>
				</div>
			</div>
			
			
		</div>
		
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