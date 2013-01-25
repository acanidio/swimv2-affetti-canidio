<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<html>
<head>

<script type="text/javascript">
function mandatory_filtering(){
	var friends = document.forms["filter"]["friends"].checked;
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
<link rel="stylesheet" href="css/mycss.css" type="text/css">
<title>Filter Wall</title>
</head>
<body onload="addNone();">
	<div id="container">
		<div id="header"></div>
		<div id="navigation">
			<swim:topMenu type="${sessionScope.type}"></swim:topMenu>
		</div>
		<div id="content">
			<form name="filter" action="filterwall.servlet" method="get" onsubmit="return mandatory_filtering();">
				<fieldset>
					<legend>Wall Filtering Form</legend>
					<table>
						<tr>
							<td>Filter by friends' posts: </td>
							<td><input type="checkbox" name="friends" value="true"></td>
						</tr>
						<tr>
							<td>Filter by city:</td>
							<td><input type="text" name="city"></td>
						</tr>
						<tr>
							<td>Filter by ability:</td>
							<td><swim:abilitiesSelect abilities="${requestScope.abilities}"></swim:abilitiesSelect></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Filter"></td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
		<swim:footer/>
	</div>
</body>
</html>