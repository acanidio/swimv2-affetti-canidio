<%@ attribute name="user" required="true" type="it.polimi.swim.entities.Person"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul>
<li>${user.name}</li>
<li>${user.surname}</li>
<li>${user.email}</li>
<li>${user.phonenumber}</li>
<li>${user.city}</li>
<li>${user.birthday}</li>
</ul>

<c:set var="pending" value=""></c:set>
<ul>
	<c:forEach var="ability" items="${requestScope.userAbilities}">
	<c:if test="${ability.key.pending == true}">
		<c:set var="pending" value="(?)"></c:set>
	</c:if>
		<li>${ability.key.name} ${pending}: ${ability.value}</li>
	</c:forEach>
</ul>