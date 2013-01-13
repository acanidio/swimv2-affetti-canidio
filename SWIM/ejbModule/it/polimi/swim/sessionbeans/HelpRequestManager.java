package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Ability;
import it.polimi.swim.entities.Feedback;
import it.polimi.swim.entities.HelpRequest;
import it.polimi.swim.entities.Reply;
import it.polimi.swim.entities.User;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
			if(!u.getAbilities().contains(hr.getAbility())) {
				return false;
			}
			if(hr.getSender().equals(u)) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean markAsBestReply(int IDReply) {
		try {
			Reply r = manager.find(Reply.class, IDReply);
			r.setBest(true);
			manager.merge(r);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean hasBestReply(int IDHelpRequest) {
		try {
			HelpRequest hr = manager.find(HelpRequest.class, IDHelpRequest);
			for(Reply r: hr.getReplies()) {
				if(r.isBest()) {
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public Integer createFeedback(int mark, String description, int IDUser,
			int IDAbility, int IDReply) {
		Feedback f = new Feedback();
		try {
			f.setMark(mark);
			f.setDescription(description);
			f.setSender(manager.find(User.class, IDUser));
			f.setAbility(manager.find(Ability.class, IDAbility));
			f.setReply(manager.find(Reply.class, IDReply));
		} catch (Exception e) {
			return null;
		}
		return f.getID();
	}

	@Override
	public boolean hasFeedback(int IDHelpRequest) {
		try {
			HelpRequest hr = manager.find(HelpRequest.class, IDHelpRequest);
			for(Reply r: hr.getReplies()) {
				if(r.isBest() && (r.getFeedback() != null)) {
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	//TODO review
	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> getHelpRequests() {
		List<HelpRequest> helprequests = null;
		try {
			Query query = manager.createQuery("SELECT h " +
											"FROM HelpRequest h" +
											"ORDERBY ID descendent");
			helprequests = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return helprequests;
		}

	//TODO review
	@Override
	public HelpRequest getHelpRequest(int id) {
		HelpRequest hr = null;
		try {
			hr = manager.find(HelpRequest.class, id);
		} catch (Exception e) {
			return null;
		}
		return hr;
	}

	//TODO review
	@Override
	public boolean postedByMe(int IDUser, int IDHr) {
		try {
			User u = manager.find(User.class, IDUser);
			HelpRequest hr = manager.find(HelpRequest.class, IDHr);
			
			if(hr.getSender().equals(u)) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
