<%@ attribute name="abilities" required="true" type="java.util.Collection"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<select id="select0" name="ability0">
	<c:set var="count" value="1"/>
		<c:forEach var="ability" items="${abilities}">
			<option id="option${count}" value="${ability.ID}">${ability.name}</option>
			<c:set var="count" value="${count+1}"/>
		</c:forEach>
</select>