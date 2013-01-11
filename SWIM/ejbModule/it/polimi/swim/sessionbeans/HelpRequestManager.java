package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Ability;
import it.polimi.swim.entities.HelpRequest;
import it.polimi.swim.entities.Reply;
import it.polimi.swim.entities.User;

import java.sql.Date;
import java.sql.Time;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class HelpRequestManager
 */
@Stateless
@RemoteBinding(jndiBinding = HelpRequestManager.REMOTE)
public class HelpRequestManager implements HelpRequestManagerRemote {

    public static final String REMOTE = "HelpRequestManager/remote";
    
    @PersistenceContext(unitName = "SWIMPU")
    private EntityManager manager;

	@Override
	public Integer createHelpRequest(String title, String city,
			String description, Date date, Time hour, int IDUser, int IDAbility) {
		HelpRequest hr = new HelpRequest();
		try {
			hr.setTitle(title);
			hr.setCity(city);
			hr.setDescription(description);
			hr.setDate(date);
			hr.setHour(hour);
			hr.setSender(manager.find(User.class, IDUser));
			hr.setAbility(manager.find(Ability.class, IDAbility));
			manager.persist(hr);
		} catch (Exception e) {
			return null;
		}
		return hr.getID();
	}

	@Override
	public Integer replyToHelpRequest(int IDUser, int IDHelpRequest) {
		Reply r = new Reply();
		try {
			r.setSender(manager.find(User.class, IDUser));
			r.setRequest(manager.find(HelpRequest.class, IDHelpRequest));
			manager.persist(r);
		}catch (Exception e) {
			return null;
		}
		return r.getID();
	}

	@Override
	public boolean checkCanReplyToHelpRequest(int IDUser, int IDHelpRequest) {
		try {
			User u = manager.find(User.class, IDUser);
			HelpRequest hr = manager.find(HelpRequest.class, IDHelpRequest);
			// TODO
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean markAsBestReply(int IDReply) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBestReply(int IDHelpRequest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer createFeedback(int mark, String description, int IDUser,
			int IDAbility, int IDReply) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasFeedback(int IDHelpRequest) {
		// TODO Auto-generated method stub
		return false;
	}
}
