<%@ attribute name="conversations" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table>
	<c:forEach var="conv" items="${conversations}">
		<c:if test="${conv.sender.ID == sessionScope.person.ID }">
			<tr>
				<td><a href="loaduser.servlet?id=${conv.receiver.ID}">${conv.receiver.name} ${conv.receiver.surname}: </a></td>
				<td><a href="expandconv.servlet?id=${conv.ID}">${conv.lastMessage.text}</a></td>
			</tr>
		</c:if>
		<c:if test="${conv.receiver.ID == sessionScope.person.ID }">	
			<tr>
				<td><a href="loaduser.servlet?id=${conv.sender.ID}">${conv.sender.name} ${conv.sender.surname}: </a></td>
				<td><a href="expandconv.servlet?id=${conv.ID}">${conv.lastMessage.text}</a></td>
			</tr>
		</c:if>
	</c:forEach>
</table>