<%@page import="model.daoFactory.PizzeriaDAOFactory"%>
<%@page import="model.beans.Pizzeria"%>
<%@page import="java.util.Set"%>

<%
	Set<Pizzeria> pizzerie = (Set<Pizzeria>) PizzeriaDAOFactory.getPizzeriaDAO().getPizzerie();
%>


<div class="modal fade padding" id="modalAggiungiFattorino" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<form id="formAggiungiFatt" onsubmit="aggiungiFattorino(); return false">
			<div class="modal-content" style="padding:10">
				<div class="modal-header" style="color: #333333;">
					<h5 class="modal-title" id="titolo">Aggiungi fattorino</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
			
					<div class="form-group">
						<label for="nomeRist">Ristorante</label>
						<select id="elencoPizzerie" required="required">
							<%
								for(Pizzeria p:pizzerie){
							%>
							<option disabled selected value>Scegli pizzeria</option>
							<option value = <%= p.getPartitaIva() %>><%= p.getNome() %></option>
							<%
								}
							%>
							
						</select>
					</div>
					<div class="form-group">
						<label for="nomeFatt">Nome</label>
						<input type="text" class="form-control" id="nomeFatt" pattern="[a-zA-z]{2,19}" aria-describedby="emailHelp" required/>
						
					</div>
					<div class="form-group">
						<label for="cognomeFatt">Cognome</label>
						<input type="text" class="form-control" id="cognomeFatt" pattern="[a-zA-z]{2,29}" aria-describedby="emailHelp" required/>
						
					</div>
					<div class="form-group">
						<label for="emailFatt">Email</label>
						<input type="text"  class="form-control" id="emailFatt" pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$" aria-describedby="emailHelp" required/>
					</div>
					<div class="form-group">
						<label for="passwordFatt">Password</label>
						<input type="password"  class="form-control" id="passwordFatt" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,}" aria-describedby="emailHelp" required/>
					</div>	
					<div class="form-group">
						<label for="telefonoFatt">Telefono</label>
						<input type="text"  class="form-control" id="telefonoFatt" pattern="[0-9]{9,14}" aria-describedby="emailHelp" required/>
					</div>				
				
				
				<div class="modal-footer">
				<input id="btnAggiungiFattorino" type="submit" class="btn btn-success" value="Crea"/>

				</div>
			</div>
		</form>
		</div>
		</div>