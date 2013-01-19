<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">
function mandatory_HR(){
	var title = document.forms["newhr"]["title"].value;
	var city = document.forms["newhr"]["city"].value;
	var date = document.forms["newhr"]["date"].value;
	var hour = document.forms["newhr"]["hour"].value;
	
	var nullFields = title==null || city==null || date==null || hour==null;
	var emptyFields = title=="" || city=="" || date=="" || hour=="";
	
	if(nullFields || emptyFields){
		alert("Some mandatory fields were not fulfilled!");
		return false;
	}
	
	return true;
	
}
</script>

<title>Post Help Request</title>
</head>
<body>
<swim:topMenu type="${sessionScope.type}" />


<form name="newhr" action="hr.store" method="post" onsubmit="return mandatory_HR();">
<ul>
	<li>Title: <input type="text" name="title"></li>
	<li>City: <input type="text" name="city"></li>
	<li>Description: <input type="text" name="description"></li>
	<li>Ability: <swim:abilitiesSelect abilities="${abilities}"></swim:abilitiesSelect></li>
	<li>Date:	<input type="date" name="date"></li>
	<li>Hour:	<input type="time" name="hour"></li>
	<li><input type="submit" value="Post"></li>
</ul>
</form>

</body>
</html>