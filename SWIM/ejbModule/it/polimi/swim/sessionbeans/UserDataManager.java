package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Ability;
import it.polimi.swim.entities.Administrator;
import it.polimi.swim.entities.Conversation;
import it.polimi.swim.entities.Friendship;
import it.polimi.swim.entities.HelpRequest;
import it.polimi.swim.entities.Message;
import it.polimi.swim.entities.Person;
import it.polimi.swim.entities.User;

import java.sql.Date;
import java.util.ArrayList;
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

	@SuppressWarnings("unused")
	@Override
	public boolean emailAlreadyExists(String email) {
		Person person = null;
		try {
			Query query = manager.createQuery("SELECT p " + "FROM Person p "
					+ "WHERE p.email = :email");
			person = (Person) query.setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
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
			if (avatar == null) {
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
	public Hashtable<Ability, Double> loadUserAbilities(int IDUser) {
		Hashtable<Ability, Double> possessedAbilities = new Hashtable<Ability, Double>();
		try {
			Query query = manager.createQuery("SELECT a "
					+ "FROM Ability a, IN (a.users) u " + "WHERE u.ID = :ID");
			List<Ability> abilities = (List<Ability>) query.setParameter("ID",
					IDUser).getResultList();
			for (Ability a : abilities) {
				Double average = averageMark(IDUser, a.getID());
				possessedAbilities.put(a, average);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return possessedAbilities;
	}

	private Double averageMark(int IDUser, int IDAbility) {
		Double average = null;
		try {
			Query query = manager
					.createQuery("SELECT AVG(f.mark) "
							+ "FROM Feedback f JOIN f.reply r JOIN r.sender u JOIN f.ability a "
							+ "WHERE u.ID = :IDUser AND a.ID = :IDAbility");
			average = (Double) query.setParameter("IDUser", IDUser)
					.setParameter("IDAbility", IDAbility).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if (average == null) {
			average = (double) 0;
		}
		return average;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> loadUserHelpRequests(int IDUser) {
		List<HelpRequest> helprequests = null;
		try {
			Query query = manager.createQuery("SELECT h "
					+ "FROM User u, IN (u.helprequests) h "
					+ "WHERE u.ID = :IDUser");
			helprequests = query.setParameter("IDUser", IDUser).getResultList();
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
			Query query = manager
					.createQuery("SELECT c "
							+ "FROM Conversation c JOIN c.sender u1 JOIN c.receiver u2 "
							+ "WHERE u1.ID = :IDUser OR u2.ID = :IDUser");
			conversations = (List<Conversation>) query.setParameter("IDUser",
					IDUser).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conversations;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friendship> loadPendingFriendships(int IDUser) {
		List<Friendship> pendingFriendships = null;
		try {
			Query query = manager.createQuery("FROM Friendship f "
					+ "WHERE f.receiver.ID = :IDUser AND f.accepted = false");
			pendingFriendships = query.setParameter("IDUser", IDUser)
					.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pendingFriendships;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Message> loadNewReceivedMessages(int IDUser) {
		List<Message> newMessages = null;
		try {
			Query query = manager.createQuery("FROM Message m "
					+ "WHERE m.receiver.ID = :IDUser AND m.unreaded = true");
			newMessages = query.setParameter("IDUser", IDUser).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return newMessages;
	}

	@Override
	public boolean modifyUser(int IDUser, String password, String name,
			String surname, String city, Date birthday, String phonenumber) {
		boolean operationResult = false;
		try {
			User modifiedUser = manager.find(User.class, IDUser);
			if (!modifiedUser.getPassword().equals(password)) {
				modifiedUser.setPassword(password);
			}
			if (!modifiedUser.getName().equals(name)) {
				modifiedUser.setName(name);
			}
			if (!modifiedUser.getSurname().equals(surname)) {
				modifiedUser.setSurname(surname);
			}
			if (!modifiedUser.getCity().equals(city)) {
				modifiedUser.setCity(city);
			}
			if (!modifiedUser.getBirthday().equals(birthday)) {
				modifiedUser.setBirthday(birthday);
			}
			if (!modifiedUser.getPhonenumber().equals(phonenumber)) {
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
			if (user.getAbilities().contains(ability)) {
				return false;
			}
			user.getAbilities().add(ability);
			manager.merge(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchUsers(String username, String city,
			Integer abilityID) {
		String ucond = "";
		String ccond = "";
		String acond = "";
		String from = "FROM User u ";
		String select = "SELECT DISTINCT u ";
		List<User> users = null;

		if (username != null && !username.isEmpty()) {
			ucond += "(CONCAT(u.name,CONCAT(' ',u.surname)) LIKE :pattern OR CONCAT(u.surname,CONCAT(' ',u.name)) LIKE :pattern) ";
			username = "%" + username.replace(' ', '%') + "%";
		}

		if (city != null && !city.isEmpty()) {
			ccond += "AND u.city LIKE :city ";
			city = "%" + city.replace(' ', '%') + "%";
		}

		if (abilityID != null) {
			acond += "AND a.ID = :abID";
			from += "LEFT JOIN u.abilities a ";
		}

		String where = ucond + ccond + acond;

		if (where.substring(0, 3).equals("AND")) {
			where = where.substring(4);
		}

		try {
			String queryString = select + from + "WHERE " + where;

			Query query = manager.createQuery(queryString);

			if (username != null && !username.isEmpty()) {
				query = query.setParameter("pattern", username);
			}

			if (city != null && !city.isEmpty()) {
				query = query.setParameter("city", city);
			}

			if (abilityID != null) {
				query = query.setParameter("abID", abilityID);
			}

			users = (List<User>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return users;
	}

	@Override
	public Integer searchUserByEmail(String email) {
		User user = null;
		try {
			Query query = manager.createQuery("SELECT u " + "FROM User u "
					+ "WHERE u.email = :email");
			user = (User) query.setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			return null;
		}
		return user.getID();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadFriends(int IDUser) {
		List<User> friends = new ArrayList<User>();

		try {
			Query query = manager
					.createQuery("FROM Friendship f WHERE (f.receiver.ID = :ID OR f.sender.ID = :ID) AND f.accepted = true");
			List<Friendship> frships = (List<Friendship>) query.setParameter(
					"ID", IDUser).getResultList();

			for (Friendship f : frships) {
				int sender = f.getSender().getID();
				int receiver = f.getReceiver().getID();

				if (IDUser == sender) {
					friends.add(manager.find(User.class, receiver));
				} else if (IDUser == receiver) {
					friends.add(manager.find(User.class, sender));
				}
			}
		} catch (NoResultException e) {
			return friends;
		}

		return friends;
	}

	@Override
	public boolean createAdmin() {
		String email = "admin@admin.com";
		String name = "admin";
		String password = "admin";

		if (emailAlreadyExists(email)) {
			return false;
		}

		Administrator admin = new Administrator();
		admin.setEmail(email);
		admin.setName(name);
		admin.setPassword(password);

		try {
			manager.persist(admin);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
