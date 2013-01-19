package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Friendship;
import it.polimi.swim.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class FriendshipManager
 */
@Stateless
@RemoteBinding(jndiBinding = FriendshipManager.REMOTE)
public class FriendshipManager implements FriendshipManagerRemote {

	public static final String REMOTE = "FriendshipManager/remote";
	
	@PersistenceContext(unitName ="SWIMPU")
	private EntityManager manager;
	
	@Override
	public boolean haveFriendshipRequestBetween(int IDsender, int IDreceiver) {
		if(getFriendshipRequest(IDsender, IDreceiver) != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public Friendship addFriendshipRequest(int IDsender, int IDreceiver) {
		Friendship newFriendship;
		try {
			newFriendship = new Friendship();
			newFriendship.setSender(manager.find(User.class, IDsender));
			newFriendship.setReceiver(manager.find(User.class, IDreceiver));
			newFriendship.setAccepted(false);
			manager.persist(newFriendship);
		} catch (Exception e) {
			return null;
		}
		return newFriendship;
	}

	@Override
	public Friendship getFriendshipRequest(int IDsender, int IDreceiver) {
		Friendship request = null;
		try {
			Query query = manager.createQuery("SELECT f " +
					"FROM Friendship f JOIN f.sender u1 JOIN f.receiver u2 " +
					"WHERE (u1.ID = :first AND u2.ID = :second) OR " +
					"(u2.ID = :first AND u1.ID = :second)");
			request = (Friendship) query.setParameter("first", IDsender)
							.setParameter("second", IDreceiver)
							.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		return request;
	}
	
	@Override
	public Friendship getFriendshipRequest(int IDFriendshipRequest) {
		Friendship request = null;
		try {
			request = manager.find(Friendship.class, IDFriendshipRequest);
		} catch (Exception e) {
			return null;
		}
		return request;
	}

	@Override
	public boolean acceptFriendshipRequest(int IDFriendshipRequest) {
		Friendship request = null;
		try {
			request = manager.find(Friendship.class, IDFriendshipRequest);
			request.setAccepted(true);
			manager.merge(request);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean declineFriendshipRequest(int IDFriendshipRequest) {
		Friendship request = null;
		try {
			request = manager.find(Friendship.class, IDFriendshipRequest);
			manager.remove(request);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean isFriendOf(int IDFirstUser, int IDSecondUser) {
		Friendship request = null;
		try {
			Query query = manager.createQuery("SELECT f " +
					"FROM Friendship f JOIN f.sender u1 JOIN f.receiver u2 " +
					"WHERE ((u1.ID = :first AND u2.ID = :second) OR " +
					"(u2.ID = :first AND u1.ID = :second)) AND " +
					"f.accepted = true");
			request = (Friendship) query.setParameter("first", IDFirstUser)
							.setParameter("second", IDSecondUser)
							.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isReceiver(int IDUser, int IDFriendship) {
		/*
		Friendship request = null;
		try {
			Query query = manager.createQuery("SELECT f " +
					"FROM Friendship f JOIN f.sender u1 JOIN f.receiver u2 " +
					"WHERE ((u1.ID = :first AND u2.ID = :second) OR " +
					"(u2.ID = :first AND u1.ID = :second)) AND " +
					"f.accepted = true");
			request = (Friendship) query.setParameter("first", IDFirstUser)
							.setParameter("second", IDSecondUser)
							.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
		*/
		
		return false;
	}

}

