<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<html>
<head>
<script type="text/javascript">
function mandatory_advSearch(){
	var username = document.forms["advsearch"]["username"].value;
	var city = document.forms["advsearch"]["city"].value;
	var ability = document.forms["advsearch"]["ability0"].value;
	
	var emptyFields = (username==null || username=="") && (city==null || city=="") && ability=="none";
	
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
<title>Advanced Search</title>
</head>
<body onload="addNone();">
	<div id="container">
		<div id="header"></div>
		<div id="navigation">
			<swim:topMenu type="${sessionScope.type}"></swim:topMenu>
		</div>
		<div id="content">
			<form name="advsearch" method="get" action="search.servlet" onsubmit="return mandatory_advSearch();">
				<fieldset>
					<legend>Advanced Search</legend>
					<table>
						<tr>
							<td>Username: </td>
							<td><input type="text" name="username"></td>
						</tr>
						<tr>
							<td>City: </td>
							<td><input type="text" name="city"></td>
						</tr>
						<tr>
							<td>Ability:</td>
							<td><swim:abilitiesSelect abilities="${requestScope.abilities}"></swim:abilitiesSelect></td>
						</tr>	
						<tr>
							<td></td>
							<td><input type="submit" value="Search"></td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
		<swim:footer/>
	</div>
</body>
</html>