<%@ attribute name="conv" required="true" type="it.polimi.swim.entities.Conversation" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul>
<c:forEach var="msg" items="${conv.messages}">
<li>
	<p><a href="loaduser.servlet?id=${msg.sender.ID}">${msg.sender.name} ${msg.sender.surname}</a></p>
	<p>${msg.text}</p>
</li>	
</c:forEach>
</ul>