<%@ attribute name="users" type="java.util.Collection" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
	<c:forEach var="user" items="${users}">
		<tr>
			<td><a href="loaduser.servlet?id=${user.ID}">${user.name} ${user.surname}</a></td>
		</tr>
	</c:forEach>
</table>