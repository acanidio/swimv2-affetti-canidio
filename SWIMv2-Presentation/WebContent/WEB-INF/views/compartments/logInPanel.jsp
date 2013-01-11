<!DOCTYPE html>

<script type="text/javascript">
function validateLogin(){
	var name = document.forms["login"]["name"].value;
	var surname =  document.forms["login"]["surname"].value;
	var nullField = name==null || surname==null;
	var emptyField = name=="" || surname=="";
	
	if(nullField || emptyField){
		alert("Missing some fields!");
		return false;
	}
	
	if(get("exception")=="noresult"){
		alert("Username or password is wrong!");
		return false;
	}
}

function get(name){
	
	   if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
	      return decodeURIComponent(name[1]);
}
</script>



<form name="login" action="login.servlet" method="post" onsubmit="return validateLogin()">
E-mail address: <input type="text" name="email">
Password: <input type="password" name="password">
<input type="submit" value="Log In">
</form>