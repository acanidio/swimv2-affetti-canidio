<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>
<div class="center">
	<ul>
		<li><a href="loadabilities.servlet?path=posthr.view">Post new Help Request</a></li>
		<li><a href="loadabilities.servlet?path=filterwall.view">Filter...</a></li>
		<li><swim:hrList helpreqs="${helpreqs}"></swim:hrList></li>
	</ul>
</div>