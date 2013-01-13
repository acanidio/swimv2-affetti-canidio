<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Post Help Request</title>
</head>
<body>

<form action="hr.store" method="post">
<ul>
	<li>Title: <input type="text" name="title"></li>
	<li>City: <input type="text" name="city"></li>
	<li>Description: <input type="text" name="description"></li>
	<li>Ability: <swim:abilitiesSelect abilities="${abilities}"></swim:abilitiesSelect></li>
	<li>Date:	<input type="text" name="date"></li>
	<li>Hour:	<input type="text" name="hour"></li>
	<li><input type="submit" value="Post"></li>
</ul>
</form>

</body>
</html>