<%@ taglib tagdir="/WEB-INF/tags" prefix="swim"%>

<a href="loadabilities.servlet?path=posthr.view">Post new Help Request</a>
<a href="loadabilities.servlet?path=filterwall.view">Filter...</a>

<swim:hrList helpreqs="${helpreqs}"></swim:hrList>