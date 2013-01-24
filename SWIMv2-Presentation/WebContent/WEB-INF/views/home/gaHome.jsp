<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib	tagdir="/WEB-INF/tags" prefix="swim" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/mycss.css" type="text/css">
<title>Welcome to SWIMv2</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="navigation">
			<swim:topMenu type="${sessionScope.type}"></swim:topMenu>
		</div>
		<div id="content">
			<c:if test="${not empty requestScope.log}">
				<h3>${requestScope.log}</h3>
			</c:if>
			<c:if test="${empty sessionScope.type}">
				<h1>Small World hypothesIs Machine v2 (SWIMv2)</h1>
				<div></div>
				<div>
					<p>
						SWIMv2 is the link between a person and his needs.<br>We tried to develop this platform in order to provide the best way
						to find help in the web.<br>For instance, have you ever got into these situations?<br><br>
						
						My web site leaks in design.. I absolutely need a web designer! But who?<br>
						My piano is out of tune... But who can help me?<br>
						I want to organize a big party, but which is the best catering service in town?<br><br>
						
						Now, with SWIMv2, you can solve these problems in few clicks!<br>
						You can post a new help request that will be seen worldwide.<br>
						Thanks to the feedback mechanism you will have the possibility to choose among a lot of answers the one you think it is the best<br>
						and, in turn, give a feedback standing on the performance received.<br>
						You can also search for jobs that fits you among the help requested posted by anyone in the world and contact your friends directly through the platform.<br><br>
						
						Enjoy SWIMv2 experience as we have enjoyed the development!
					</p>
				</div>
			</c:if>
		</div>
		<swim:footer/>
	</div>
</body>
</html>