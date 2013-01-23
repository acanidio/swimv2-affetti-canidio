<%@ attribute name="conv" required="true" type="it.polimi.swim.entities.Conversation" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="msg" items="${conv.messages}">
	<tr>
		<td><a href="loaduser.servlet?id=${msg.sender.ID}">${msg.sender.name} ${msg.sender.surname}: </a></td>
		<td>${msg.text}</td>
	</tr>	
</c:forEach>