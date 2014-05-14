package edu.uci.ics.luci.groupstatusserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import edu.uci.ics.luci.groupstatusserver.userdatabase.*;

@SuppressWarnings("serial")
public class GroupStatus_ServerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/plain");
		
		System.out.println("line 19 @ GroupStatus_ServerServlet");

		String function = checkNull(req.getParameter("function"));
		if(function == null) function = "null";

		switch (function) {
		case "login":
			String userID = checkNull(req.getParameter("userID"));
			String userPW = checkNull(req.getParameter("userPW"));
			String group = Dao.INSTANCE.checkUser(userID, userPW);
			if (group.equals("Login/password combination not found")){
				out.println("log in failure");
			}else{
				out.println("successfully logged in");
			}
						break;
		case "upload":
			String status = checkNull(req.getParameter("status"));
			if (status.equals("Coding in Vista del Campo"))
				out.println("success");
			break;
		default:
			out.println("failure");
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req, resp);
	}
	
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}
