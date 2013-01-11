<%@ attribute name="action" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="swim" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form action="${action}" method="post">
<ul>
<li>Name: <input type="text" name="name" value="${sessionScope.user.name}"></li>

<li>Surname: <input type="text" name="surname" value="${sessionScope.user.surname}"></li>

<li>Avatar: <input type="file" name="avatar"></li>

<li>Password: <input type="password" name="password"></li>
<li>Confirm Password: <input type="password" name="cpassword"></li>

<li>E-mail address: <input type="text" name="email" value="${sessionScope.user.email}"></li>

<li>City: <input type="text" name="city" value="${sessionScope.user.city}"></li>

<li>Birthday: <input type="date" name="birthday"></li>

<li>Phone number: <input type="text" name="phonenumber"></li>

<c:if test="${action=='storemodif.servlet'}">
<li>
<ul>
	<c:forEach var="ability" items="${sessionScope.user.abilities}">
		<li>${ability.name}</li>
	</c:forEach>
</ul>
</li>
</c:if>

<li>
<swim:abilitiesCheckBox abilities="${abilities}"></swim:abilitiesCheckBox>
<a href="newability.view">Other...</a>
</li>

<li><input type="submit" value="Send profile information"></li>

</ul>
</form>