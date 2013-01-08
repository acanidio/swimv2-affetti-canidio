package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Ability;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AbilityManagerRemote {

	public int createNewAcceptedAbility(String name);
	
	public int createNewPendingAbility(String name);
	
	public List<Ability> getAbilityList();
	
	public boolean verifyNewAbility(String name);
	
	public List<Ability> loadPendingAbilities();
	
	public boolean removeAbility(int ID);
	
	public boolean acceptAbility(int ID);
}
