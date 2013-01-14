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

<c:set var="isfriendof" value="false"/>
<c:forEach var="friendship" items="${requestScope.user.sendedRequests}">
	<c:if test="${friendship.receiver.ID == requestScope.user.ID}">
		<c:set var="isfriendof" value="true"></c:set>
	</c:if>
</c:forEach>

<c:if test="${isfriendof=='false'}">
<c:set var="received" value="false"/>
<c:forEach var="friendship" items="${requestScope.user.receivedRequests}">
	<c:if test="${friendship.sender.ID == requestScope.user.ID}">
		<c:set var="received" value="true"></c:set>
		<c:set var="receivedID" value="${friendship.ID}"></c:set>
	</c:if>
</c:forEach>
</c:if>


<c:choose>
	<c:when  test="${requestScope.user.ID==sessionScope.person.ID}">
		<a href="loadabilities.servlet?path=modifyprofile.view">Modify profile</a>
	</c:when>
	
	<c:otherwise>
		<c:if test="${sessionScope.type=='USER' && isfriendof=='false' && received=='false'}">
			<a href="frship.store?id=${requestScope.user.ID}">Add to friends</a>
		</c:if>
		<c:if test="${sessionScope.type=='USER' && isfriendof=='false' && received=='true'}">
			<a href="adfrship.store?id=${receivedID}">Accept the friendship request</a>
		</c:if>
	</c:otherwise>
</c:choose>

<swim:profile user="${requestScope.user}"></swim:profile>


<p></p>
</body>
</html>