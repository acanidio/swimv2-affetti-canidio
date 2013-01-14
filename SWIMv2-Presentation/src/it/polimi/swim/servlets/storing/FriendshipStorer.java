package it.polimi.swim.servlets.storing;

import it.polimi.swim.entities.Person;
import it.polimi.swim.sessionbeans.FriendshipManager;
import it.polimi.swim.utils.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class FriendshipStorer implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {
		int recID = Integer.parseInt(request.getParameter("id"));
		Person user = (Person) request.getSession().getAttribute("person");

		InitialContext ctx = Configuration.getInitialContext();
		
		try {
			FriendshipManager frmgr = (FriendshipManager) ctx.lookup(FriendshipManager.REMOTE);
			
			frmgr.addFriendshipRequest(user.getID(), recID);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "loaduser.servlet?id=" + request.getParameter("id");
	}

}
