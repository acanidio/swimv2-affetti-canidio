<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
function mandatory_Search(){
	var search = document.forms["search"]["username"].value;
	
	if(search==null || search=="" || search=="Username"){
		alert("You have to search for somebody");
		return false;
	}
	
	return true;
}
</script>
<div>
	<form name="search" method="get" action="search.servlet" onsubmit="return mandatory_Search();">
		<ul>
			<li><input type="text" value="Username" style="color:#C0C0C0" name="username" onclick="if(value=='Username'){value=''; style.color='#000000';}" 
																						onblur="if(value==''){value='Username'; style.color='#C0C0C0';}" /></li>
			<li><input type="submit" value="Search" /></li>
			<li><a href="loadabilities.servlet?path=advsearch.view">Advanced Search</a></li>
		</ul>
	</form>
</div>