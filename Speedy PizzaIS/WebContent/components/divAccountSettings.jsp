<%@page import="model.beans.Cliente"%>
<%
	if (request.getSession().getAttribute("utente") != null){
		Cliente c = (Cliente)request.getSession().getAttribute("utente");
		if (c.getTipo() != 0){
			response.sendRedirect("/index.jsp");
		}else{%>
<form onsubmit="updateData(); return false">
<div class="form-group" style="margin-top: 5vh;">
	<h3 style="color: green;">Email</h3>
	<input type="email" class="form-control input-bordered" id="email" value="<%=c.getEmail() %>" style="width: 90%;" required>
		<div class="invalid-feedback">
        	Email non valida
      	</div>
</div>
<div class="form-group">
	<h3 style="color: green;">Nome</h3>
	<input type="text" class="form-control input-bordered" id="nome"value="<%=c.getNome() %>" style="width: 90%;" required>
		<div class="invalid-feedback">
        	Nome non valido
      	</div>
</div>
<div class="form-group">
	<h3 style="color: green;">Cognome</h3>
	<input type="text" class="form-control input-bordered" id="cognome" value="<%=c.getCognome() %>" style="width: 90%;" required>
		<div class="invalid-feedback">
        	Cognome non valido
      	</div>
</div>
<div class="form-group" style="margin-top: 5vh;">
	<h3 style="color: green;">Telefono</h3>
	<input type="text" class="form-control input-bordered" id="telefono" value="<%=c.getTelefono() %>" style="width: 90%;" required>
		<div class="invalid-feedback">
        	Telefono non valdo
      	</div>
</div>
<div class="col-xl-12" style="margin-top: 7vh;">
	<input type="submit" value="Aggiorna" class="btn btn-outline-success" style="width: 90%; font-size: 1.2em; margin-bottom: 2vh; border: 2px solid green;">
</div>
</form><%}} %>
<div class="col-xl-12">
	<button id="btn-change-password" onclick="apriModalChange();" class="btn btn-outline-warning" style="width: 90%; font-size: 1.2em; margin-bottom: 2vh; border: 2px solid #F2C337;">Cambia password</button>
</div>


	
