<%@ attribute name="pendingFR" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul id="pfrships">
<c:forEach var="fr" items="${pendingFR}">
	<li>
	<p><a href="loaduser.servlet">${fr.fromU.name} ${fr.fromU.surname}</a></p>
	<input type="hidden" name="id" value="${fr.fromU.id}">
	<form method="post" action="adfrship.store?id=${fr.id}">
	<input type="submit" name="ans" value="Accept">
	<input type="submit" name="ans" value="Decline">
	</form>
	</li>
</c:forEach>
</ul>