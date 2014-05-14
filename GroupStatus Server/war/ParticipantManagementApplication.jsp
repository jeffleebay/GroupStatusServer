<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="edu.uci.ics.luci.groupstatusserver.userdatabase.UserDAO" %>
<%@ page import="edu.uci.ics.luci.groupstatusserver.userdatabase.UserObject" %>

<!DOCTYPE html>


<%@page import="java.util.ArrayList"%>

<html>
  <head>
    <title>[Group Status] Participant Database</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
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
            
if (user != null){
	if(userService.isUserAdmin()){		
	    url = userService.createLogoutURL(request.getRequestURI());
	    urlLinktext = "Logout";
	    userList = dao.getUserList(user.getUserId());
	}else{
		String redirectURL = "http://www.yahoo.com"; 
	    response.sendRedirect(redirectURL);
	}
}
    
%>
  <div style="width: 100%;">
    <div class="line"></div>
    <div class="topLine">
      <div style="float: left;"><img src="images/icon.png" /></div>
      <div style="float: left;" class="headline">Participants</div>
      <div style="float: right;"><a href="<%=url%>"><%=urlLinktext%></a> <%=(user==null? "" : user.getNickname())%></div>
    </div>
  </div>

<div style="clear: both;">  
You have a total number of <%= userList.size() %>  participants.
</div>

<table>
  <tr>
      <th>User ID </th>
      <th>User PW</th>
      <th>Group</th>
      <th>Remove</th>
    </tr>

<% for (UserObject userobject : userList) {%>
<tr> 
<td>
<%=userobject.getUserID()%>
</td>
<td>
<%=userobject.getUserPW()%>
</td>
<td>
<%=userobject.getGroup()%>
</td>
<td>
<a class="done" href="/done?id=<%=userobject.getId()%>" >Remove</a>
</td>
</tr> 
<%}
%>
</table>


<hr />

<div class="main">

<div class="headline">New participant</div>

<% if (user != null){ %> 
<!-- userID, userPW, group -->
<form action="/new" method="post" accept-charset="utf-8">
  <table>
    <tr>
      <td><label for="userID">User ID</label></td>
      <td><input type="text" name="userID" id="userID" size="65"/></td>
    </tr>
    <tr>
      <td><label for="userPW">User PW</label></td>
      <td><input type="text" name="userPW" id="userPW" size="65"/></td>
    </tr>
    <tr>
      <td><label for="group">Group</label></td>
      <td><input type="text" name="group" id="group" size="65"/></td>
    </tr>
    
  <tr>
      <td colspan="2" align="right"><input type="submit" value="Create"/></td>
    </tr>
  </table>
</form>

<% }else{ %>

Please login with your Google account

<% } %>
</div>
</body>
</html> 