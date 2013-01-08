<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personal Conversations</title>
</head>
<body>

<h1>Personal Conversations</h1> <br>

<swim:convs conversations="${convs}"></swim:convs>

<br><br>

<a href="newmsg.view">Send New Message</a>

</body>
</html>