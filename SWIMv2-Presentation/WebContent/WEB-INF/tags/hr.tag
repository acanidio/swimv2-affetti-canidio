<%@ attribute name="helpreq" required="true" type="temporaryClasses.HelpRequest"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul>
	<li><a href="loaduser.servlet?id=${helpreq.user.id}">${helpreq.user.name} ${helpreq.user.surname}</a></li>
	<li>${helpreq.subject}</li>
	<li>${helpreq.ability.name}</li>
	<li>${helpreq.date}</li>
	<li>${helpreq.hour}</li>
</ul>


<c:set var="postedbyme" value="false"></c:set>
<c:if test="${helpreq.user.id == sessionScope.user.id}">
	<c:set var="postedbyme" value="true"></c:set>
</c:if>

<c:set var="thereisbr" value="false"></c:set>
<c:forEach var="reply" items="${helpreq.replies}">
	<c:if test="${reply.best == true}">
		<c:set var="thereisbr" value="true"></c:set>
	</c:if>
</c:forEach>

<ul>
<c:forEach var="reply" items="${helpreq.replies}">
	<li><a href="loaduser.servlet?id=${reply.user.id}">${reply.user.name} ${reply.user.surname}</a>
	<c:if test="${flag=='false'}">
		<form action="bestreply.store?replyid=${reply.id}" method="get"><input type="submit" value="Select as Best Reply"></form>
	</c:if>
	
	<c:if test="${flag=='true' && reply.best==true && empty reply.feedback}">
		<form action="newfeed.view?replyid=${reply.id}" method="get"><input type="submit" value="Give a Feedback"></form>
	</c:if>
	</li>
</c:forEach>
</ul>