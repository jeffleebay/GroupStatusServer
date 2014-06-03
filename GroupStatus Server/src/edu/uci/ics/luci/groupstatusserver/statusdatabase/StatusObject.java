package edu.uci.ics.luci.groupstatusserver.statusdatabase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Text;

@Entity
public class StatusObject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userID;
	private String mGroup;
	private String timestamp;
	private String status;
	private String mGroupStatus;
	private Text wifiList;
	private String noiseLevel;
	private String location;
	private String address;

	public StatusObject(String userID, String mGroup, String timestamp, String status, 
			String mGroupStatus, Text wifiList, String noiseLevel, String location, String address) {
		this.userID = userID;
		this.mGroup = mGroup;
		this.timestamp = timestamp;
		this.status = status;
		this.mGroupStatus = mGroupStatus;
		this.wifiList = wifiList;
		this.noiseLevel = noiseLevel;
		this.location = location;
		this.address = address;
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

	public String getmGroup() {
		return mGroup;
	}

	public void setmGroup(String mGroup) {
		this.mGroup = mGroup;
	}

	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getmGroupStatus() {
		return mGroupStatus;
	}
	
	public void setmGroupStatus(String mGroupStatus) {
		this.mGroupStatus = mGroupStatus;
	}	
	
	public Text getWifiList() {
		return wifiList;
	}
	
	public void setWifiList(Text wifiList) {
		this.wifiList = wifiList;
	}	
	
	public String getNoiseLevel() {
		return noiseLevel;
	}
	
	public void setNoiseLevel(String noiseLevel) {
		this.noiseLevel = noiseLevel;
	}	
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}	
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}	
	
}