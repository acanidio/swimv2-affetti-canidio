<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/mycss.css" type="text/css">
<link href="css/custom-theme/jquery-ui-1.10.0.custom.css" rel="stylesheet">
<script src="js/jquery-1.9.0.js"></script>
<script src="js/jquery-ui-1.10.0.custom.js"></script>
<script src="js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript">
	$(function() {
		$( "#datepicker" ).datepicker({
			showOtherMonths: true,
		    selectOtherMonths: true,
		    showButtonPanel: true,
		    dateFormat: "yy-mm-dd",
		    changeMonth: true,
		    changeYear: true,
			inline: true
		});
		// Hover states on the static widgets
		$( "#dialog-link, #icons li" ).hover(
			function() {
				$( this ).addClass( "ui-state-hover" );
			},
			function() {
				$( this ).removeClass( "ui-state-hover" );
			}
		);
		$("#timepicker").timepicker();
	});
</script>
<script type="text/javascript">
function mandatory_HR(){
	var title = document.forms["newhr"]["title"].value;
	var city = document.forms["newhr"]["city"].value;
	var date = document.forms["newhr"]["date"].value;
	var hour = document.forms["newhr"]["hour"].value;
	
	var nullFields = title==null || city==null || date==null || hour==null;
	var emptyFields = title=="" || city=="" || date=="" || hour=="";
	
	if(nullFields || emptyFields){
		alert("Some mandatory fields were not fulfilled!");
		return false;
	}
	
	return true;
	
}
</script>

<title>Post Help Request</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="navigation">
			<swim:topMenu type="${sessionScope.type}"></swim:topMenu>
		</div>
		<div id="content">
			<form name="newhr" action="hr.store" method="post" onsubmit="return mandatory_HR();">
				<fieldset>
					<legend>Help Request Form</legend>
					<table>
						<tr>
							<td>Title:</td>
							<td><input type="text" name="title"></td>
						</tr>
						<tr>
							<td>City:</td>
							<td><input type="text" name="city"></td>
						</tr>
						<tr>
							<td>Description:</td>
							<td>
								<textarea rows="6" cols="17" name="description"></textarea>
							</td>
						</tr>
						<tr>
							<td>Ability:</td>
							<td><swim:abilitiesSelect abilities="${abilities}"></swim:abilitiesSelect></td>
						</tr>
						<tr>
							<td>Date:</td>
							<td><input type="text" id="datepicker" name="date"></td>
						</tr>
						<tr>
							<td>Hour:</td>
							<td><input type="text" id="timepicker" name="hour"></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Post"></td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
		<div id="footer">
			<p>Developed by Affetti Lorenzo and Canidio Andrea</p>
		</div>
	</div>
</body>
</html>