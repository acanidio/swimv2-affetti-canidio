<%@ attribute name="pendAbilities" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul>
<c:forEach var="ability" items="${pendAbilities}">
	<li>
	<p>${ability.name}</p>
	
	<form method="get" action="answertoAbility.servlet?ans=accept">
	<input type="submit" value="Accept">
	</form>
	
	<form method="get" action="answertoAbility.servlet?ans=decline">
	<input type="submit" value="Decline">
	</form>
	</li>
</c:forEach>
</ul>