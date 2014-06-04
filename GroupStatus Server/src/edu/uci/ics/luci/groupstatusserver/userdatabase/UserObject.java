package edu.uci.ics.luci.groupstatusserver.userdatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	private String mGroup; 
	private String type; //debugging, testing, experiment 
	private Date startingDateOFExp;  //experiment only
	private String timeIntervalOFExp;  //experiment only
	private String other;

	public UserObject(String userID, String userPW, String mGroup, String type, Date startingDateOFExp, String timeIntervalOFExp, String other) {
		this.userID = userID;
		this.userPW = userPW;
		this.mGroup = mGroup;
		this.type = type;
		this.startingDateOFExp = startingDateOFExp;
		this.timeIntervalOFExp = timeIntervalOFExp;
		this.other = other;
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

	public String getmGroup() {
		return mGroup;
	}
	
	public void setmGroup(String mGroup) {
		this.mGroup = mGroup;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Date getStartingDateOfExpAsDateObject() {
		return startingDateOFExp;
	}	
	public String getStartingDateOfExpAsString() {
//		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
		String s = null;
		if(startingDateOFExp != null) s = sdf.format(startingDateOFExp);
		
		return s;
	}	
	public String getStartingDateOfExpForDisplay() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String s = null;
		if(startingDateOFExp != null) s = sdf.format(startingDateOFExp);
		
		return s;
	}	
	
	
	
	public void setStartingDateOfExp(Date startingDateOFExp) {
		this.startingDateOFExp = startingDateOFExp;
	}
	
	public String getTimeIntervalOfExp() {
		return timeIntervalOFExp;
	}	
	
	public void setTimeIntervalOfExp(String timeIntervalOFExp) {
		this.timeIntervalOFExp = timeIntervalOFExp;
	}

	public String getOther() {
		return other;
	}
	
	public void setOther(String other) {
		this.other = other;
	}
}