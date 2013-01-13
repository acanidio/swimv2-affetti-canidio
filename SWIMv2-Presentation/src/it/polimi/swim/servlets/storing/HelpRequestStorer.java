package it.polimi.swim.servlets.storing;

import java.sql.Date;
import java.sql.Time;

import it.polimi.swim.entities.Person;
import it.polimi.swim.sessionbeans.AbilityManager;
import it.polimi.swim.sessionbeans.AbilityManagerRemote;
import it.polimi.swim.sessionbeans.HelpRequestManager;
import it.polimi.swim.sessionbeans.HelpRequestManagerRemote;
import it.polimi.swim.utils.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class HelpRequestStorer implements DataStorer {

	private Integer hrID;

	@Override
	public void store(HttpServletRequest request) {
		InitialContext ctx = Configuration.getInitialContext();
		
		Person user = (Person) request.getSession().getAttribute("person");
		
		String title = request.getParameter("title");
		String city = request.getParameter("city");
		String description = request.getParameter("description");
		String ability = request.getParameter("ability");
		
		//TODO work on this
		Date date = null;
		Time hour = null;
		
		int IDUser = user.getID();

		try {
			HelpRequestManagerRemote hrmgr = (HelpRequestManagerRemote) ctx
					.lookup(HelpRequestManager.REMOTE);
			
			AbilityManagerRemote abmgr = (AbilityManagerRemote) ctx.lookup(AbilityManager.REMOTE);
			
			int abID = abmgr.searchAbilitiesByName(ability.toLowerCase()).get(0).getID();
			
			hrID = hrmgr.createHelpRequest(title, city, description, date, hour, IDUser, abID);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "loadhr.servlet?id=" + hrID;
	}

}
