package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Administrator;
import it.polimi.swim.entities.Person;
import it.polimi.swim.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class LoginManager
 */
@Stateless
@RemoteBinding(jndiBinding = LoginManager.REMOTE)
public class LoginManager implements LoginManagerRemote {

	public static final String REMOTE = "LoginManager/remote";
	
	@PersistenceContext(unitName = "SWIMPU")
	private EntityManager manager;

	@Override
	public Integer validLogin(String username, String password) {
		Person person;
		try {
			person = loadPerson(username);
			if(person.getPassword().equals(password)) {
				return person.getID();
			}
		}
		catch (NoResultException e) {
			return null;
		}
		return null;
	}

	@Override
	public Person loadPerson(String email) throws NoResultException{
		Query query = manager.createQuery("SELECT p " +
										"FROM Person p " +
										"WHERE p.email = :email");
		Person person = (Person) query.setParameter("email", email).getSingleResult();
		return person;
	}

	@Override
	public User loadUser(String email) throws NoResultException{
		Query query = manager.createQuery("SELECT u " +
										"FROM User u " +
										"WHERE u.email = :email");
		User user = (User) query.setParameter("email", email).getSingleResult();
		return user;
	}

	@Override
	public Administrator loadAdministrator(String email) throws NoResultException{
		Query query = manager.createQuery("SELECT a " +
										"FROM Administrator a " +
										"WHERE a.email = :email");
		Administrator administrator = (Administrator) query.setParameter("email", email).getSingleResult();
		return administrator;
	}

}
