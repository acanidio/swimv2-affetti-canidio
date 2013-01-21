<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/mycss.css" type="text/css">
<title>Pending Abilities</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="navigation">
			<swim:topMenu type="${sessionScope.type}"></swim:topMenu>
		</div>
		<div id="content">
			<swim:pendAbilities pendAbilities="${pendAbilities}"></swim:pendAbilities>
		</div>
		<div id="footer">
			<p>Developed by Affetti Lorenzo and Canidio Andrea</p>
		</div>
	</div>
</body>
</html>