<%@ attribute name="helpreq" required="true" type="it.polimi.swim.entities.HelpRequest"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul>
	<li><a href="loaduser.servlet?id=${helpreq.sender.ID}">${helpreq.sender.name} ${helpreq.sender.surname}</a></li>
	<li>${helpreq.title}</li>
	<li>${helpreq.city}</li>
	<li>${helpreq.description}</li>
	<li>${helpreq.ability.name}</li>
	<li>${helpreq.date}</li>
	<li>${helpreq.hour}</li>
</ul>

<ul>
<c:if test="${not empty requestScope.bestreply}">
<li><a href="loaduser.servlet?id=${requestScope.bestreply.sender.ID}">${requestScope.bestreply.sender.name} ${requestScope.bestreply.sender.surname}</a>
	<c:if test="${requestScope.hasFeed==false && requestScope.postedByMe==true}">
			<form action="newfeed.view" method="get">
				<input type="hidden" value="${requestScope.bestreply.ID}" name="replyid">
				<input type="submit" value="Give a Feedback">
			</form>
	</c:if>
	<c:if test="${requestScope.hasFeed==true}">
		<span class="stars">${requestScope.bestreply.feedback.mark}</span>
		&nbsp;&nbsp;${helpreq.sender.name} ${helpreq.sender.surname} says: ${requestScope.bestreply.feedback.description}
	</c:if>
</li>
</c:if>




<c:forEach var="reply" items="${helpreq.replies}">
	<li><a href="loaduser.servlet?id=${reply.sender.ID}">${reply.sender.name} ${reply.sender.surname}</a>
	
	<c:if test="${requestScope.postedByMe==true && requestScope.hasBR==false}">
		<form action="bestreply.store" method="post">
		<input type="hidden" value="${reply.ID}" name="replyid">
		<input type="hidden" value="${helpreq.ID}" name="hrid">
		<input type="submit" value="Select as Best Reply">
		</form>
	</c:if>
	</li>
</c:forEach>
</ul>