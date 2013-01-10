package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Friendship;
import it.polimi.swim.entities.User;

import javax.ejb.Remote;

@Remote
public interface FriendshipManagerRemote {
	
	public boolean haveFriendshipRequestBetween(User sender, User receiver);

	public Friendship addFriendshipRequest(User sender, User receiver);
	
	public Friendship getFriendshipRequest(User sender, User receiver);
	
	public Friendship getFriendshipRequest(int IDFriendshipRequest);
	
	public boolean acceptFriendshipRequest(int IDFriendshipRequest);
	
	public boolean declineFriendshipRequest(int IDFriendshipRequest);
}
