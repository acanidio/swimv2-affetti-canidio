<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new ability</title>
</head>
<body>

<swim:topMenu type="${sessionScope.type}" />

<form action="ability.store" method="get">
	Name of the new ability: <input type="text" name="newability">
	<input type="submit" value="Send">
</form>

</body>
</html>