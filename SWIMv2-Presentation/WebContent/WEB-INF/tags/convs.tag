<%@ attribute name="conversations" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul>
<c:forEach var="conv" items="${conversations}">
<c:if test="${conv.sender.ID == sessionScope.person.ID }">
<li>
	<p><a href="loaduser.servlet?id=${conv.receiver.ID}">${conv.receiver.name} ${conv.receiver.surname}</a></p>
	<p><a href="expandconv.servlet?id=${conv.ID}">${conv.lastMessage.text}</a></p>
</li>
</c:if>

<c:if test="${conv.receiver.ID == sessionScope.person.ID }">	
<li>
	<p><a href="loaduser.servlet?id=${conv.sender.ID}">${conv.sender.name} ${conv.sender.surname}</a></p>
	<p><a href="expandconv.servlet?id=${conv.ID}">${conv.lastMessage.text}</a></p>
</li>
</c:if>
</c:forEach>
</ul>