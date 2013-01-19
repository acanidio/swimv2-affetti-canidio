<%@ attribute name="pendingFR" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul id="pfrships">
<c:forEach var="fr" items="${pendingFR}">
	<li>
	<p><a href="loaduser.servlet?id=${fr.sender.ID}">${fr.sender.name} ${fr.sender.surname}</a></p>
	
	<form method="post" action="adfrship.store">
	<input type="hidden" name="id" value="${fr.ID}">
	<input type="submit" name="ans" value="Accept">
	<input type="submit" name="ans" value="Decline">
	</form>
	</li>
</c:forEach>
</ul>