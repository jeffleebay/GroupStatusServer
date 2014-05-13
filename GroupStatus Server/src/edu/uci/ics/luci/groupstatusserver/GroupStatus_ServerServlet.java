package edu.uci.ics.luci.groupstatusserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class GroupStatus_ServerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/plain");
		
		System.out.println("line 19 @ GroupStatus_ServerServlet");

		String function = req.getParameter("function");
		if(function == null) function = "null";

		switch (function) {
		case "login":
			String userID = req.getParameter("userID");
			String userPW = req.getParameter("userPW");
			if (userID.equals("user001") && userPW.equals("1234")){
				out.println("success");
				
				ServletContext context = this.getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher("/new");
				try {
//					HttpServletRequest request;
//					HttpServletResponse response;
					dispatcher.forward(req, resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
