package edu.uci.ics.luci.groupstatusserver.userdatabase;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletRemoveUser extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
  throws IOException {
    String id = req.getParameter("id");
    UserDAO.INSTANCE.remove(Long.parseLong(id));
    resp.sendRedirect("/ParticipantManagementApplication.jsp");
  }
} 