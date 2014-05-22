package edu.uci.ics.luci.groupstatusserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.Text;

import edu.uci.ics.luci.groupstatusserver.userdatabase.*;
import edu.uci.ics.luci.groupstatusserver.statusdatabase.*;

@SuppressWarnings("serial")
public class GroupStatus_ServerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/plain");

//		System.out.println("line 19 @ GroupStatus_ServerServlet");

		String function = checkNull(req.getParameter("function"));
		String userID = "";
		String userPW = "";
		String group = "";

		switch (function) {
		case "login":
			userID = checkNull(req.getParameter("userID"));
			userPW = checkNull(req.getParameter("userPW"));
			
			// Dao would return the group name and other parameters if ID/PW are correct
			String flag = UserDAO.INSTANCE.checkUser(userID, userPW);

			if (flag.equals("Login/password combination not found")) {
				out.println("log in failure");
			} else {
				out.println("successfully logged in as a member of :" + flag);
			}
			break;
		case "upload":
			userID = checkNull(req.getParameter("userID"));
			group = checkNull(req.getParameter("group"));
			String timestamp = checkNull(req.getParameter("timestamp"));
			String status = checkNull(req.getParameter("status"));
			String groupStatus = checkNull(req.getParameter("groupStatus"));
			Text wifiList = new Text(checkNull(req.getParameter("wifiList"))); //String must be 500 characters or less.
			String noiseLevel = checkNull(req.getParameter("noiseLevel"));
			String location = checkNull(req.getParameter("location"));
			String address = checkNull(req.getParameter("address"));

//			if (userID.equals("null") && group.equals("null") && timestamp.equals("null") && status.equals("null")
//					&& groupStatus.equals("null") && wifiList.equals("null") && noiseLevel.equals("null") && location.equals("null") && address.equals("null")){
//				out.println("incomplete parameters");
//			}else{
				StatusDAO.INSTANCE.add(userID, group, timestamp, status, groupStatus, wifiList, noiseLevel, location, address);
				out.println("success");
//			}
			break;
		default:
			out.println("Can't find any matched function");
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doGet(req, resp);
	}

	private String checkNull(String s) {
		if (s == null) {
			return "null";
		}
		return s;
	}
}
