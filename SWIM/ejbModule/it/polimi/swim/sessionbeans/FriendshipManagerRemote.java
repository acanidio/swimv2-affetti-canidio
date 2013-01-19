package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Friendship;

import javax.ejb.Remote;

@Remote
public interface FriendshipManagerRemote {
	
	public boolean haveFriendshipRequestBetween(int IDsender, int IDreceiver);

	public Friendship addFriendshipRequest(int IDsender, int IDreceiver);
	
	public Friendship getFriendshipRequest(int IDsender, int IDreceiver);
	
	public Friendship getFriendshipRequest(int IDFriendshipRequest);
	
	public boolean acceptFriendshipRequest(int IDFriendshipRequest);
	
	public boolean declineFriendshipRequest(int IDFriendshipRequest);
	
	public boolean isFriendOf(int IDLoggedUser, int IDOtherUser);
	
	public boolean isReceiver(int IDUser, int IDFriendship);
}
