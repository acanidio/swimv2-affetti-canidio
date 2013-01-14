<%@ attribute name="pendAbilities" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul>
<c:forEach var="ability" items="${pendAbilities}">
	<li>
	<p>${ability.name}</p>
	
	<form method="post" action="adability.store">
	<input type="hidden" name="id" value="${ability.ID}">
	<input type="submit" name="ans" value="Accept">
	<input type="submit" name="ans" value="Decline">
	</form>
	</li>
</c:forEach>
</ul>