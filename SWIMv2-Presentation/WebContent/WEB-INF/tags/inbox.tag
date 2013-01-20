<%@ attribute name="incomingmsgs" required="true"
	type="java.util.Collection"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul>
	<c:forEach var="msg" items="${incomingmsgs}">
		<li>
			<a href="loaduser.servlet?id=${msg.sender.ID}">${msg.sender.name} ${msg.sender.surname}</a><br>
			<a href="expandconv.servlet?id=${msg.conversation.ID}">${msg.text}</a>
		</li>
	</c:forEach>
</ul>