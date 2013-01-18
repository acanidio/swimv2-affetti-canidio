package it.polimi.swim.servlets.storing;

import it.polimi.swim.entities.Person;
import it.polimi.swim.sessionbeans.HelpRequestManager;
import it.polimi.swim.sessionbeans.HelpRequestManagerRemote;
import it.polimi.swim.utils.Configuration;

import java.sql.Date;
import java.sql.Time;

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
		int abilityID = Integer.parseInt(request.getParameter("ability0"));
		
		Date date = Date.valueOf(request.getParameter("date"));
		Time hour =Time.valueOf(request.getParameter("hour")+":00");
		
		int IDUser = user.getID();

		try {
			HelpRequestManagerRemote hrmgr = (HelpRequestManagerRemote) ctx
					.lookup(HelpRequestManager.REMOTE);
			
			hrID = hrmgr.createHelpRequest(title, city, description, date, hour, IDUser, abilityID);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "loadhr.servlet?id=" + hrID;
	}

}
