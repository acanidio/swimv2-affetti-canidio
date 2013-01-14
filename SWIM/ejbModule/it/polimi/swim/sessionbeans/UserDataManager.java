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

import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class ProfileDataManager
 */
@Stateless
@RemoteBinding(jndiBinding = UserDataManager.REMOTE)
public class UserDataManager implements UserDataManagerRemote {

	public static final String REMOTE = "UserDataManager/remote";
	
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
	public Integer registerNewUser(String email, String password, String name,
			String surname, String avatar, String city, char gender,
			Date birthday, String phonenumber) {
		User user = new User();
		try {
			user.setEmail(email);
			user.setPassword(password);
			user.setName(name);
			user.setSurname(surname);
			if(avatar == null) {
				user.setAvatar("default.gif");
			} else {
				user.setAvatar(avatar);
			}
			user.setCity(city);
			user.setGender(gender);
			user.setBirthday(birthday);
			user.setPhonenumber(phonenumber);
			manager.persist(user);
		} catch (Exception e) {
			return null;
		}
		return user.getID();
	}

	@Override
	public User loadProfile(int IDUser) {
		User user = null;
		try {
			user = manager.find(User.class, IDUser);
		} catch (Exception e) {
			return null;
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Hashtable<Ability, Float> loadUserAbilities(int IDUser) {
		Hashtable<Ability, Float> possessedAbilities = new Hashtable<Ability, Float>();
		try {
			Query query = manager.createQuery("SELECT a " +
											"FROM User u JOIN u.abilities a " +
											"WHERE u.ID = :ID");
			List<Ability> abilities = (List<Ability>) query.setParameter("ID", IDUser).getResultList();
			for(Ability a: abilities) {
				Float average = averageMark(IDUser, a.getID());
				possessedAbilities.put(a, average);
			}
		} catch (Exception e) {
			return null;
		}
		return possessedAbilities;
	}
	
	private Float averageMark(int IDUser, int IDAbility) {
		Float average = null;
		try {
			Query query = manager.createQuery("SELECT AVG(f.mark) " +
											"FROM User u JOIN u.abilities a LEFT JOIN a.feedbacks f " +
											"WHERE u.ID = :IDUser AND a.ID = :IDAbility");
			average = (Float) query.setParameter("IDUser", IDUser)
							.setParameter("IDAbility", IDAbility)
							.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		return average;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> loadUserHelpRequests(int IDUser) {
		List<HelpRequest> helprequests = null;
		try {
			Query query = manager.createQuery("SELECT h " +
											"FROM User u JOIN u.helprequests h " +
											"WHERE u.ID = :IDUser");
			helprequests = query.setParameter("IDUser", IDUser)
								.getResultList();
		} catch (Exception e) {
			return null;
		}
		return helprequests;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Conversation> loadConversations(int IDUser) {
		List<Conversation> conversations = null;
		try {
			Query query = manager.createQuery("SELECT c " +
											"FROM Conversation c JOIN c.sender u1 JOIN c.receiver u2 " +
											"WHERE u1.ID = :IDUser OR u2.ID = :IDUser");
			conversations = (List<Conversation>) query.setParameter("IDUser", IDUser);
		} catch (Exception e) {
			return null;
		}
		return conversations;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friendship> loadPendingFriendships(int IDUser) {
		List<Friendship> pendingFriendships = null;
		try {
			Query query = manager.createQuery("SELECT f " +
											"FROM Friendship f JOIN f.sender u1 JOIN f.receiver u2 " +
											"WHERE (u1.ID = :IDUser OR u2.ID = :IDUser) AND f.pending = true");
			pendingFriendships = query.setParameter("IDUser", IDUser)
										.getResultList();
		} catch (Exception e) {
			return null;
		}
		return pendingFriendships;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<Message> loadNewReceivedMessages(int IDUser) {
		List<Message> newMessages = null;
		try {
			Query query = manager.createQuery("SELECT m " +
											"FROM Message JOIN f.sender u1 JOIN f.receiver u2 " +
											"WHERE (u1.ID = :IDUser OR u2.ID = :IDUser) AND m.unreaded = true");
			newMessages = query.setParameter("IDUser", IDUser)
								.getResultList();
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public boolean modifyUser(int IDUser, String password, String name,
			String surname, String avatar, String city, char gender,
			Date birthday, String phonenumber) {
		boolean operationResult = false;
		try {
			User modifiedUser = manager.find(User.class, IDUser);
			if(!modifiedUser.getPassword().equals(password)) {
				modifiedUser.setPassword(password);
			}
			if(!modifiedUser.getName().equals(name)) {
				modifiedUser.setName(name);
			}
			if(!modifiedUser.getSurname().equals(surname)) {
				modifiedUser.setSurname(surname);
			}
			if(!modifiedUser.getAvatar().equals(avatar)) {
				modifiedUser.setAvatar(avatar);
			}
			if(!modifiedUser.getCity().equals(city)) {
				modifiedUser.setCity(city);
			}
			if(modifiedUser.getGender() != gender) {
				modifiedUser.setGender(gender);
			}
			if(!modifiedUser.getBirthday().equals(birthday)) {
				modifiedUser.setBirthday(birthday);
			}
			if(!modifiedUser.getPhonenumber().equals(phonenumber)) {
				modifiedUser.setPhonenumber(phonenumber);
			}
			manager.merge(modifiedUser);
		} catch (Exception e) {
			operationResult = false;
		}
		return operationResult;
	}

	@Override
	public boolean addAbilityToUser(int IDUser, int IDAbility) {
		try {
			User user = manager.find(User.class, IDUser);
			Ability ability = manager.find(Ability.class, IDAbility);
			if(user.getAbilities().contains(ability)) {
				return false;
			}
			user.getAbilities().add(ability);
			manager.merge(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchUsersByName(String name) {
		List<User> users = null;
		try {
			Query query = manager.createQuery("SELECT u " +
											"FROM User u " +
											"WHERE u.name = :name");
			users = query.setParameter("name", name)
						.getResultList();
		} catch (Exception e) {
			return null;
		}
		return users;
	}

	@Override
	public Integer searchUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
