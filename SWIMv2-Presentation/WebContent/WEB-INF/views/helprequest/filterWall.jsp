<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filter Wall</title>
</head>
<body>

<swim:topMenu type="${sessionScope.type}" />


<form action="filterwall.servlet" method="get">
<ul>
<li>Filter by friends' posts: <input type="checkbox" name="friends" value="true"></li>
<li>Filter by city: <input type="text" name="city"></li>
<li>Filter by ability: <swim:abilitiesSelect abilities="${requestScope.abilities}"></swim:abilitiesSelect></li>
<li><input type="submit" value="filter"></li>
</ul>

</form>

</body>
</html>