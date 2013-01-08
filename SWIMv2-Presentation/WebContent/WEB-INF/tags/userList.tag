<%@ attribute name="users" type="java.util.Collection" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul>
<c:forEach var="user" items="${users}">
	
	<li><a href="loaduser.servlet?id=${user.id}">${user.name} ${user.surname}</a></li>
	
</c:forEach>
</ul>