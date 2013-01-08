<%@ attribute name="action" required="true"%>

<form action="${action}" method="post">
<ul>
<li>Name: <input type="text" name="name" value="${sessionScope.user.name}"></li>

<li>Surname: <input type="text" name="surname" value="${sessionScope.user.surname}"></li>

<li>E-mail address: <input type="text" name="email" value="${sessionScope.user.email}"></li>

<li>
<select name="abilities">
	<option value="cooker">Cooker</option>
	<option value="blacksmith">Black-smith</option>
	<option value="plumber">Plumber</option>
</select>
<a href="newability.view">Other...</a>
</li>

<li><input type="submit" value="Send profile information"></li>

</ul>
</form>