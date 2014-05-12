package edu.uci.ics.luci.groupstatusserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class GroupStatus_ServerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/plain");

		String function = req.getParameter("function");

		switch (function) {
		case "login":
			String userID = req.getParameter("userID");
			String userPW = req.getParameter("userPW");
			if (userID.equals("user001") && userPW.equals("1234"))
				out.println("success");
			break;
		case "upload":
			String status = req.getParameter("status");
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
}
