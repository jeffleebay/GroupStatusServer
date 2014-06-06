<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.lang.System"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page
	import="edu.uci.ics.luci.groupstatusserver.statusdatabase.StatusDAO"%>
<%@ page
	import="edu.uci.ics.luci.groupstatusserver.statusdatabase.StatusObject"%>
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

<title>[Group Status] Status Database</title>

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
<script type="text/javascript" src="chrome-extension://bfbmjmiodbnnpllbbbfblcplfjjepjdn/js/injected.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script>
    $(function() {
    	$(document).ready(function() {   
             $('#navbar').load('navbar.jsp');
             return false;
         });
         
    	$(document).ready(function() {   
            $('#footer').load('footer.html');
            return false;
        });

	});
    </script>
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
		StatusDAO statusDAO = StatusDAO.INSTANCE;
		UserDAO userDAO = UserDAO.INSTANCE;

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String url = userService.createLoginURL(request.getRequestURI());
		String urlLinktext = "Login";
		List<String> groupList = new ArrayList<String>();
		List<StatusObject> statusList = new ArrayList<StatusObject>();

		if (user != null) {
			if (userService.isUserAdmin()) {
				url = userService.createLogoutURL(request.getRequestURI());
				urlLinktext = "Logout";
				groupList = statusDAO.getDistinctGroupList();
				statusList = statusDAO.getSortedStatusList();
			} else {
				String redirectURL = "http://www.yahoo.com";
				response.sendRedirect(redirectURL);
			}
		}
	%>

	<!-- Nav bar -->

	<div id= "navbar"></div>

	<%
		if (user != null) {
	%>

	<!-- content -->

	<div class="container-fluid">
	<h1 class="page-header">Status Management Console</h1>
	<ul class="nav nav-tabs nav-justified">
	  <li><a href="StatusManagementApplication_overview.jsp">Overview</a></li>
	  <li><a href="StatusManagementApplication_groupmode.jsp">Group Mode</a></li>
	  <li class="active"><a href="#">Time Mode</a></li>
	</ul>
	
	<%
	
		List <UserObject> userListOfTheGroup = new ArrayList<UserObject>(); 
	
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		Calendar startingDateOfExp = Calendar.getInstance();
		Calendar endingDateOfExp = Calendar.getInstance();
		int timeIntervalOfExp = 0;
		
		int experimentTimepPool[]={9,12,15,18,21};
		int time_lowerBound = 0;
		int time_upperBound = 0;
		
	
		for (String groupName : groupList) {
			List<StatusObject> statusListOfTheGroup = new ArrayList<StatusObject>();
			
	%>
			<h3 class="page-header"><%=groupName%></h3>
		<%
		
			userListOfTheGroup = userDAO.getUserListOfTheGroup(groupName);
			if(userListOfTheGroup.size()>0){
				startingDateOfExp.setTime(userListOfTheGroup.get(0).getStartingDateOfExpAsDateObject());
				endingDateOfExp.setTime(userListOfTheGroup.get(0).getStartingDateOfExpAsDateObject());
				timeIntervalOfExp = Integer.parseInt(userListOfTheGroup.get(0).getTimeIntervalOfExp());
				//(timeIntervalOfExp - 1) because it's time "interval"
				endingDateOfExp.set(Calendar.DAY_OF_YEAR, startingDateOfExp.get(Calendar.DAY_OF_YEAR) + timeIntervalOfExp - 1);
			}
			
			for(int j=0;j<timeIntervalOfExp;j++){
				
				Calendar currentDateForRetrivingStatuses = startingDateOfExp;
				currentDateForRetrivingStatuses.set(Calendar.DAY_OF_YEAR, startingDateOfExp.get(Calendar.DAY_OF_YEAR) + j);
				
				
				for(int i=0;i<experimentTimepPool.length;i++){
					
					time_lowerBound = experimentTimepPool[i]-1;	
					time_upperBound = experimentTimepPool[i]+2;	
					String days[]={"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
					
					String experimentTime = time_lowerBound + ":00 - " + time_upperBound + ":00";
					String experimentDate = sdf.format(currentDateForRetrivingStatuses.getTime()) + " "
											+ days[currentDateForRetrivingStatuses.get(Calendar.DAY_OF_WEEK)-1];
					
					statusListOfTheGroup = statusDAO.getStatusListOfTheGroupInATimeInterval(currentDateForRetrivingStatuses, time_lowerBound, time_upperBound, groupName);
					
					if(statusListOfTheGroup.size()>0){
						%>
						<div style="float:right; text-align:right"><h4 class="page-header"><%=experimentTime%></h4></div>
			
						<div style="float:left; text-align:left"><h4 class="page-header"><%=experimentDate%></h4></div>
						
						
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Group</th>
										<th>User ID</th>
										<th>Time stamp</th>
										<th>Status</th>
										<th>Group Status</th>
									</tr>
								</thead>
								<tbody>
							<%
								for (StatusObject statusobject : statusListOfTheGroup) {
							%>
										<tr>
											<td><%=statusobject.getmGroup()%></td>
											<td><%=statusobject.getUserID()%></td>
											<td><%=statusobject.getTimestamp()%></td>
											<td><%=statusobject.getStatus()%></td>
											<td><%=statusobject.getmGroupStatus()%></td>
										</tr>
							<%
								}
							%>	
								</tbody>
							</table>
						</div>
	<%
					}
				}
			}
		}
	%>	


		<div style="text-align: center;">
			<h1>
				<small>You have a total number of <%=groupList.size()%> groups.</small> 
				<br>
				<small>You have a total number of <%=statusList.size()%> statuses.</small>
			</h1>
		</div>

		<!-- Footer -->

		<br> 
		
		<div id= "footer"></div>


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
