<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Advanced Search</title>
</head>
<body>

	<form method="get" action="search.servlet">
		Username: <input type="text" name="username">
		City: <input type="text" name="city">
		Abilities: 	<select name="abilities">
						<option value="sample">Sample ability</option>
					</select> <input type="submit" value="Search">
	</form>

</body>
</html>