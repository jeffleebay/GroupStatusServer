package edu.uci.ics.luci.groupstatusserver.userdatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import edu.uci.ics.luci.groupstatusserver.userdatabase.UserDAO;

@SuppressWarnings("serial")
public class ServletDeleteFooUsers extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}
		
		String group="";
		for(int i=0;i<3;i++){
			group="Foo group 00" +(i+1);
			List<UserObject> userList = new ArrayList<UserObject>();
			userList = UserDAO.INSTANCE.getUserListOfTheGroup(group);
			
			for(int j=0;j<userList.size();j++){
				UserDAO.INSTANCE.remove(userList.get(j).getId());
			}
		}


		resp.sendRedirect("/ParticipantManagementApplication.jsp");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("doGet @ DeleteFooUsers");

		doPost(req, resp);

	}
}