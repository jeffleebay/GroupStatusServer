<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page
	import="edu.uci.ics.luci.groupstatusserver.userdatabase.UserDAO"%>
<%@ page
	import="edu.uci.ics.luci.groupstatusserver.userdatabase.UserObject"%>

<!DOCTYPE html>


<%@page import="java.util.ArrayList"%>

<html>
<head>
<title>[Group Status] Participant Database</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<meta charset="utf-8">
</head>
<body>

	<%
		UserDAO dao = UserDAO.INSTANCE;

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String url = userService.createLoginURL(request.getRequestURI());
		String urlLinktext = "Login";
		List<UserObject> userList = new ArrayList<UserObject>();

		if (user != null) {
			if (userService.isUserAdmin()) {
				url = userService.createLogoutURL(request.getRequestURI());
				urlLinktext = "Logout";
				userList = dao.getUserList(user.getUserId());
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
			<div style="float: left;" class="headline">Participants</div>
			<div style="float: right;">
				<a href="<%=url%>"><%=urlLinktext%></a>
				<%=(user == null ? "" : user.getNickname())%></div>
		</div>
	</div>
	
	<br>

	<table align="center">
		<tr>
			<th>User ID</th>
			<th>User PW</th>
			<th>Group</th>
			<th>Type</th>
			<th>Starting Date</th>
			<th>Time Interval</th>
			<th>Other</th>
			<th>Remove</th>
		</tr>

		<%
			for (UserObject userobject : userList) {
		%>
		<tr>
			<td><%=userobject.getUserID()%></td>
			<td><%=userobject.getUserPW()%></td>
			<td><%=userobject.getGroup()%></td>
			<td><%=userobject.getType()%></td>
			<td><%=userobject.getStartingDateForExp()%></td>
			<td><%=userobject.geTtimeIntervalForExp()%></td>
			<td><%=userobject.getOther()%></td>
			<td><a class="done" href="/done?id=<%=userobject.getId()%>">Remove</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>

	<br>
	<div style="clear: both;">
		You have a total number of
		<%=userList.size()%>
		participants.
	</div>

	<hr />
	<br>
	
	<div class="main" align="center">

		<%
			if (user != null) {
		%>

		<div class="headline">Create a new participant</div>
<br>
		<!-- userID, userPW, group -->
		<form action="/new" method="post" accept-charset="utf-8">
			<table>
				<tr>
					<td><label for="userID">User ID (4 digits)</label></td>
					<td><input type="text" name="userID" id="userID" size="65" /></td>
				</tr>
				<tr>
					<td><label for="userPW">User PW (4 digits)</label></td>
					<td><input type="text" name="userPW" id="userPW" size="65" /></td>
				</tr>
				<tr>
					<td><label for="group">Belonged group</label></td>
					<td><input type="text" name="group" id="group" size="65" /></td>
				</tr>
				<tr>
					<td><label for="Type">Type (debugging, testing,
							experiment)</label></td>
					<td><input type="text" name="type" id="type" size="65" /></td>
				</tr>
				<tr>
					<td><label for="startingDateForExp">[Experiment]
							Starting Date (MMDD)</label></td>
					<td><input type="text" name="startingDateForExp"
						id="startingDateForExp" size="65" /></td>
				</tr>
				<tr>
					<td><label for="timeIntervalForExp">[Experiment] Time
							Interval (Days)</label></td>
					<td><input type="text" name="timeIntervalForExp"
						id="timeIntervalForExp" size="65" /></td>
				</tr>
				<tr>
					<td><label for="other">Other</label></td>
					<td><input type="text" name="other" id="other" size="65" /></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Create" /></td>
				</tr>
			</table>
		</form>
		<br>
		<form action="/fooUser"  method="post" accept-charset="utf-8">
    		<input type="submit" value="Create foo users">
		</form>
		
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
