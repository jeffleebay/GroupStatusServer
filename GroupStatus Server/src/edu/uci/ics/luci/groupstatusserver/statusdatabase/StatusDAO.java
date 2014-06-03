package edu.uci.ics.luci.groupstatusserver.statusdatabase;

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
			EntityManager em = EMFService.get().createEntityManager();
			StatusObject update = new StatusObject(userID, group, timestamp, status, groupStatus, wifiList, noiseLevel, location, address);
			em.persist(update);
			em.close();
		}
	}
	
	public List<StatusObject> getSortedStatusList(String adminID) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT t FROM StatusObject t ORDER BY t.mGroup, t.timestamp, t.userID ASC");
		 
		@SuppressWarnings("unchecked")
		List<StatusObject> statuses = q.getResultList();
		
		return statuses;
	}
	
	public List<String> getDistinctGroupList(String adminID) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT DISTINCT t.mGroup FROM StatusObject t ORDER BY t.mGroup ASC");
		 
		@SuppressWarnings("unchecked")
		List<String> groupNames = q.getResultList();
		
		return groupNames;
	}
	
	public List<StatusObject> getStatusListOfTheGroup(String groupName, String adminID) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT t FROM StatusObject t WHERE t.mGroup = :groupName ORDER BY t.timestamp, t.userID ASC");
		q.setParameter("groupName", groupName);
		
		@SuppressWarnings("unchecked")
		List<StatusObject> statuses = q.getResultList();
		
		return statuses;
	}
	
}