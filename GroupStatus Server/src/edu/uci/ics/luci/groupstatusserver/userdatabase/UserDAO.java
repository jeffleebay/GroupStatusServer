package edu.uci.ics.luci.groupstatusserver.userdatabase;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public enum UserDAO {
	INSTANCE;

	public void add(String userID, String userPW, String group, String type, String startingDateOfExp, String timeIntervalOfExp, String other) {
		synchronized (this) {
			
			Calendar startingDateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),
					  Integer.parseInt(startingDateOfExp.substring(0,2))-1, Integer.parseInt(startingDateOfExp.substring(2)), 
					  00, 00, 00);
			
			EntityManager em = EMFService.get().createEntityManager();
			UserObject user = new UserObject(userID, userPW, group, type, startingDateTime.getTime(), timeIntervalOfExp, other);
			em.persist(user);
			em.close();
		}
	}
	
	public String checkUser(String userID, String userPW) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT t FROM UserObject t WHERE t.userID = :userID");
		q.setParameter("userID", userID);
		List<UserObject> users = q.getResultList();
		if(users.size()>=1 && users.get(0).getUserPW().equals(userPW)) //size>=1 rather than size==1 in case there are duplicates
			return ("group=" + users.get(0).getmGroup()+";type="+users.get(0).getType()+";startingDate="+users.get(0).getStartingDateOfExpAsString()+";timeInterval="+users.get(0).getTimeIntervalOfExp());
		else
			return "Login/password combination not found";
	}
	
	public List<UserObject> getUserList() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT t FROM UserObject t ORDER BY t.mGroup, t.userID");
		
		@SuppressWarnings("unchecked")
		List<UserObject> users = q.getResultList();
		
		return users;
	}
	
	public List<UserObject> getUserListOfTheGroup(String mGroup) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT t FROM UserObject t WHERE t.mGroup = :mGroup ORDER BY t.userID");
		q.setParameter("mGroup", mGroup);
		
		@SuppressWarnings("unchecked")
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