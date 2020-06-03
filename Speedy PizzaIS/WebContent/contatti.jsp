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
	<div class="row div-tracker">
		<div class="col-xl-3"></div>
		<div class="col-xl-4" style="text-align: left; font-size: 1.2em;margin: auto;">
			<a href="mailto:info@speedypizza.it"><button type="button" class="btn btn-outline-warning" style="margin-right: 5vw;"><i class="material-icons" >email</i></button> </a>
			<span style="margin-top: 2vh;">info@speedypizza.it</span>
			<div style="margin-top: 5vh;">
				<a href="tel:3485813155"><button type="button" class="btn btn-outline-warning" style="margin-right: 5vw;"><i class="material-icons" >phone</i></button></a>
				<span>3485813155</span>
			</div>
			<div style="margin-top: 5vh;">
				<button type="button" class="btn btn-outline-warning" style="margin-right: 5vw;"><i class="material-icons" >location_on</i></button> 
				<span>Narnia, Via Pinco Pallino 10</span>
			</div>
		</div>
		<div class="col-xl-3"></div>
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