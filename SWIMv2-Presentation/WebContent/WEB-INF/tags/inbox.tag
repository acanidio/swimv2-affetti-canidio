<%@ attribute name="incomingmsgs" required="true"
	type="java.util.Collection"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
	<c:forEach var="msg" items="${incomingmsgs}">
		<tr>
			<td><a href="loaduser.servlet?id=${msg.sender.ID}">${msg.sender.name} ${msg.sender.surname}: </a></td>
			<td><a href="expandconv.servlet?id=${msg.conversation.ID}">${msg.text}</a></td>
		</tr>
	</c:forEach>
</table>