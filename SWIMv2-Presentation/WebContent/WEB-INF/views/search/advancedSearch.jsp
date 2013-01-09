<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Advanced Search</title>
</head>
<body>

	<form method="get" action="search.servlet">
	<ul>
		<li>Username: <input type="text" name="username"></li>
		<li>City: <input type="text" name="city"></li>
		<li>Abilities: 	<swim:abilitiesCheckBox abilities="${abilities}"></swim:abilitiesCheckBox> </li>	
		<li><input type="submit" value="Search"></li>
	</ul>
	</form>

</body>
</html>