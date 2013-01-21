<%@ attribute name="helpreqs" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="helprequests">
<c:forEach var="helpreq" items="${helpreqs}">
	<div id="helprequest">
		<div>
			<ul>
				<li><a href="loaduser.servlet?id=${helpreq.sender.ID}">${helpreq.sender.name} ${helpreq.sender.surname}</a></li>
				<li><a href="loadhr.servlet?id=${helpreq.ID}">${helpreq.title}</a></li>
			</ul>
		</div>
		<div>
			<ul>
				<li>${helpreq.ability.name}</li>
				<li>${helpreq.date}</li>
				<li>${helpreq.hour}</li>
			</ul>
		</div>
	</div>
</c:forEach>
</div>
