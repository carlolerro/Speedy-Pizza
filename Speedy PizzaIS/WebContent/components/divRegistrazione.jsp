
		<div class="col-xl-12 div-registrazione" style="padding-top: 2vh;">
			<form onsubmit="register(); return false" >
				<h1>Registrazione</h1>
				<div class="form-group" >
					<label for="input-nome" style="color: green;">Nome</label>
					<input type="text" class="form-control input-bordered" id="input-name-reg" pattern="[a-zA-z]{2,19}" style="width: 90%" required>
				</div> 	
				
				<div class="form-group">
					<label for="input-cognome" style="color: green;">Cognome</label>
					<input type="text" class="form-control input-bordered" id="input-surname-reg" pattern="[a-zA-z]{2,29}"  style="width: 90%" required>
				</div>
				
				<div class="form-group">
					<label for="input-email" style="color: green;">E-mail</label>
					<input type="email" class="form-control input-bordered" id="input-email-reg" pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$" style="width: 90%" required>
				</div>
				<div class="form-group">
					<label for="input-password" style="color: green;">Password</label>
					<input type="password" class="form-control input-bordered" id="input-password-reg" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,}" style="width: 90%" placeholder="Minimo 6 caratteri - Almeno una lettera e un numero" required>
				</div>
				<div class="form-group">
					<label for="input-conferma-password" style="color: green;">Conferma password</label>
					<input type="password" class="form-control input-bordered" id="input-conferma-password-reg" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,}" style="width: 90%" required>
				</div>
				
				<div class="form-group">
					<label for="phone" style="color: green;">Telefono</label>
					<input type="phone" class="form-control input-bordered" id="input-phone-reg" pattern="[0-9]{9,14}" style="width: 90%" required>
				</div>
				
				<input id="btnRegistrati" type="submit" class="btn btn-outline-success" value="Registrati" style="width: 90%; font-size: 1.2em; margin-top: 4vh; border: 2px solid green;">
			</form>
		</div>
	