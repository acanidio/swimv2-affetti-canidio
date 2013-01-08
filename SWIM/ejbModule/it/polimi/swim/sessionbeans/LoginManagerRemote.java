package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Administrator;
import it.polimi.swim.entities.Person;
import it.polimi.swim.entities.User;

import javax.ejb.Remote;
import javax.persistence.NoResultException;

@Remote
public interface LoginManagerRemote {
	
	public Integer validLogin(String username, String password);

	public Person loadPerson(String username) throws NoResultException;
	
	public User loadUser(String username) throws NoResultException;
	
	public Administrator loadAdministrator(String username) throws NoResultException;
}
