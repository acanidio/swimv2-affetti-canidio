<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Help request</title>
</head>
<body>

<swim:hr helpreq="${hr}"></swim:hr>

<c:if test="${not (hr.user.id == sessionScope.user.id)}">
	<form action="reply.store?hrid=${hr.id}" method="get"><input type="submit" value="Reply"></form>
</c:if>

</body>
</html>