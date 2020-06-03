<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

		<nav class="navbar navbar-expand-lg navbar-light bg-light menubar">
			<a class="navbar-brand" href="indexGestore.jsp"><img src="../img/logo.png" style="max-height: 40px;"></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				
				<span class="navbar-text">
					<%@page import="model.beans.Utente"%>
						<%
						if (request.getSession().getAttribute("utente") != null) {
							Utente u = (Utente)request.getSession().getAttribute("utente");
							if(u.getTipo()!=3){
								response.sendRedirect("../index.jsp");}
						}else{
								response.sendRedirect("../index.jsp");
						}%>
						<i onclick="logout();" id="logout-icon" class="material-icons" style="margin-right: 2vw;">logout </i>
						
				</span>
			
			</div>
		</nav>
	