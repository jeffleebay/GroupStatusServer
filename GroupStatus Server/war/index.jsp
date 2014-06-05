<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<!DOCTYPE html>
<!-- saved from url=(0043)http://getbootstrap.com/examples/dashboard/ -->
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="shortcut icon"
		href="http://getbootstrap.com/assets/ico/favicon.ico">
	
	<title>Group Status Console</title>
	
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
					<li class="dropdown">
				        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Stress Testing<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/fooUser">Create foo users</a></li>
							<li class="divider"></li>
							<li><a href="/fooStatus">Create foo statuses</a></li>
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

	<br>
	<br>
	<br>
	<div class="container">
		<div class="jumbotron">
			<h1>Group Status</h1>
			<p>Welcome to researcher console.</p>
			<p>There are two management systems available now.</p>
		</div>
	</div>
	<hr>
	<footer align="center">
        <p>© Jeff S.C. Lee 2014</p>
      </footer>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="./js/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/docs.min.js"></script>


</body>
</html>