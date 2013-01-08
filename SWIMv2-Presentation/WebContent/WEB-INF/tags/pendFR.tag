<%@ attribute name="pendingFR" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul>
<c:forEach var="fr" items="${pendingFR}">
	<li>
	<p><a href="loaduser.servlet?id=${fr.fromU.id}">${fr.fromU.name} ${fr.fromU.surname}</a></p>
	
	<form method="get" action="answertoFR.servlet?ans=accept">
	<input type="submit" value="Accept">
	</form>
	
	<form method="get" action="answertoFR.servlet?ans=decline">
	<input type="submit" value="Decline">
	</form>
	</li>
</c:forEach>
</ul>