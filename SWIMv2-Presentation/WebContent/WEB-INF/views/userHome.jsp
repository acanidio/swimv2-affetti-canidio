<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib	tagdir="/WEB-INF/tags" prefix="swim" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--<link rel="stylesheet" href="css/mycss.css" type="text/css">-->
<link rel="stylesheet" href="css/temporaryStyle.css" type="text/css">
<title>Welcome to SWIMv2</title>
</head>
<body>

<p align="center">${log}</p>

<div>
	<swim:topMenu type="${sessionScope.type}"></swim:topMenu>
</div>

<c:import url="compartments/wall.jsp"></c:import>

</body>
</html>