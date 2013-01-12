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
	public Integer validLogin(String email, String password) {
		Person person;
		try {
			Query query = manager.createQuery("SELECT p " +
					"FROM Person p " +
					"WHERE p.email = :email");
			person = (Person) query.setParameter("email", email).getSingleResult();
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
	public Person loadPerson(int IDPerson) throws NoResultException{
		Person person = null;
		try {
			person = manager.find(Person.class, IDPerson);
		} catch (Exception e) {
			return null;
		}
		return person;
	}

	@Override
	public User loadUser(int IDUser) throws NoResultException{
		User user = null;
		try {
			user = manager.find(User.class, IDUser);
		} catch (Exception e) {
			return null;
		}
		return user;
	}

	@Override
	public Administrator loadAdministrator(int IDAdministrator) throws NoResultException{
		Administrator administrator = null;
		try {
			administrator = manager.find(Administrator.class, IDAdministrator);
		} catch (Exception e) {
			return null;
		}
		return administrator;
	}

}
