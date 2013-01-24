<%@ attribute name="pendingFR" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table id="pfrships">
	<c:forEach var="fr" items="${pendingFR}">
		<form method="post" action="adfrship.store">
			<tr>
				<td><a href="loaduser.servlet?id=${fr.sender.ID}">${fr.sender.name} ${fr.sender.surname}</a></td>
				<input type="hidden" name="id" value="${fr.ID}"/>
				<input type="hidden" name="frompending" value="true"/>
				<td><input type="submit" name="ans" value="Accept"></td>
				<td><input type="submit" name="ans" value="Decline"></td>
			</tr>
		</form>
	</c:forEach>
</table>