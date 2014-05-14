package edu.uci.ics.luci.groupstatusserver.statusdatabase;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import edu.uci.ics.luci.groupstatusserver.statusdatabase.StatusDAO;

@SuppressWarnings("serial")
public class ServletCreateStatus extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Creating new status ");

		String userID = checkNull(req.getParameter("userID"));
		String group = checkNull(req.getParameter("group"));
		String timestamp = checkNull(req.getParameter("timestamp"));
		String status = checkNull(req.getParameter("status"));
		String groupStatus = checkNull(req.getParameter("groupStatus"));
		String wifiList = checkNull(req.getParameter("wifiList"));
		String noiseLevel = checkNull(req.getParameter("noiseLevel"));

		StatusDAO.INSTANCE.add(userID, group, timestamp, status, groupStatus, wifiList, noiseLevel);

		resp.sendRedirect("/ParticipantManagementApplication.jsp");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("doGet @ ServletCreateTodo");

		doPost(req, resp);

	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}