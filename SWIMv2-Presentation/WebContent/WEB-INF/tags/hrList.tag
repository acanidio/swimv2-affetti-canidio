<%@ attribute name="helpreqs" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul id="helprequests">
<c:forEach var="helpreq" items="${helpreqs}">
	<li>
		<ul>
			<li><a href="loaduser.servlet?id=${helpreq.user.id}">${helpreq.user.name} ${helpreq.user.surname}</a></li>
			<li><a href="loadhr.servlet?id=${helpreq.id}">${helpreq.subject}</a></li>
			<li>${helpreq.ability.name}</li>
			<li>${helpreq.date}</li>
			<li>${helpreq.hour}</li>
		</ul>
	</li>
</c:forEach>
</ul>
