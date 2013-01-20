package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Ability;
import it.polimi.swim.entities.User;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class AbilityManager
 */
@Stateless
@RemoteBinding(jndiBinding = AbilityManager.REMOTE)
public class AbilityManager implements AbilityManagerRemote {

    public static final String REMOTE = "AbilityManager/remote";
    
    @PersistenceContext(unitName = "SWIMPU")
    EntityManager manager;

	@Override
	public Integer createNewAcceptedAbility(String name) {
		Ability ability = new Ability();
		ability.setName(name);
		ability.setPending(false);
		try {
			manager.persist(ability);
		} catch (Exception e) {
			return null;
		}
		return ability.getID();
	}
	
	@Override
	public int createNewPendingAbility(String name) {
		Ability ability = new Ability();
		ability.setName(name);
		ability.setPending(true);
		try {
			manager.persist(ability);
		} catch (Exception e) {
			return 0;
		}
		return ability.getID();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ability> getAbilityList() {
		List<Ability> abilities;
		try {
			Query query = manager.createQuery("SELECT a " +
											"FROM Ability a " +
											"WHERE a.pending = false");
			abilities = (List<Ability>) query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return abilities;
	}

	@Override
	public boolean verifyNewAbility(String name) {
		boolean verified = false;
		try {
			Query query = manager.createQuery("SELECT a " +
											"FROM Ability a " +
											"WHERE a.name = :name");
			Ability ability = (Ability) query.setParameter("name", name).getSingleResult();
			if(ability != null) {
				verified = true;
			}
		} catch (Exception e) {
			return false;
		}
		return verified;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Ability> loadPendingAbilities() {
		List<Ability> abilities;
		try {
			Query query = manager.createQuery("SELECT a " +
											"FROM Ability a " +
											"WHERE a.pending = true");
			abilities = (List<Ability>) query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return abilities;
	}

	@Override
	public boolean removeAbility(int IDAbility) {
		try {
			Ability ability = manager.find(Ability.class, IDAbility);
			List<User> users = ability.getUsers();
			for(User u: users) {
				removeAbilityFromUser(IDAbility, u.getID());
			}
			manager.remove(ability);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void removeAbilityFromUser(int iDAbility, int id) {
		try {
			User u = manager.find(User.class, id);
			Ability a = manager.find(Ability.class, iDAbility);
			u.getAbilities().remove(a);
			manager.merge(u);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	@Override
	public boolean acceptAbility(int ID) {
		try {
			Query query = manager.createQuery("SELECT a " +
											"FROM Ability a " +
											"WHERE a.ID = :ID AND a.pending = true");
			Ability ability = (Ability) query.setParameter("ID", ID).getSingleResult();
			ability.setPending(false);
			manager.merge(ability);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ability> searchAbilitiesByName(String name) {
		List<Ability> abilities = null;
		try {
			Query query = manager.createQuery("SELECT a " +
											"FROM Ability a " +
											"WHERE a.name = :name");
			abilities = query.setParameter("name", name)
							.getResultList();
		} catch (Exception e) {
			return null;
		}
		return abilities;
	}
}
