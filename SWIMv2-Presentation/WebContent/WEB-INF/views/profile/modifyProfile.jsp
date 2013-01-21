<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/mycss.css" type="text/css">
<title>Modify Profile</title>
<script type="text/javascript">

function mandatory_Modify(){
	var name = document.forms["signup"]["name"].value;
	var surname = document.forms["signup"]["surname"].value;
	
	var nullFields = name==null || surname==null;
	var emptyFields = name=="" || surname=="";
	
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
	
	if(password.length > 0 && password.length < 3){
		alert("New password has to be at least 3 characters long!");
		return false;
	}
	
	return true;
	
}

function checkProfileForm(){
	
	if(!mandatory_Modify()){
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
 
 function addNone(){
	 var select = document.getElementById("select0");
	 var none = document.createElement("option");
	 var text = document.createTextNode("None...");
	 none.id = "option0";
	 none.value = "none";
	 none.selected = "selected";
	 
	 none.appendChild(text);
	 
	 select.appendChild(none);
 }
</script>
</head>
<body onload="addNone();">
	<div id="container">
		<div id="header"></div>
		<div id="navigation">
			<swim:topMenu type="${sessionScope.type}"></swim:topMenu>
		</div>
		<div id="content">
			<form name="signup" action="storemodif.servlet" method="post" onsubmit="return checkProfileForm();">
				<ul>
					<li>Name*: <input type="text" name="name" value="${sessionScope.person.name}"></li>
					<li>Surname*: <input type="text" name="surname" value="${sessionScope.person.surname}"></li>
					<li>Password: <input type="password" name="password"></li>
					<li>Confirm Password: <input type="password" name="cpassword"></li>
					<li>City: <input type="text" name="city" value="${sessionScope.person.city}"></li>
					<li>Birthday: <input type="date" name="birthday"></li>
					<li>Phone number: <input type="text" name="phonenumber"></li>
					<li> Already owned abilities:
						<ul>
							<c:forEach var="ability" items="${sessionScope.person.abilities}">
								<li>${ability.name}</li>
							</c:forEach>
						</ul>
					</li>
					<li>
						<ul id="abilities">
							<li>
								<swim:abilitiesSelect abilities="${abilities}" />
							</li>
						</ul>
						<input type="button" value="+" onclick="appendSelect();">
						<input type="button" value="-" onclick="removeSelect();">
					</li>
					<li><a href="newability.view">Other...</a></li>
					<li><input type="submit" value="Send profile information"></li>
				</ul>
			</form>
		</div>
		<div id="footer">
			<p>Developed by Affetti Lorenzo and Canidio Andrea</p>
		</div>
	</div>
</body>
</html>