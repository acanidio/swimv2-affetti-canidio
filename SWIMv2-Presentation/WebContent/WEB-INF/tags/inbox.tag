<%@ attribute name="incomingmsgs" required="true"
	type="java.util.Collection"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul>
	<c:forEach var="msg" items="${messages}">
		<li>
			<p>
				<a href="loaduser.servlet?id=${msg.fromU.id}">${msg.fromU.name}
					${msg.fromU.surname}</a>
			</p>
			<p>
				<a href="expandconv.servlet?id=${msg.fromU.id}">${msg.text}</a>
			</p>
		</li>
	</c:forEach>
</ul>