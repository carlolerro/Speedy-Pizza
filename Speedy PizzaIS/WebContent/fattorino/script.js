
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


$(".orderStateSelect").change(function(){
	var st = $(this).val();
	
	var order = $(this).attr("ordine");
	
	$.ajax({
		url:'../OrdineServlet',
		type:"POST",
		data:{
			method: 'aggiornaStato',
			idOrdine : order,
			stato : st
		},
		success: function(result){
			location.reload()
		}
	});
}).fadeIn('500');

