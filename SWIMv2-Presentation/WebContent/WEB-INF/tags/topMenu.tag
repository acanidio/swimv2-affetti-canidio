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
					var emptyField = email=="E-mail Address" || password=="Password";
					
					if(nullField || emptyField){
						alert("Missing some fields!");
						return false;
					}
				}
			</script>
			<div class="external"><a href="home.view">Home</a></div>
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
					<div>
						<input type="text" value="E-mail Address" style="color:#C0C0C0" name="email" onclick="if(value=='E-mail Address'){value=''; style.color='#000000';}" 
																			onblur="if(value==''){value='E-mail Address'; style.color='#C0C0C0';}" />
					</div>
					<div>
						<input type="password" value="Password" style="color:#C0C0C0" name="password" onclick="if(value=='Password'){value=''; style.color='#000000';}" 
																			onblur="if(value==''){value='Password'; style.color='#C0C0C0';}" />
					</div>
					<div><input type="submit" value="Log In"></div>
				</form>
			</div>
</c:if>
<c:if test="${type=='USER'}">
			<div class="external"><a href="home.view">Home</a></div>
			<div class="external">
				<div><a href="pfrships.servlet">New Friendships</a></div>
				<div><a href="pmessages.servlet">New Messages</a></div>
			</div>
			<div class="external">
				<form name="search" method="get" action="search.servlet" onsubmit="return mandatory_Search();">
					<div><input type="text" value="Username" style="color:#C0C0C0" name="username" onclick="if(value=='Username'){value=''; style.color='#000000';}" 
																								onblur="if(value==''){value='Username'; style.color='#C0C0C0';}" /></div>
					<div><input type="submit" value="Search" /></div>
					<div><a href="loadabilities.servlet?path=advsearch.view">Advanced Search</a></div>
				</form>
			</div>
			<div class="external">
				<div><a href="loaduser.servlet?id=${sessionScope.person.ID}">${sessionScope.person.name} ${sessionScope.person.surname}</a></div>
				<div><a href="myhrs.servlet">Help Requests</a></div>
				<div><a href="conversations.servlet">Conversations</a></div>
			</div>
			<div class="external"><a href="logout.servlet">Log Out</a></div>
</c:if>
<c:if test="${type=='ADMINISTRATOR'}">
			<div class="external"><a href="home.view">Home</a></div>
			<div class="external"><a href="pabilities.servlet">Pending Abilities</a></div>
			<div class="external"><a href="newability.view">Add New Ability</a></div>
			<div class="external"><a href="logout.servlet">Log Out</a></div>
</c:if>