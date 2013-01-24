<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/mycss.css" type="text/css">
<link href="css/custom-theme/jquery-ui-1.10.0.custom.css" rel="stylesheet">
<script src="js/jquery-1.9.0.js"></script>
<script src="js/jquery-ui-1.10.0.custom.js"></script>
<script>
	$(function() {
		$( "#datepicker" ).datepicker({
			showOtherMonths: true,
		    selectOtherMonths: true,
		    showButtonPanel: true,
		    dateFormat: "yy-mm-dd",
		    changeMonth: true,
		    changeYear: true,
			inline: true
		});
		// Hover states on the static widgets
		$( "#dialog-link, #icons li" ).hover(
			function() {
				$( this ).addClass( "ui-state-hover" );
			},
			function() {
				$( this ).removeClass( "ui-state-hover" );
			}
		);
	});
</script>
<title>Signing up to SWIM</title>
<script type="text/javascript">

function mandatory_SignUp(){
	var name = document.forms["signup"]["name"].value;
	var surname = document.forms["signup"]["surname"].value;
	var password = document.forms["signup"]["password"].value;
	var email = document.forms["signup"]["email"].value;
	
	var nullFields = name==null || surname==null || password==null || email==null;
	var emptyFields = name=="" || surname=="" || password=="" || email=="";
	
	if(nullFields || emptyFields){
		alert("Some mandatory fields were not fulfilled!");
		return false;
	}
	
	return true;
}

function differentPWs(){
	var password = document.forms["signup"]["password"].value;
	var cpassword = document.forms["signup"]["cpassword"].value;
	
	if(password!=cpassword){
		alert("Password different from confirmed password!");
		return false;
	}
	
	if(password.length < 3){
		alert("Password has to be at least 3 characters long!");
		return false;
	}
	
	return true;
	
}

function notValidEmail(){
	var email = document.forms["signup"]["email"].value;
	
	var atpos = email.indexOf("@");
	var dotpos = email.lastIndexOf(".");
	
	if((atpos<1) || (dotpos<(atpos+2)) || ((dotpos+2)>=email.length)){
		alert("E-mail address not valid!");
		return false;
	}
	
	return true;
}

function checkProfileForm(){
	
	if(!mandatory_SignUp()){
		return false;
	}
	if(!notValidEmail()){
		return false;
	}
	if(!differentPWs()){
		return false;
	}
	
	return true;
}
</script>

<script type="text/javascript">
 var counter = 1;
 
 function appendSelect(){
	 var originalSelect = document.getElementById("select0");
	 var select = document.createElement("select");
	 
	 select.name = "ability" + counter;
	 select.id = "select" + counter;
	 
	 
	 select.innerHTML += originalSelect.innerHTML;

	 var listel = document.createElement("li");
	 listel.appendChild(select);
	 
	 document.getElementById("abilities").appendChild(listel);
	 
	 counter++;
	 }
 
 function removeSelect(){
	 if(counter>1){
	 	counter--;
	 	 
	 	var lastSelect = document.getElementById("select"+counter);
	 	var li = lastSelect.parentNode;
	 	li.removeChild(lastSelect);
	 	li.parentNode.removeChild(li);
	 }
 }
</script>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="navigation">
			<swim:topMenu type="${sessionScope.type}"></swim:topMenu>
		</div>
		<div id="content">
			<form name="signup" action="user.store" method="post" onsubmit="return checkProfileForm();">
				<fieldset>
					<legend>Registration Form</legend>
				<table>
					<tr>
						<td>Name*: </td>
						<td><input type="text" name="name" value="${sessionScope.person.name}"></td>
					</tr>
					<tr>
						<td>Surname*: </td>
						<td><input type="text" name="surname" value="${sessionScope.person.surname}"></td>
					</tr>
					<tr>
						<td>Gender*: </td>
						<td>
							M<input name="gender" type="radio" value="M" checked="checked">
							F<input name="gender" type="radio" value="F">
						</td>
					</tr>
					<tr>
						<td>Password*: </td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td>Confirm Password*: </td>
						<td><input type="password" name="cpassword"></td>
					</tr>
					<tr>
						<td>E-mail address*: </td>
						<td><input type="text" name="email"></td>
					</tr>
					<tr>
						<td>City: </td>
						<td><input type="text" name="city" value="${sessionScope.person.city}"></td>
					</tr>
					<tr>
						<td>Birthday: </td>
						<td><input type="text" id="datepicker" name="birthday"></td>
					</tr>
					<tr>
						<td>Phone number: </td>
						<td><input type="text" name="phonenumber"></td>
					</tr>
					<tr>
						<td>
							Abilities: 
						</td>
						<td>
							<ul id="abilities">
								<li>
									<swim:abilitiesSelect abilities="${abilities}" />
								</li>
							</ul>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="button" value="+" onclick="appendSelect();">
							<input type="button" value="-" onclick="removeSelect();">
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="Send profile information">
						</td>
					</tr>
				</table>
				</fieldset>
			</form>
		</div>
		<swim:footer/>
	</div>
</body>
</html>