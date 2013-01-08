<%@ attribute name="conversations" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul>
<c:forEach var="conv" items="${convs}">
<li>
	<p><a href="loaduser.servlet?id=${conv.user.id}">${conv.user.name} ${conv.user.surname}</a></p>
	<p><a href="expandconv.servlet?id=${conv.user.id}">${conv.message}</a></p>
</li>	
</c:forEach>
</ul>