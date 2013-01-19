<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
function mandatoryFields(){
	var search = document.forms["search"]["username"].value;
	
	if(search==null || search=="" || search=="Username"){
		alert("You have to search for somebody");
		return false;
	}
	
	return true;
}
</script>

<form name="search" method="get" action="search.servlet" onsubmit="return mandatoryFields();">
	<input type="text" value="Username" style="color:#C0C0C0" name="username" onclick="if(value=='Username'){value=''; style.color='#000000';}" 
																				onblur="if(value==''){value='Username'; style.color='#C0C0C0';}" />
	<input type="submit" value="Search" />
</form>
<a href="loadabilities.servlet?path=advsearch.view">Advanced Search</a>