package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Ability;
import it.polimi.swim.entities.Conversation;
import it.polimi.swim.entities.Friendship;
import it.polimi.swim.entities.HelpRequest;
import it.polimi.swim.entities.Message;
import it.polimi.swim.entities.User;

import java.sql.Date;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface UserDataManagerRemote {

	public boolean verifyUser(String email);
	
	public int registerNewUser(String email, String password, String name, String surname, String avatar, String city, char gender, Date birthday, String phonenumber);
	
	public User loadProfile(int IDUser);
	
	public Hashtable<Ability, Float> loadUserAbilities(int IDUser);
	
	public List<HelpRequest> loadUserHelpRequests(int IDUser);
	
	public List<Conversation> loadConversations(int IDUser);
	
	public Conversation loadSpecificConversation(int IDConversation);
	
	public List<Friendship> loadPendingFriendships(int IDUser);
	
	public List<Message> loadNewReceivedMessages(int IDUser);
}
