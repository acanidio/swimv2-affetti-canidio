package it.polimi.swim.servlets.storing;

import it.polimi.swim.entities.HelpRequest;
import it.polimi.swim.entities.Person;
import it.polimi.swim.sessionbeans.HelpRequestManager;
import it.polimi.swim.sessionbeans.HelpRequestManagerRemote;
import it.polimi.swim.utils.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class FeedbackStorer implements DataStorer {
	private HelpRequest hr;

	@Override
	public void store(HttpServletRequest request) {
		String description = request.getParameter("description");
		int replyID = Integer.parseInt(request.getParameter("replyid"));
		int mark = Integer.parseInt(request.getParameter("mark"));
		
		Person user = (Person) request.getSession().getAttribute("person");

		InitialContext ctx = Configuration.getInitialContext();
		
		try {
			HelpRequestManagerRemote hrmgr = (HelpRequestManagerRemote) ctx.lookup(HelpRequestManager.REMOTE);
			
			hr = hrmgr.getHelpRequestFromReply(replyID);
			
			hrmgr.createFeedback(mark, description, user.getID(), hr.getAbility().getID(), replyID);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "loadhr.servlet?id=" + hr.getID();
	}

}
