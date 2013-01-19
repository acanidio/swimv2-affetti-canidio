package it.polimi.swim.servlets.storing;

import it.polimi.swim.entities.Administrator;
import it.polimi.swim.entities.Person;
import it.polimi.swim.entities.User;
import it.polimi.swim.sessionbeans.AbilityManager;
import it.polimi.swim.sessionbeans.AbilityManagerRemote;
import it.polimi.swim.sessionbeans.UserDataManager;
import it.polimi.swim.sessionbeans.UserDataManagerRemote;
import it.polimi.swim.utils.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class AbilityStorer implements DataStorer {

	private String forwardingPath = "loadabilities.servlet?path=modifyprofile.view";

	@Override
	public void store(HttpServletRequest request) {

		String type = (String) request.getSession().getAttribute("type");
		Person user = (Person) request.getSession().getAttribute("person");
		String name = request.getParameter("newability");
		InitialContext ctx = Configuration.getInitialContext();

		try {
			AbilityManagerRemote abmgr = (AbilityManagerRemote) ctx
					.lookup(AbilityManager.REMOTE);

			if (abmgr.verifyNewAbility(name)) {
				request.setAttribute("error",
						"The ability is already into the database");
				forwardingPath = "error.view";
				return;
			}

			if (type.equals(Administrator.TYPE)) {
				abmgr.createNewAcceptedAbility(name);
			} else if (type.equals(User.TYPE)) {
				int abID = abmgr.createNewPendingAbility(name);
				
				UserDataManagerRemote usermgr = (UserDataManagerRemote) ctx.lookup(UserDataManager.REMOTE);
				usermgr.addAbilityToUser(user.getID(), abID);
				
				request.getSession().setAttribute("person", usermgr.loadProfile(user.getID()));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {

		if (request.getSession().getAttribute("type")
				.equals(Administrator.TYPE)) {
			forwardingPath = "home.servlet";
		}
		return forwardingPath;
	}

}
