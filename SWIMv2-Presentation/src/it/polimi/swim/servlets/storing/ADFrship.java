package it.polimi.swim.servlets.storing;

import it.polimi.swim.sessionbeans.FriendshipManager;
import it.polimi.swim.sessionbeans.FriendshipManagerRemote;
import it.polimi.swim.utils.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class ADFrship implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {
		String ans = request.getParameter("ans").toLowerCase();
		String isE = "";
		int frID = Integer.parseInt(request.getParameter("id"));

		InitialContext ctx = Configuration.getInitialContext();

		try {
			FriendshipManagerRemote frmgr = (FriendshipManagerRemote) ctx
					.lookup(FriendshipManager.REMOTE);

			if (ans.equals("accept")) {
				frmgr.acceptFriendshipRequest(frID);
				isE = "e";
			} else if (ans.equals("decline")) {
				frmgr.declineFriendshipRequest(frID);
			}

			request.setAttribute("log", "The friendship has been correctly "
					+ ans + isE + "d");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "pfrships.servlet";
	}

}
