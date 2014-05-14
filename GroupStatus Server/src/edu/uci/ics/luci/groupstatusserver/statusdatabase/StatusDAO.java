package edu.uci.ics.luci.groupstatusserver.statusdatabase;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public enum StatusDAO {
	INSTANCE;


	public void add(String userID, String group, String timestamp, String status, 
			String groupStatus, String wifiList, String noiseLevel) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			StatusObject update = new StatusObject(userID, group, timestamp, status, groupStatus, wifiList, noiseLevel);
			em.persist(update);
			em.close();
		}
	}
}