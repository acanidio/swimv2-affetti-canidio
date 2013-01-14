package it.polimi.swim.servlets.storing;

import java.sql.Date;

import it.polimi.swim.sessionbeans.UserDataManager;
import it.polimi.swim.sessionbeans.UserDataManagerRemote;
import it.polimi.swim.utils.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class UserStorer implements DataStorer {

	private String forwardingPath = "home.servlet";

	@Override
	public void store(HttpServletRequest request) {
		InitialContext ctx = Configuration.getInitialContext();

		try {
			UserDataManagerRemote datamgr = (UserDataManagerRemote) ctx
					.lookup(UserDataManager.REMOTE);
			String email = request.getParameter("email");

			if (!datamgr.verifyUser(email)) {
				request.setAttribute("error",
						"The email entered is already into the database!");
				forwardingPath = "error.view";
				return;
			}

			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String avatar = request.getParameter("avatar");
			String city = request.getParameter("city");
			char gender = request.getParameter("gender").charAt(0);
			String bday = request.getParameter("birthday");
			Date birthday = null;

			if (bday != null && !bday.isEmpty()) {
				birthday = Date.valueOf(bday);
			}

			String phonenumber = request.getParameter("phonenumber");

			int userID = datamgr.registerNewUser(email, password, name,
					surname, avatar, city, gender, birthday, phonenumber);

			String sabilityID = request.getParameter("ability0");

			for (int i = 1; sabilityID != null && !sabilityID.isEmpty(); i++) {
				int abilityID = Integer.parseInt(sabilityID);
				datamgr.addAbilityToUser(userID, abilityID);

				sabilityID = request.getParameter("ability" + i);
			}

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return forwardingPath;
	}

}
