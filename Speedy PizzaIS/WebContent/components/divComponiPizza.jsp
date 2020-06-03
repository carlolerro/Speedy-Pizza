
	<div id="divComeBack" onclick="showProductOfRestaurant('03944080658','Ristorante Europa');" class="col"><i class='material-icons'>arrow_back</i></div><div class="col-10"><h3 id="nomePizzeria">Ristorante Europa - Componi pizza</h3></div>
	<div class="col-sm-12 mb-3 mt-3">
		<h2>Che tipo di pizza preferisci?</h2>
	</div>
	<div class="col-sm-6 mt-2">
		<div class="card card-interna">
			<div onclick="selectProduct('rossa', 'productRossa');" id="productRossa" class="card-body card-principale" style='transition: all 0.5s;  font-size: 1.2em;'>
				Rossa
			</div>
		</div>
	</div>
	<div class="col-sm-6 mt-2">
		<div class="card card-interna">
			<div onclick="selectProduct('bianca', 'productBianca');" id="productBianca" class="card-body card-principale" style='transition: all 0.5s; font-size: 1.2em;'>
				Bianca
			</div>
		</div>
	</div>
	<div class="row mt-5 w-100" id="divSecondoStep" style="display: none !important;">
		<div class="col-sm-12">
			<h2>Scegli gli ingredienti da aggiungere</h2>
		</div>
	</div>
	<div class="row w-100 mt-5">
		<button id="btn-crea-prodotto" onclick="addCreatedProduct();" style="opacity: 0; transition: 0.5s all; width: 90vw; margin: auto !important; height: 8vh; font-size: 1.2em;" class="btn btn-outline-warning">Procedi</button>
	</div>