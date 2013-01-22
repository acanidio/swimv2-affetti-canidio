<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/mycss.css" type="text/css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script>
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
<title>Help request</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="navigation">
			<swim:topMenu type="${sessionScope.type}"></swim:topMenu>
		</div>
		<div id="content">
			<swim:hr helpreq="${hr}"></swim:hr>
			<c:if test="${requestScope.canReply == true}">
				<form action="reply.store" method="post">
					<input type="hidden" value="${hr.ID}" name="hrid">
					<input type="submit" value="Reply">
				</form>
			</c:if>
		</div>
		<div id="footer">
			<p>Developed by Affetti Lorenzo and Canidio Andrea</p>
		</div>
	</div>
</body>
</html>