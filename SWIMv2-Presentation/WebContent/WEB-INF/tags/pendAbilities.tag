<%@ attribute name="pendAbilities" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table>
	<c:forEach var="ability" items="${pendAbilities}">
		<form method="post" action="adability.store">
			<input type="hidden" name="id" value="${ability.ID}">
			<tr>
				<td>${ability.name}</td>
				<td><input type="submit" name="ans" value="Accept"></td>
				<td><input type="submit" name="ans" value="Decline"></td>
			</tr>
		</form>
	</c:forEach>
</table>