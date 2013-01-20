<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim" %>
<html>
<head>
<script type="text/javascript">
function mandatory(){
	var text = document.forms["message"]["text"].value;
	
	if(text==null || text==""){
		alert("Write some text please!");
		return false
	}
	
	return true;
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conversation</title>
</head>
<body>

<swim:topMenu type="${sessionScope.type}" />

<swim:conv conv="${requestScope.conv}"></swim:conv>

<form name="message" method="post" action="message.store" onsubmit="return mandatory();">
<input type="hidden" name="convid" value="${requestScope.conv.ID}">
<ul>
	<li><input type="text" name="text" height="50" width="200"></li>
	<li><input type="submit" value="Send"></li>
</ul>
</form>

</body>
</html>