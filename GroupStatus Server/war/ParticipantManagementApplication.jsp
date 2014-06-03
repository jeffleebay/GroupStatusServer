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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon"
	href="http://getbootstrap.com/assets/ico/favicon.ico">

<title>[Group Status] Participant Database</title>

<!-- Bootstrap core CSS -->
<link href="http://getbootstrap.com/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<!--<link href="http://getbootstrap.com/examples/dashboard/dashboard.css" rel="stylesheet"> -->
<link href="./css/jumbotron-narrow.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	      <![endif]-->
<style type="text/css"></style>
<style id="holderjs-style" type="text/css"></style>
<script type="text/javascript"
	src="chrome-extension://bfbmjmiodbnnpllbbbfblcplfjjepjdn/js/injected.js"></script>
<style>
[touch-action="none"] {
	-ms-touch-action: none;
	touch-action: none;
}

[touch-action="pan-x"] {
	-ms-touch-action: pan-x;
	touch-action: pan-x;
}

[touch-action="pan-y"] {
	-ms-touch-action: pan-y;
	touch-action: pan-y;
}

[touch-action="scroll"],[touch-action="pan-x pan-y"],[touch-action="pan-y pan-x"]
	{
	-ms-touch-action: pan-x pan-y;
	touch-action: pan-x pan-y;
}
</style>
<style id="clearly_highlighting_css" type="text/css">/* selection */
html.clearly_highlighting_enabled ::-moz-selection {
	background: rgba(246, 238, 150, 0.99);
}

html.clearly_highlighting_enabled ::selection {
	background: rgba(246, 238, 150, 0.99);
} /* cursor */
html.clearly_highlighting_enabled {
	/* cursor and hot-spot position -- requires a default cursor, after the URL one */
	cursor:
		url("chrome-extension://pioclpoplcdbaefihamjohnefbikjilc/clearly/images/highlight--cursor.png")
		14 16, text;
} /* highlight tag */
em.clearly_highlight_element {
	font-style: inherit !important;
	font-weight: inherit !important;
	background-image:
		url("chrome-extension://pioclpoplcdbaefihamjohnefbikjilc/clearly/images/highlight--yellow.png");
	background-repeat: repeat-x;
	background-position: top left;
	background-size: 100% 100%;
} /* the delete-buttons are positioned relative to this */
em.clearly_highlight_element.clearly_highlight_first {
	position: relative;
} /* delete buttons */
em.clearly_highlight_element a.clearly_highlight_delete_element {
	display: none;
	cursor: pointer;
	padding: 0;
	margin: 0;
	line-height: 0;
	position: absolute;
	width: 34px;
	height: 34px;
	left: -17px;
	top: -17px;
	background-image:
		url("chrome-extension://pioclpoplcdbaefihamjohnefbikjilc/clearly/images/highlight--delete-sprite.png");
	background-repeat: no-repeat;
	background-position: 0px 0px;
}

em.clearly_highlight_element a.clearly_highlight_delete_element:hover {
	background-position: -34px 0px;
} /* retina */
@media ( min--moz-device-pixel-ratio : 2) , ( -webkit-min-device-pixel-ratio : 2)
		, ( min-device-pixel-ratio : 2) {
	em.clearly_highlight_element {
		background-image:
			url("chrome-extension://pioclpoplcdbaefihamjohnefbikjilc/clearly/images/highlight--yellow@2x.png");
	}
	em.clearly_highlight_element a.clearly_highlight_delete_element {
		background-image:
			url("chrome-extension://pioclpoplcdbaefihamjohnefbikjilc/clearly/images/highlight--delete-sprite@2x.png");
		background-size: 68px 34px;
	}
}
</style>
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

	<!-- Nav bar -->

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
					<li><a href="StatusManagementApplication.jsp">Status
							Management</a></li>
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

	<%
		if (user != null) {
	%>

	<!-- content -->

	<div class="container-fluid">
		<h1 class="page-header">Participants</h1>

		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
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
				</thead>
				<tbody>
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
				</tbody>
			</table>
		</div>

		<div style="text-align: center;">
			<h1>
				<small>You have a total number of <%=userList.size()%>
					participants.
				</small>
			</h1>
		</div>

		<!-- Create a new participant -->

		<h1 class="page-header">Create a new participant</h1>

		<form class="form-horizontal" role="form" action="/new" method="post"
			accept-charset="utf-8">

			<div class="form-group">
				<label for="userID" class="col-sm-2 control-label">User ID</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="userID"
						placeholder="User ID (4 digits)">
				</div>
			</div>

			<div class="form-group">
				<label for="userPW" class="col-sm-2 control-label">User PW</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="userPW"
						placeholder="User PW (4 digits)">
				</div>
			</div>

			<div class="form-group">
				<label for="group" class="col-sm-2 control-label">Group</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="group"
						placeholder="Group">
				</div>
			</div>

			<div class="form-group">
				<label for="type" class="col-sm-2 control-label">Type</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="type"
						placeholder="Type (debugging, testing, experiment)">
				</div>
			</div>

			<div class="form-group">
				<label for="startingDateForExp" class="col-sm-2 control-label">Starting
					Date</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="startingDateForExp"
						placeholder="Starting Date (MMDD) [Experiment only]">
				</div>
			</div>

			<div class="form-group">
				<label for="timeIntervalForExp" class="col-sm-2 control-label">Time
					Interval</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="timeIntervalForExp"
						placeholder="Time Interval (Days) [Experiment only]">
				</div>
			</div>

			<div class="form-group">
				<label for="other" class="col-sm-2 control-label">Other</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="other"
						placeholder="Other">
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Create a new
						participant</button>
				</div>
			</div>

		</form>


		<h1 class="page-header">Create foo participants</h1>

		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown">
				Don't click me if you don't know what you are doing <span
					class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="/fooUser">Create foo users</a></li>
				<li class="divider"></li>
				<li><a href="/fooStatus">Create foo statuses</a></li>
			</ul>
		</div>

		<!-- Footer -->

		<br> <br> <br>

		<footer align="center">
			<p>© Jeff S.C. Lee 2014</p>
		</footer>


		<%
			} else {
		%>

		Please login with your Google account

		<%
			}
		%>

	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="./js/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/docs.min.js"></script>

</body>
</html>
