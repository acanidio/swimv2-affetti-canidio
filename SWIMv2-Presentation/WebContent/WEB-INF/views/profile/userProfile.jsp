<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="swim" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${requestScope.user.name}'s profile</title>
</head>
<body>
<swim:topMenu type="${sessionScope.type}" />

<c:choose>
	<c:when  test="${requestScope.user.ID==sessionScope.person.ID}">
		<a href="loadabilities.servlet?path=modifyprofile.view">Modify profile</a>
	</c:when>
	
	<c:otherwise>
		<c:if test="${sessionScope.type=='USER' && requestScope.reqexists==false}">
			<a href="frship.store?id=${requestScope.user.ID}">Add to friends</a>
		</c:if>
		<c:if test="${sessionScope.type=='USER' && requestScope.reqexists==true && requestScope.accepted==false}">
			<a href="adfrship.store?id=${requestScope.frid}">Accept the friendship request</a>
		</c:if>
	</c:otherwise>
</c:choose>

<swim:profile user="${requestScope.user}"></swim:profile>


<p></p>
</body>
</html>