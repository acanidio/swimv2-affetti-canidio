<%@ attribute name="conversations" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="role" value="sender"></c:set>
<c:if test="${requestScope.issender==false}">
	<c:set var="role" value="receiver"></c:set>
</c:if>

<c:if test="${role=='sender'}">
<ul>
<c:forEach var="conv" items="${convs}">
<li>
	<p><a href="loaduser.servlet?id=${conv.receiver.ID}">${conv.receiver.name} ${conv.receiver.surname}</a></p>
	<p><a href="expandconv.servlet?id=${conv.ID}">${conv.message.lastmessage.text}</a></p>
</li>	
</c:forEach>
</ul>
</c:if>

<c:if test="${role=='receiver'}">
<ul>
<c:forEach var="conv" items="${convs}">
<li>
	<p><a href="loaduser.servlet?id=${conv.sender.ID}">${conv.sender.name} ${conv.sender.surname}</a></p>
	<p><a href="expandconv.servlet?id=${conv.ID}">${conv.lastmessage.text}</a></p>
</li>	
</c:forEach>
</ul>
</c:if>