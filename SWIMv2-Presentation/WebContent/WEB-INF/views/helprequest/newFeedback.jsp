<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Feedback</title>
</head>
<body>
<swim:topMenu type="${sessionScope.type}" />


<form action="feedback.store" method="post">
<input type="hidden" value="${replyid}" name="replyid">
<ul>
<li>
<fieldset>
	<legend>Mark:</legend>
	1<input name="mark" type="radio" value="1">
	2<input name="mark" type="radio" value="2">
	3<input name="mark" type="radio" value="3">
	4<input name="mark" type="radio" value="4">
	5<input name="mark" type="radio" value="5" checked="checked">
</fieldset>
</li>

<li>Description: <input type="text" name="description"></li>
<li><input type="submit" value="Send"></li>

</ul>
</form>

</body>
</html>