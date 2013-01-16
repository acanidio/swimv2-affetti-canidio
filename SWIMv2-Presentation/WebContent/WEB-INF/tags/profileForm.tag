<%@ attribute name="action" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="swim" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">

function mandatoryFields(){
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
	
	if(!mandatoryFields()){
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

	/*
	 
	 var options = new Array();
	 
	 var element = document.getElementById("option0");
	 for(var i=0; element!=null ; i++){
		 element = document.getElementById("option"+i);
		 options[i] = element;
	 }
	 
	 for(var i=0; i<options.length; i++){
		 var option = document.createElement("option");
		 option.value = options[i].value;
		 var text = document.createTextNode(option.value);
		 
		 option.appendChild(text);
		 select.appendChild(option);
	 }
	 */
	 var listel = document.createElement("li");
	 listel.appendChild(select);
	 
	 document.getElementById("abilities").appendChild(listel);
	 
	 counter++;
 }
</script>


<form name="signup" action="${action}" method="post" onsubmit="return checkProfileForm();">
<ul>
<li>Name*: <input type="text" name="name" value="${sessionScope.person.name}"></li>

<li>Surname*: <input type="text" name="surname" value="${sessionScope.person.surname}"></li>


<c:if test="${action=='user.store'}">
<li>
<fieldset>
	<legend>Gender*:</legend>
	M<input name="gender" type="radio" value="M" checked="checked">
	F<input name="gender" type="radio" value="F">
</fieldset>
</li>
</c:if>

<li>Avatar: <input type="file" name="avatar"></li>


<li>Password*: <input type="password" name="password"></li>

<li>Confirm Password*: <input type="password" name="cpassword"></li>

<c:if test="${action=='user.store'}">
<li>E-mail address*: <input type="text" name="email"></li>
</c:if>

<li>City: <input type="text" name="city" value="${sessionScope.person.city}"></li>

<li>Birthday: <input type="date" name="birthday"></li>

<li>Phone number: <input type="text" name="phonenumber"></li>

<c:if test="${action=='storemodif.servlet'}">
<li>
<ul>
	<c:forEach var="ability" items="${sessionScope.person.abilities}">
		<li>${ability.name}</li>
	</c:forEach>
</ul>
</li>
</c:if>



<li>
<ul id="abilities">
<li>
	<swim:abilitiesSelect abilities="${abilities}" />
</li>
</ul>

<input type="button" value="+" onclick="appendSelect();">
</li>

<c:if test="${action=='storemodif.servlet'}">
	<li><a href="newability.view">Other...</a></li>
</c:if>

<li><input type="submit" value="Send profile information"></li>

</ul>
</form>