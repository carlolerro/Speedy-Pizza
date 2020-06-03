
function logout() {
	
	$.ajax({
		url : '../AutenticazioneServlet',
		type: "POST",
		data:{
			method: 'logout'
		},
		success : function() {
			
			location.reload();
		}
	});
}

function mostraUtenti(){
	$("#contenutoPagina").load('components/divUtenti.jsp');
}
function mostraRichieste(){
	$("#contenutoPagina").load('components/divRichieste.jsp');
}

function deleteUtente(email){
	$.ajax({
		url : '../ManagerUtentiServlet',
		type: "POST",
		data : {
			method : "rimuoviUtente",
			email: email
		},
		success : function(result) {
			location.reload();
		},
		error: function() {
			alert("impossibile eliminare");
		}
	});	

}
function accettaRichiesta(id){
	$.ajax({
		url : '../AffiliazioneServlet',
		type: "POST",
		data : {
			method : "accettaRichiesta",
			idRichiesta: id
		},
		success : function(result) {
			mostraRichieste();
		},
		error: function() {
			alert("errore");
		}
	});	
}
function rifiutaRichiesta(id){
	$.ajax({
		url : '../AffiliazioneServlet',
		type: "POST",
		data : {
			method : "rifiutaRichiesta",
			idRichiesta: id
		},
		success : function(result) {
			mostraRichieste();
		},
		error: function() {
			alert("impossibile eliminare");
		}
	});	
}
function visualizzaModalAggiungiFattorino(){
	$('#modalAggiungiFattorino').modal('show');
}
function aggiungiFattorino(){
		
	var utente={
			nome : $("#nomeFatt").val(),
			cognome:$("#cognomeFatt").val(),
			email:$("#emailFatt").val(),
			password : $("#passwordFatt").val(),
			telefono:$("#telefonoFatt").val(),
			tipo: 2
	};
	$.ajax({
		url : '../ManagerFattorinoServlet',
		type: "POST",
		data : {
			
			method : 'aggiungiFattorino',
			fattorino : JSON.stringify(utente),
			pizzeria : $("#elencoPizzerie :selected").val()
			
		},
		success : function() {
			alert("aggiunto");			
			$("#modalAggiungiFattorino").modal('hide');
			mostraUtenti()
		},
		error: function() {
			alert("impossibile aggiungere");
		}
	});	
}








