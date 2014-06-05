package edu.uci.ics.luci.groupstatusserver.statusdatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Text;

import edu.uci.ics.luci.groupstatusserver.statusdatabase.EMFService;

public enum StatusDAO {
	INSTANCE;


	public void add(String userID, String group, String timestamp, String status, 
			String groupStatus, Text wifiList, String noiseLevel, String location, String address) {
		synchronized (this) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss");
		Calendar datetime = new GregorianCalendar();
		try {
			datetime.setTime(sdf.parse(timestamp));
		} catch (ParseException e) {
			System.out.print("can't parse the string to date");
			e.printStackTrace();
		}
		datetime.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
			EntityManager em = EMFService.get().createEntityManager();
			StatusObject update = new StatusObject(userID, group, datetime.getTime(), status, groupStatus, wifiList, noiseLevel, location, address);
			em.persist(update);
			em.close();
		}
	}
	
	public List<StatusObject> getSortedStatusList() {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT t FROM StatusObject t ORDER BY t.timestamp DESC"); //ORDER BY t.mGroup,  t.userID  ASC
		 
		@SuppressWarnings("unchecked")
		List<StatusObject> statuses = q.getResultList();
		return statuses;
	}
	
	public List<String> getDistinctGroupList() {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT DISTINCT t.mGroup FROM StatusObject t ORDER BY t.mGroup ASC");
		 
		@SuppressWarnings("unchecked")
		List<String> groupNames = q.getResultList();
		
		return groupNames;
	}
	
	public List<StatusObject> getStatusListOfTheGroup(String groupName) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT t FROM StatusObject t WHERE t.mGroup = :groupName ORDER BY t.timestamp DESC");
		q.setParameter("groupName", groupName);
		
		@SuppressWarnings("unchecked")
		List<StatusObject> statuses = q.getResultList();
		
		return statuses;
	}
	
	public List<StatusObject> getStatusListOfTheGroupInATimeInterval(Calendar date, int time_lowerBound, int time_upperBound, String groupName) {
		
		//Be careful that month of year starts at 0...
		Calendar lowerBoundOfTimeInterval = (Calendar) date.clone();
		lowerBoundOfTimeInterval.set(Calendar.HOUR_OF_DAY, time_lowerBound);
		Calendar upperBoundOfTimeInterval = (Calendar) date.clone();
		upperBoundOfTimeInterval.set(Calendar.HOUR_OF_DAY, time_upperBound);
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT t FROM StatusObject t WHERE t.mGroup = :groupName AND t.timestamp BETWEEN :lowerBound AND :upperBound ORDER BY t.timestamp ASC");
		q.setParameter("groupName", groupName);
		q.setParameter("lowerBound", lowerBoundOfTimeInterval.getTime());
		q.setParameter("upperBound", upperBoundOfTimeInterval.getTime());
		
		@SuppressWarnings("unchecked")
		List<StatusObject> statuses = q.getResultList();
		return statuses;
	}
	
	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			StatusObject status = em.find(StatusObject.class, id);
			em.remove(status);
		} finally {
			em.close();
		}
	}
	
}