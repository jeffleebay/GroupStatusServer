package edu.uci.ics.luci.groupstatusserver.userdatabase;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public enum UserDAO {
	INSTANCE;

	public void add(String userID, String userPW, String group, String type, String startingDateForExp, String timeIntervalForExp, String other, String admin) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			UserObject user = new UserObject(userID, userPW, group, type, startingDateForExp, timeIntervalForExp, other, admin);
			em.persist(user);
			em.close();
		}
	}

	public String checkUser(String userID, String userPW) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from UserObject t where t.userID = :userID");
		q.setParameter("userID", userID);
		List<UserObject> users = q.getResultList();
		if(users.size()>=1 && users.get(0).getUserPW().equals(userPW)) //size>=1 rather than size==1 in case there are duplicates
			return ("group=" + users.get(0).getGroup()+";type="+users.get(0).getType()+";startingDate="+users.get(0).getStartingDateForExp()+";timeInterval="+users.get(0).geTtimeIntervalForExp());
		else
			return "Login/password combination not found";
	}
	
	public List<UserObject> getUserList(String adminID) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from UserObject t where t.admin = :adminID");
		q.setParameter("adminID", adminID);
		List<UserObject> users = q.getResultList();
		return users;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			UserObject user = em.find(UserObject.class, id);
			em.remove(user);
		} finally {
			em.close();
		}
	}
}