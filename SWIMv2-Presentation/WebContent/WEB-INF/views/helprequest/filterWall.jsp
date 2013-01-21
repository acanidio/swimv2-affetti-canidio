<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<html>
<head>

<script type="text/javascript">
function mandatory_filtering(){
	var friends = document.forms["filter"]["friends"].selected;
	var city = document.forms["filter"]["city"].value;
	var ability = document.forms["filter"]["ability0"].value;
	
	var emptyFields = (friends==null || friends=="") && (city==null || city=="") && ability=="none";
	
	if(emptyFields){
		alert("You must fill at least one field!");
		return false;
	}
	
	return true;
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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filter Wall</title>
</head>
<body onload="addNone();">

<swim:topMenu type="${sessionScope.type}" />


<form name="filter" action="filterwall.servlet" method="get" onsubmit="return mandatory_filtering();">
<ul>
<li>Filter by friends' posts: <input type="checkbox" name="friends" value="true"></li>
<li>Filter by city: <input type="text" name="city"></li>
<li>Filter by ability: <swim:abilitiesSelect abilities="${requestScope.abilities}"></swim:abilitiesSelect></li>
<li><input type="submit" value="Filter"></li>
</ul>

</form>

</body>
</html>