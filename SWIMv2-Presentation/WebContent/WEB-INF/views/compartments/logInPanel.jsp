<!DOCTYPE html>

<script>
function checkFields(){
	var email = document.forms["login"]["email"].value;
	var password =  document.forms["login"]["password"].value;
	var nullField = email==null || password==null;
	var emptyField = email=="" || password=="";
	
	if(nullField || emptyField){
		alert("Missing some fields!");
		return false;
	}
}
/*
function get(name){
	
	   if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
	      return decodeURIComponent(name[1]);
}
*/
</script>



<form name="login" action="login.servlet" method="post" onsubmit="return checkFields()">
E-mail address: <input type="text" name="email">
Password: <input type="password" name="password">
<input type="submit" value="Log In">
</form>