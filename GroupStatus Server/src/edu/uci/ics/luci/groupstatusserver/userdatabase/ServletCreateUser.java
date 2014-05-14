package edu.uci.ics.luci.groupstatusserver.userdatabase;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import edu.uci.ics.luci.groupstatusserver.userdatabase.Dao;

@SuppressWarnings("serial")
public class ServletCreateUser extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Creating new user ");
		User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}
		String userID = checkNull(req.getParameter("userID"));
		String userPW = checkNull(req.getParameter("userPW"));
		String group = checkNull(req.getParameter("group"));
		String admin = user.getUserId();

		Dao.INSTANCE.add(userID, userPW, group, admin);

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