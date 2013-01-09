<%@ attribute name="abilities" required="true" type="java.util.Collection"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<select name="abilities">
	<c:forEach var="ability" items="${abilities}">
		<option value="${ability.name}">${ability.name}</option>
	</c:forEach>
</select>