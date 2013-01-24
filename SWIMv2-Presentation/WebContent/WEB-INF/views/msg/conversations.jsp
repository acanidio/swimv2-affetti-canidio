<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/mycss.css" type="text/css">
<title>Personal Conversations</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="navigation">
			<swim:topMenu type="${sessionScope.type}"></swim:topMenu>
		</div>
		<div id="content">
			<c:if test="${not empty requestScope.log}">
				<h3>${requestScope.log}</h3>
			</c:if>
			<swim:convs conversations="${convs}"></swim:convs>
			<br>
			<br>
			<a href="loadfriends.servlet?path=newmsg.view">Send New Message</a>
		</div>
		<swim:footer/>
	</div>
</body>
</html>