<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Feedback</title>
</head>
<body>

<form action="feedback.store?replyid=${replyid}" method="post">
<fieldset >
	<legend>Mark:</legend>
	1<input type="radio" name="1">
	2<input type="radio" name="2">
	3<input type="radio" name="3">
	4<input type="radio" name="4">
	5<input type="radio" name="5">
</fieldset>
Description: <input type="text" name="description">
<input type="submit" value="Send">
</form>

</body>
</html>