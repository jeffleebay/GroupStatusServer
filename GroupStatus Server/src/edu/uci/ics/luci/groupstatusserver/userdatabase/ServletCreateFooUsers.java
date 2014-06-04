package edu.uci.ics.luci.groupstatusserver.userdatabase;

import java.io.IOException;

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
public class ServletCreateFooUsers extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Creating foo users");
		User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}
		String userID=""; 
		String userPW=""; 
		String group="";
		String type="debugging";
		String startingDateOfExp="0602"; 
		String timeIntervalOfExp="2";
		String other="";

		for(int i=0;i<3;i++){
			group="Foo group 00" +(i+1);  
			for(int j=0;j<5;j++){
				userID=Integer.toString(9000 + i*10 + j);
				userPW=Integer.toString(9900 + i*10 + j);
				UserDAO.INSTANCE.add(userID, userPW, group, type, startingDateOfExp, timeIntervalOfExp, other);
			}
		}


		resp.sendRedirect("/ParticipantManagementApplication.jsp");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("doGet @ CreateFooUsers");

		doPost(req, resp);

	}
}