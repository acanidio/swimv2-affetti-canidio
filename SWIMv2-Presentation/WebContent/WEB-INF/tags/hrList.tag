<%@ attribute name="helpreqs" required="true" type="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="helprequests">
<c:forEach var="helpreq" items="${helpreqs}">
	<div id="helprequest">
		<table>
			<tr>
				<td><a href="loaduser.servlet?id=${helpreq.sender.ID}">${helpreq.sender.name} ${helpreq.sender.surname}</a></td>
				<td>${helpreq.ability.name}</td>
			</tr>
			<tr>
				<td></td>
				<td>${helpreq.date}</td>
			</tr>
			<tr>
				<td><a href="loadhr.servlet?id=${helpreq.ID}">${helpreq.title}</a></td>
				<td>${helpreq.hour}</td>
			</tr>
		</table>
	</div>
</c:forEach>
</div>
