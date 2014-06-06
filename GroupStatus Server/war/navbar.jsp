<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<!DOCTYPE html>
<html lang="en">
<head>
</head>

<body>

	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String url = userService.createLoginURL(request.getRequestURI());
		String urlLinktext = "Login";

		if (user != null) {
			if (userService.isUserAdmin()) {
				url = userService.createLogoutURL(request.getRequestURI());
				urlLinktext = "Logout";
			} else {
				String redirectURL = "http://www.yahoo.com";
				response.sendRedirect(redirectURL);
			}
		}
	%>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">Group Status</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="ParticipantManagementApplication.jsp">Participant
							Management</a></li>
					<li><a href="StatusManagementApplication_overview.jsp">Status
							Management</a></li>
					<%
					if (user != null) {
						if (userService.isUserAdmin()) {
					%>
					<li class="dropdown" id="dropdown">
				        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Stress Testing<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/CreateFooUser">Create foo users</a></li>
							<li><a href="/RemoveFooUser">Delete foo users</a></li>
							<li class="divider"></li>
							<li><a href="/CreateFooStatus">Create foo statuses</a></li>
							<li><a href="/RemoveFooStatus">Delete foo statuses</a></li>
						</ul>
					</li>
					<%
						}
					}
					%>
					
				</ul>
				<form class="nav navbar-form navbar-right" role="form">
					<a href="<%=url%>" class="btn btn-success" role="button"><%=urlLinktext%></a>
				</form>
				<%
					if (user != null) {
				%>
				<p class="navbar-text navbar-right"><%=(user == null ? "" : user.getNickname())%></p>
				<%
					}
				%>
			</div>
		</div>
	</div>

</body>
</html>