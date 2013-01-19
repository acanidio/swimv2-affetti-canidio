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
	<c:if test="${requestScope.hasFeed==false}">
			<form action="newfeed.view?replyid=${reply.ID}" method="get"><input type="submit" value="Give a Feedback"></form>
	</c:if>
</li>
</c:if>




<c:forEach var="reply" items="${helpreq.replies}">
	<li><a href="loaduser.servlet?id=${reply.sender.ID}">${reply.sender.name} ${reply.sender.surname}</a>
	
	<c:if test="${requestScope.postedByMe==true && requestScope.hasBR==false}">
		<form action="bestreply.store?replyid=${reply.ID}" method="get"><input type="submit" value="Select as Best Reply"></form>
	</c:if>
	</li>
</c:forEach>
</ul>