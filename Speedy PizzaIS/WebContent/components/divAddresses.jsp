<%@page import="model.beans.Utente"%>
<%@page import="model.beans.Indirizzo"%>
<%@page import="model.beans.Carta"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.beans.Cliente"%>
<div class="row div-registrazione" id="div-addresses" style="padding-top: 2vh">
	<%
	if (request.getSession().getAttribute("utente") != null){
		Cliente c= (Cliente)request.getSession().getAttribute("utente");
		if (c.getTipo() != 0){
			response.sendRedirect("/index.jsp");
		}else{
			
			Set<Indirizzo> set = c.getIndirizzi();
			if(set!=null){
			for(Indirizzo indirizzo: set){%>
				<div onclick="deleteAddress(<%=indirizzo.getIdIndirizzo() %>)"class="col-md-3" style="padding-top: 2vh">
					<div class="card card-interna">
					
						<div class="card-body">				
							<p>Indirizzo: <%=indirizzo.toString() %></p>
					
						</div>
					</div>
				</div>
			<%}
		}
		}
	}
	%>

	<button onclick="showAddAddress();" class="btn btn-outline-success" style="margin: auto; width: 90%; margin-top: 6vh; font-size: 1.2em;" >
		+</button>
</div>