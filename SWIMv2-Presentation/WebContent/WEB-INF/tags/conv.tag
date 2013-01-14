<%@ attribute name="messages" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul>
<c:forEach var="msg" items="${messages}">
<li>
	<p><a href="loaduser.servlet?id=${msg.sender.ID}">${msg.sender.name} ${msg.sender.surname}</a></p>
	<p>${msg.text}</p>
</li>	
</c:forEach>
</ul>