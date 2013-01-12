package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Administrator;
import it.polimi.swim.entities.Person;
import it.polimi.swim.entities.User;

import javax.ejb.Remote;
import javax.persistence.NoResultException;

@Remote
public interface LoginManagerRemote {
	
	public Integer validLogin(String username, String password);

	public Person loadPerson(int IDPerson) throws NoResultException;
	
	public User loadUser(int IDUser) throws NoResultException;
	
	public Administrator loadAdministrator(int IDAdministrator) throws NoResultException;
}
