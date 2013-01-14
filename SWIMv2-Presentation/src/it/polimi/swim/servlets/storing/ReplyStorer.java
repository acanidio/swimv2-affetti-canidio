package it.polimi.swim.servlets.storing;

import it.polimi.swim.entities.Person;
import it.polimi.swim.sessionbeans.HelpRequestManager;
import it.polimi.swim.sessionbeans.HelpRequestManagerRemote;
import it.polimi.swim.utils.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class ReplyStorer implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {

		int hrid = Integer.parseInt(request.getParameter("hrid"));
		Person user = (Person) request.getSession().getAttribute("person");
		
		InitialContext ctx = Configuration.getInitialContext();
		
		try {
			HelpRequestManagerRemote hrmgr = (HelpRequestManagerRemote) ctx.lookup(HelpRequestManager.REMOTE);
			
			hrmgr.replyToHelpRequest(user.getID(), hrid);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "loadhr.servlet?id=" + request.getParameter("hrid");
	}

}
