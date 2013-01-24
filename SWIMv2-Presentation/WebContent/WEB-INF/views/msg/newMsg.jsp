<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
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
<link rel="stylesheet" href="css/mycss.css" type="text/css">
<title>New message</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="navigation">
			<swim:topMenu type="${sessionScope.type}"></swim:topMenu>
		</div>
		<div id="content">
			<form name="message" action="message.store" method="post" onsubmit="return mandatory();">
				<table>
					<tr>
						<td>To: </td>
						<td><swim:friendsSelect friends="${friends}"></swim:friendsSelect></td>
					</tr>
					<tr>
						<td>Message: </td>
						<td><input type="text" name="text" height="50" width="200"></td>
						<td><input type="submit" value="Send"></td>
					</tr>
				</table>
			</form>
		</div>
		<swim:footer/>
	</div>
</body>
</html>