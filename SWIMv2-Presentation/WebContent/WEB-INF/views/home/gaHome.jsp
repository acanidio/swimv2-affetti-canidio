<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib	tagdir="/WEB-INF/tags" prefix="swim" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/mycss.css" type="text/css">
<title>Welcome to SWIMv2</title>
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
			<c:if test="${empty sessionScope.type}">
				<h1>Small World hypothesIs Machine v2 (SWIMv2)</h1>
				<div></div>
				<div>
					<p>
						This is a Social Network with the purpose of getting in touch people. 
						SWIMv2 allows you to search for people possessing a determinate ability 
						and to search for help contacting them manually. It also let you 
						register and search for help automatically through a help request mechanism.
						As all the famous Social Networks it provides also a friendship mechanism and 
						a message mechanism to improve your expeience<br><br>
						Enjoy your usage as we have enjoyed the development!
					</p>
				</div>
			</c:if>
		</div>
		<swim:footer/>
	</div>
</body>
</html>