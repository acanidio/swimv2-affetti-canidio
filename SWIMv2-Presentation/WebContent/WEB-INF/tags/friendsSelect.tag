<%@ attribute name="friends" required="true" type="java.util.Collection"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<select id="select" name="friends">
	<c:set var="count" value="1"/>
		<c:forEach var="user" items="${friends}">
			<option id="option${count}" value="${user.ID}">${user.name} ${user.surname} - ${user.email}</option>
			<c:set var="count" value="${count+1}"/>
		</c:forEach>
</select>