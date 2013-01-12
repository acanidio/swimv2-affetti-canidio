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



<form name="login" action="login.servlet" method="post" onsubmit="return checkFields();">
<ul>
<li>E-mail address: <input type="text" name="email"></li>
<li>Password: <input type="password" name="password"></li>
<li><input type="submit" value="Log In"></li>
</ul>
</form>