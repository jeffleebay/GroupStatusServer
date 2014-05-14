package edu.uci.ics.luci.groupstatusserver.userdatabase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserObject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userID;
	private String userPW;
	private String group;
	private String admin;

	public UserObject(String userID, String userPW, String group, String admin) {
		this.userID = userID;
		this.userPW = userPW;
		this.group = group;
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public String getGroup() {
		return group;
	}

	public void setGroupl(String group) {
		this.group = group;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
}