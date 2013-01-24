<%@ attribute name="user" required="true" type="it.polimi.swim.entities.Person"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty requestScope.log}">
	<h3>${requestScope.log}</h3>
</c:if>
<h3>${user.name} ${user.surname}</h3>
<table>
	<tr>
		<td>E-mail Address: </td>
		<td>${user.email}</td>
	</tr>
	<tr>
		<td>Phone Number: </td>
		<td>${user.phonenumber}</td>
	</tr>
	<tr>
		<td>City: </td>
		<td>${user.city}</td>
	</tr>
	<tr>
		<td>Birthday: </td>
		<td>${user.birthday}</td>
	</tr>
	<c:set var="pending" value=""></c:set>
	<c:set var="counter" value="1"></c:set>
	<c:forEach var="ability" items="${requestScope.userAbilities}">
		<tr>
			<td>
				<c:if test="${counter == '1'}">
					Abilities:
					<c:set var="counter" value="0"></c:set> 
				</c:if>
			</td>
			<td>
				<c:if test="${ability.key.pending == true}">
					<c:set var="pending" value="(?)"></c:set>
				</c:if>
				${ability.key.name} ${pending}: 
			</td>
			<td>
				<span class="stars">${ability.value}</span>
			</td>
		</tr>
		<c:set var="pending" value=""></c:set>
	</c:forEach>
</table>
