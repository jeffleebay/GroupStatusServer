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
	private String type; //debugging, testing, experiment 
	private String startingDateForExp;  //experiment only
	private String timeIntervalForExp;  //experiment only
	private String other;
	private String admin;

	public UserObject(String userID, String userPW, String group, String type, String startingDateForExp, String timeIntervalForExp, String other, String admin) {
		this.userID = userID;
		this.userPW = userPW;
		this.group = group;
		this.type = type;
		this.startingDateForExp = startingDateForExp;
		this.timeIntervalForExp = timeIntervalForExp;
		this.other = other;
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
	
	public void setGroup(String group) {
		this.group = group;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStartingDateForExp() {
		return startingDateForExp;
	}	
	
	public void setStartingDateForExp(String startingDateForExp) {
		this.startingDateForExp = startingDateForExp;
	}
	
	public String geTtimeIntervalForExp() {
		return timeIntervalForExp;
	}	
	
	public void setTimeIntervalForExp(String timeIntervalForExp) {
		this.timeIntervalForExp = timeIntervalForExp;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	public String getOther() {
		return other;
	}
	
	public void setOther(String other) {
		this.other = other;
	}
}