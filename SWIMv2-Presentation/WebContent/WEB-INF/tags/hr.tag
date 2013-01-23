<%@ attribute name="helpreq" required="true" type="it.polimi.swim.entities.HelpRequest"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
	<tr>
		<td>Title:</td>
		<td>${helpreq.title}</td>
	</tr>
	<tr>
		<td>Author: </td>
		<td><a href="loaduser.servlet?id=${helpreq.sender.ID}">${helpreq.sender.name} ${helpreq.sender.surname}</a></td>
	</tr>
	<tr>
		<td>Place:</td>
		<td>${helpreq.city}</td>
	</tr>
	<tr>
		<td>Description:</td>
		<td>${helpreq.description}</td>
	</tr>
	<tr>
		<td>Requested Ability:</td>
		<td>${helpreq.ability.name}</td>
	</tr>
	<tr>
		<td>Date:</td>
		<td>${helpreq.date}</td>
	</tr>
	<tr>
		<td>Hour:</td>
		<td>${helpreq.hour}</td>
	</tr>
	<c:if test="${not empty requestScope.bestreply}">
		<tr>
			<td><a href="loaduser.servlet?id=${requestScope.bestreply.sender.ID}">${requestScope.bestreply.sender.name} ${requestScope.bestreply.sender.surname}</a></td>
				<c:if test="${requestScope.hasFeed==false && requestScope.postedByMe==true}">
					<form action="newfeed.view" method="get">
						<input type="hidden" value="${requestScope.bestreply.ID}" name="replyid">
						<td><input type="submit" value="Give a Feedback"></td>
					</form>
				</c:if>
				<c:if test="${requestScope.hasFeed==true}">
					<td><span class="stars">${requestScope.bestreply.feedback.mark}</span></td></tr>
					<tr><td>${helpreq.sender.name} ${helpreq.sender.surname} says:</td><td>${requestScope.bestreply.feedback.description}</td>
				</c:if>
		</tr>
	</c:if>
	<c:if test="${requestScope.canReply == true}">
		<form action="reply.store" method="post">
			<input type="hidden" value="${hr.ID}" name="hrid">
			<tr>
				<td></td>
				<td><input type="submit" value="Reply"></td>
			</tr>
		</form>
	</c:if>
	<c:forEach var="reply" items="${helpreq.replies}">
		<tr>
			<td><a href="loaduser.servlet?id=${reply.sender.ID}">${reply.sender.name} ${reply.sender.surname}</a></td>
			<c:if test="${requestScope.postedByMe==true && requestScope.hasBR==false}">
				<form action="bestreply.store" method="post">
				<input type="hidden" value="${reply.ID}" name="replyid">
				<input type="hidden" value="${helpreq.ID}" name="hrid">
				<td><input type="submit" value="Select as Best Reply"></td>
				</form>
			</c:if>
		</tr>
	</c:forEach>
</table>