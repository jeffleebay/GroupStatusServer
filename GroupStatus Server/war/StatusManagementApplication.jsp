<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page
	import="edu.uci.ics.luci.groupstatusserver.statusdatabase.StatusDAO"%>
<%@ page
	import="edu.uci.ics.luci.groupstatusserver.statusdatabase.StatusObject"%>

<!DOCTYPE html>


<%@page import="java.util.ArrayList"%>

<html>
<head>
<title>[Group Status] Status Database</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<meta charset="utf-8">
</head>
<body>

	<%
		StatusDAO dao = StatusDAO.INSTANCE;

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String url = userService.createLoginURL(request.getRequestURI());
		String urlLinktext = "Login";
		List<StatusObject> statusList = new ArrayList<StatusObject>();

		if (user != null) {
			if (userService.isUserAdmin()) {
				url = userService.createLogoutURL(request.getRequestURI());
				urlLinktext = "Logout";
				statusList = dao.getSortedStatusList(user.getUserId());
			} else {
				String redirectURL = "http://www.yahoo.com";
				response.sendRedirect(redirectURL);
			}
		}
	%>
	<div style="width: 100%;">
		<div class="line"></div>
		<div class="topLine">
			<div style="float: left;">
				<img src="images/icon.png" height="15" width="15" />
			</div>
			<div style="float: left;" class="headline">Statuses</div>
			<div style="float: right;">
				<a href="<%=url%>"><%=urlLinktext%></a>
				<%=(user == null ? "" : user.getNickname())%></div>
		</div>
	</div>
	
	<br>

	<table align="center">
		<tr>
			<th>Group</th>
			<th>User ID</th>
			<th>Time stamp</th>
			<th>Status</th>
			<th>Group Status</th>
		</tr>

		<%
			for (StatusObject statusobject : statusList) {
		%>
		<tr>
			<td><%=statusobject.getGroup()%></td>
			<td><%=statusobject.getUserID()%></td>
			<td><%=statusobject.getTimestamp()%></td>
			<td><%=statusobject.getStatus()%></td>
			<td><%=statusobject.getGroupStatus()%></td>
		</tr>
		<%
			}
		%>
	</table>

	<br>
	<div style="clear: both;">
		You have a total number of
		<%=statusList.size()%>
		statuses.
	</div>

	<hr />
	<br>
	
	<div class="main" align="center">

		<%
			if (user != null) {
		%>

		<br>
		<form action="/fooStatus"  method="post" accept-charset="utf-8">
    		<input type="submit" value="Create foo statuses">
		</form>
		<br>

		<%
			} else {
		%>

		Please login with your Google account

		<%
			}
		%>
	</div>
</body>
</html>
