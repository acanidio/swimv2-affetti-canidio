<%@ attribute name="action" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="swim" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
function moreAbilities(){

	var abilities = document.createElement("li");
	abilities.innerHTML="<swim:abilitiesCheckBox abilities='${abilities}'></swim:abilitiesCheckBox>";
	
	document.getElementById("abilities").appendChild(abilities);
	
}

function mandatoryFields(){
	var name = document.forms["signup"]["name"].value;
	var surname = document.forms["signup"]["surname"].value;
	var gender = document.forms["signup"]["gender"].value;
	var password = document.forms["signup"]["password"].value;
	var email = document.forms["signup"]["email"].value;
	
	var nullFields = name==null || surname==null || gender==null || password==null || email==null ;
	var emptyFields = name=="" || surname=="" || gender=="" || password=="" || email=="";
	
	if(nullFields || emptyFields){
		alert("Some mandatory fields were not fulfilled!");
		return false;
	}
	
}

function differentPWs(){
	var password = document.forms["signup"]["password"].value;
	var cpassword = document.forms["signup"]["cpassword"].value;
	
	if(password!=cpassword){
		alert("Password different from confirmed password!");
		return false;
	}
	
}

function notValidEmail(){
	var email = document.forms["signup"]["email"].value;
	
	var atpos = email.indexOf("@");
	var dotpos = email.lastIndexOf(".");
	
	if((atpos<1) || (dotpos<(atpos+2)) || ((dotpos+2)>=email.length)){
		alert("E-mail address not valid!");
		return false;
	}
}

function checkProfileForm(){
	mandatoryFields();
	notValidEmail();
	differentPWs();
}
</script>


<form name="signup" action="${action}" method="post" onsubmit="checkProfileForm();">
<ul>
<li>Name*: <input type="text" name="name"></li>

<li>Surname*: <input type="text" name="surname"></li>

<li>
<fieldset>
	<legend>Gender*:</legend>
	M<input name="gender" type="radio" value="M">
	F<input name="gender" type="radio" value="F">
</fieldset>
</li>

<li>Avatar: <input type="file" name="avatar"></li>

<li>Password*: <input type="password" name="password"></li>
<li>Confirm Password*: <input type="password" name="cpassword"></li>

<li>E-mail address*: <input type="text" name="email"></li>

<li>City: <input type="text" name="city"></li>

<li>Birthday: <input type="date" name="birthday"></li>

<li>Phone number: <input type="text" name="phonenumber"></li>

<c:if test="${action=='storemodif.servlet'}">
<li>
<ul>
	<c:forEach var="ability" items="${sessionScope.user.abilities}">
		<li>${ability.name}</li>
	</c:forEach>
</ul>
</li>
</c:if>

<li>
<ul id="abilities">
<li><swim:abilitiesCheckBox abilities="${abilities}"></swim:abilitiesCheckBox></li>
</ul>

<input type="button" value="+" onclick="moreAbilities()">
</li>

<li><a href="newability.view">Other...</a></li>

<li><input type="submit" value="Send profile information"></li>

</ul>
</form>