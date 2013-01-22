package it.polimi.swim.servlets.storing;

import it.polimi.swim.sessionbeans.AbilityManager;
import it.polimi.swim.sessionbeans.AbilityManagerRemote;
import it.polimi.swim.utils.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class ADAbility implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {
		String ans = request.getParameter("ans").toLowerCase();
		String isE = "";
		int abID = Integer.parseInt(request.getParameter("id"));

		InitialContext ctx = Configuration.getInitialContext();

		try {
			AbilityManagerRemote abmgr = (AbilityManagerRemote) ctx
					.lookup(AbilityManager.REMOTE);

			if (ans.equals("accept")) {
				abmgr.acceptAbility(abID);
				isE = "e";
			} else if (ans.equals("decline")) {
				abmgr.removeAbility(abID);
			}

			request.setAttribute("log", "The ability has been correctly " + ans
					+ isE + "d");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "pabilities.servlet";
	}

}
