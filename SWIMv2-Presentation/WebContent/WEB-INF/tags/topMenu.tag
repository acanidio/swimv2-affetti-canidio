<%@ attribute name="type" required="true" type="java.lang.String"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${type==''}">
		<c:set var="type" value="GUEST"></c:set>
</c:if>

<ul id="menu">
	
	<li><a href="home.view">HOME</a></li>
	
	<c:if test="${type=='USER'}">
		<li><c:import url="compartments/userNotificationPanel.jsp"></c:import></li>
	</c:if>
	
	<c:if test="${type=='ADMINISTRATOR'}">
		<li><c:import url="compartments/adminNotificationPanel.jsp"></c:import></li>
	</c:if>
	
	<li><c:import url="compartments/searchPanel.jsp"></c:import></li>
	
	<c:if test="${type=='USER'}">
		<li><c:import url="compartments/userMenu.jsp"></c:import></li>
	</c:if>
	
	<c:if test="${type=='ADMINISTRATOR'}">
		<li><c:import url="compartments/adminMenu.jsp"></c:import></li>
	</c:if>
	
	<c:if test="${type=='GUEST'}">
		<li><c:import url="compartments/guestMenu.jsp"></c:import></li>
	</c:if>
	
	<c:if test="${type=='USER' || type=='ADMINISTRATOR'}">
		<li><c:import url="compartments/logOutPanel.jsp"></c:import></li>
	</c:if>
	
	<c:if test="${type=='GUEST'}">
		<li><c:import url="compartments/logInPanel.jsp"></c:import></li>
	</c:if>

</ul>