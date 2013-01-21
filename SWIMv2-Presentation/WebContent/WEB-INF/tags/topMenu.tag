<%@ attribute name="type" required="true" type="java.lang.String"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${type==''}">
	<c:set var="type" value="GUEST"></c:set>
</c:if>
<c:if test="${type=='GUEST'}">
			<script type="text/javascript">
				function mandatory_Search(){
					var search = document.forms["search"]["username"].value;
					
					if(search==null || search=="" || search=="Username"){
						alert("You have to search for somebody");
						return false;
					}
					
					return true;
				}
				
				function checkFields(){
					var email = document.forms["login"]["email"].value;
					var password =  document.forms["login"]["password"].value;
					var nullField = email==null || password==null;
					var emptyField = email=="" || password=="";
					
					if(nullField || emptyField){
						alert("Missing some fields!");
						return false;
					}
				}
			</script>
			<div class="external"><a href="home.view">HOME</a></div>
			<div class="external">
				<form name="search" method="get" action="search.servlet" onsubmit="return mandatory_Search();">
					<div><input type="text" value="Username" style="color:#C0C0C0" name="username" onclick="if(value=='Username'){value=''; style.color='#000000';}" 
																								onblur="if(value==''){value='Username'; style.color='#C0C0C0';}" /></div>
					<div><input type="submit" value="Search" /></div>
					<div><a href="loadabilities.servlet?path=advsearch.view">Advanced Search</a></div>
				</form>
			</div>
			<div class="external"><a href="loadabilities.servlet?path=signup.view">Sign up</a></div>
			<div class="external">
				<form name="login" action="login.servlet" method="post" onsubmit="return checkFields();">
					<div>E-mail address: <input type="text" name="email"></div>
					<div>Password: <input type="password" name="password"></div>
					<div><input type="submit" value="Log In"></div>
				</form>
			</div>
</c:if>
<c:if test="${type=='USER'}">
	<c:import url="/WEB-INF/views/compartments/userNotificationPanel.jsp"></c:import>
</c:if>
<c:if test="${type=='ADMINISTRATOR'}">
	<c:import url="/WEB-INF/views/compartments/adminNotificationPanel.jsp"></c:import>
</c:if>
<c:if test="${type=='USER'}">
	<c:import url="/WEB-INF/views/compartments/userMenu.jsp"></c:import>
</c:if>
<c:if test="${type=='ADMINISTRATOR'}">
	<c:import url="/WEB-INF/views/compartments/adminMenu.jsp"></c:import>
</c:if>
<c:if test="${type=='USER' || type=='ADMINISTRATOR'}">
	<c:import url="/WEB-INF/views/compartments/logOutPanel.jsp"></c:import>
</c:if>