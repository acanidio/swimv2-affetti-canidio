<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

<script type="text/javascript">
function mandatory_ability(){
	var ability = document.forms["newability"]["newability"].value;
	
	if(ability==null || ability==""){
		alert("You must specify the name!");
		return false;
	}
	
	return true;
}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/mycss.css" type="text/css">
<title>Add new ability</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="navigation">
			<swim:topMenu type="${sessionScope.type}"></swim:topMenu>
		</div>
		<div id="content">
			<h3>Abilities already in SWIM</h3>
			<table>
				<c:forEach var="ability" items="${requestScope.abilities}">
					<tr>
						<td>${ability.name}</td>
					</tr>
				</c:forEach>
			</table>
			<form name="newability" action="ability.store" method="get" onsubmit="return mandatory_ability();">
				<table>
					<tr>
						<td>Name of the new ability:</td>
						<td><input type="text" name="newability"></td>
						<td><input type="submit" value="Send"></td>
					</tr>
				</table>
			</form>
		</div>
		<swim:footer/>
	</div>
</body>
</html>