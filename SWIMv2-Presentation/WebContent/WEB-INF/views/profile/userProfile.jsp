<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="swim" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/mycss.css" type="text/css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
	    $('span.stars').stars();
	});
	$.fn.stars = function() {
		return $(this).each(function() {
	        // Get the value
	        var val = parseFloat($(this).html());
	        val = Math.round(val * 4) / 4; /* To round to nearest quarter */
	        // Make sure that the value is in 0 - 5 range, multiply to get width
	        var size = Math.max(0, (Math.min(5, val))) * 16;
	        // Create stars holder
	        var $span = $('<span />').width(size);
	        // Replace the numerical value with stars
	        $(this).html($span);
	    });
	}
</script>
<title>${requestScope.user.name}'s profile</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="navigation">
			<swim:topMenu type="${sessionScope.type}"></swim:topMenu>
		</div>
		<div id="content">
			<c:choose>
				<c:when  test="${sessionScope.type=='USER' && requestScope.user.ID==sessionScope.person.ID}">
					<a href="loadabilities.servlet?path=modifyprofile.view">Modify profile</a>
				</c:when>
				<c:otherwise>
					<c:if test="${sessionScope.type=='USER' && requestScope.reqexists==false}">
						<a href="frship.store?id=${requestScope.user.ID}">Add to friends</a>
					</c:if>
					<c:if test="${sessionScope.type=='USER' && requestScope.reqexists==true && requestScope.accepted==false && requestScope.imReceiver == true}">
						<a href="adfrship.store?id=${requestScope.frid}&ans=accept">Accept the friendship request</a>
					</c:if>
				</c:otherwise>
			</c:choose>
			<swim:profile user="${requestScope.user}"></swim:profile>
			<c:if test="${sessionScope.type=='USER' && requestScope.user.ID==sessionScope.person.ID}">
				<a href="loadfriends.servlet?path=friends.view">My Friends</a>
			</c:if>
		</div>
		<div id="footer">
			<p>Developed by Affetti Lorenzo and Canidio Andrea</p>
		</div>
	</div>
</body>
</html>