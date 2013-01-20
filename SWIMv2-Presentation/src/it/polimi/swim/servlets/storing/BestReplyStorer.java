package it.polimi.swim.servlets.storing;

import it.polimi.swim.sessionbeans.HelpRequestManager;
import it.polimi.swim.sessionbeans.HelpRequestManagerRemote;
import it.polimi.swim.utils.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class BestReplyStorer implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {
		InitialContext ctx = Configuration.getInitialContext();
		int replyID = Integer.parseInt(request.getParameter("replyid"));
		
		try {
			HelpRequestManagerRemote hrmgr = (HelpRequestManagerRemote) ctx.lookup(HelpRequestManager.REMOTE);
			
			hrmgr.markAsBestReply(replyID);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "loadhr.servlet?id="+request.getParameter("hrid");
	}

}
