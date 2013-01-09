package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Ability;
import it.polimi.swim.entities.Conversation;
import it.polimi.swim.entities.Friendship;
import it.polimi.swim.entities.HelpRequest;
import it.polimi.swim.entities.Message;
import it.polimi.swim.entities.Person;
import it.polimi.swim.entities.User;

import java.sql.Date;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.LocalBinding;
import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class ProfileDataManager
 */
@Stateless
@RemoteBinding(jndiBinding = UserDataManager.REMOTE)
@LocalBinding(jndiBinding = UserDataManager.LOCAL)
public class UserDataManager implements UserDataManagerRemote, UserDataManagerLocal {

	public static final String REMOTE = "UserDataManager/remote";
	public static final String LOCAL = "UserDataManager/local";
	
	@PersistenceContext(unitName = "SWIMPU")
	private EntityManager manager;

	@Override
	public boolean verifyUser(String email) {
		Person person = null;
		try {
			Query query = manager.createQuery("SELECT p " +
											"FROM Person p " +
											"WHERE p.email = :email");
			person = (Person) query.setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			return true;
		}
		return person != null;
	}

	@Override
	public int registerNewUser(String email, String password, String name,
			String surname, String avatar, String city, char gender,
			Date birthday, String phonenumber) {
		User user = new User();
		user.setUsername(email);
		user.setPassword(password);
		user.setName(name);
		user.setSurname(surname);
		user.setAvatar(avatar);
		user.setCity(city);
		user.setGender(gender);
		user.setBirthday(birthday);
		user.setPhonenumber(phonenumber);
		manager.persist(user);
		return user.getID();
	}

	@Override
	public User loadProfile(int IDUser) {
		User user;
		try {
			user = manager.find(User.class, IDUser);
		} catch (Exception e) {
			return null;
		}
		return user;
	}
	
	@Override
	public Hashtable<Ability, Float> loadUserAbilities(int IDUser) {
		Hashtable<Ability, Float> possessedAbilities = new Hashtable<Ability, Float>();
		try {
			Query query = manager.createQuery("SELECT a, AVG(f.mark) " +
											"FROM User u JOIN u.abilities a LEFT JOIN a.feedbacks f " +
											"WHERE u.ID = :ID " +
											"GROUP BY a");
			// TODO
		} catch (Exception e) {
			return null;
		}
		return possessedAbilities;
	}

	@Override
	public List<HelpRequest> loadUserHelpRequests(int IDUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Conversation> loadConversations(int IDUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conversation loadSpecificConversation(int IDConversation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Friendship> loadPendingFriendships(int IDUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> loadNewReceivedMessages(int IDUser) {
		// TODO Auto-generated method stub
		return null;
	}
}
