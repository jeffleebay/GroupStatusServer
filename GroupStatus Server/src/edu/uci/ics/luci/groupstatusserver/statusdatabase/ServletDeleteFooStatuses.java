package edu.uci.ics.luci.groupstatusserver.statusdatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import edu.uci.ics.luci.groupstatusserver.statusdatabase.StatusDAO;
import edu.uci.ics.luci.groupstatusserver.userdatabase.UserDAO;
import edu.uci.ics.luci.groupstatusserver.userdatabase.UserObject;

@SuppressWarnings("serial")
public class ServletDeleteFooStatuses extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}

		String group = "";

		for(int i=0;i<3;i++){
			group="Foo group 00" + (i+1); 
			
			List<StatusObject> userList = new ArrayList<StatusObject>();
			userList = StatusDAO.INSTANCE.getStatusListOfTheGroup(group);
			for(int j=0;j<userList.size();j++){
				StatusDAO.INSTANCE.remove(userList.get(j).getId());
			}
		}

		resp.sendRedirect("/StatusManagementApplication_overview.jsp");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("doGet @ ServletDeleteFooStatuses");

		doPost(req, resp);

	}
}