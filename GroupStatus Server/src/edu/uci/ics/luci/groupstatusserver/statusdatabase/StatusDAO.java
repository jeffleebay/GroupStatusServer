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
		Query q = em.createQuery("SELECT t FROM StatusObject t ORDER BY t.group, t.timestamp ASC");
		 
		@SuppressWarnings("unchecked")
		List<StatusObject> statuses = q.getResultList();
		
//		Query query = new Query("")
		
		return statuses;
	}
	
}