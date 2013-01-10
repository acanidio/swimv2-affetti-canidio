package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Friendship;
import it.polimi.swim.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
	public boolean haveFriendshipRequestBetween(User sender, User receiver) {
		if(getFriendshipRequest(sender, receiver) != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public Friendship addFriendshipRequest(User sender, User receiver) {
		Friendship newFriendship;
		try {
			newFriendship = new Friendship();
			newFriendship.setSender(sender);
			newFriendship.setReceiver(receiver);
			manager.persist(newFriendship);
		} catch (Exception e) {
			return null;
		}
		return newFriendship;
	}

	@Override
	public Friendship getFriendshipRequest(User sender, User receiver) {
		Friendship request = null;
		try {
			Query query = manager.createQuery("SELECT f " +
					"FROM f JOIN f.sender u1 JOIN f.receiver u2 " +
					"WHERE (u1.ID = :first AND u2.ID = :second) OR " +
					"(u2.ID = :first AND u1.ID = :second)");
			request = (Friendship) query.setParameter("first", sender)
							.setParameter("second", receiver)
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
			request.setAccepted(true);
			manager.remove(request);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
