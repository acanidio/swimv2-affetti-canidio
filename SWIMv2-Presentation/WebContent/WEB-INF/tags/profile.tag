<%@ attribute name="user" required="true" type="it.polimi.swim.entities.Person"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul>
<li>${user.name}</li>
<li>${user.surname}</li>
<li>${user.email}</li>
</ul>

	<ul>
		<c:forEach var="ability" items="${requestScope.userAbilities}">
			<li>${ability}</li>
		</c:forEach>
	</ul>