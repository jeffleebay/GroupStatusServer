package edu.uci.ics.luci.groupstatusserver.statusdatabase;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.google.appengine.api.datastore.Text;
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
		Text wifiList = new Text(checkNull(req.getParameter("wifiList"))); //String must be 500 characters or less.
		String noiseLevel = checkNull(req.getParameter("noiseLevel"));
		String location = checkNull(req.getParameter("location"));
		String address = checkNull(req.getParameter("address"));
		
		StatusDAO.INSTANCE.add(userID, group, timestamp, status, groupStatus, wifiList, noiseLevel, location, address);

		resp.sendRedirect("/StatusManagementApplication_overview.jsp");
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