package it.polimi.swim.servlets.storing;


import java.sql.Date;

import it.polimi.swim.sessionbeans.UserDataManager;
import it.polimi.swim.sessionbeans.UserDataManagerRemote;
import it.polimi.swim.utils.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class UserStorer implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {
		InitialContext ctx = Configuration.getInitialContext();
		
		try {
			UserDataManagerRemote datamgr = (UserDataManagerRemote) ctx.lookup(UserDataManager.REMOTE);
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String avatar = request.getParameter("avatar");
			String city = request.getParameter("city");
			char gender = request.getParameter("gender").charAt(0);
			Date birthday = Date.valueOf(request.getParameter("birthday"));
			String phonenumber = request.getParameter("phonenumber");
			
			datamgr.registerNewUser(email, password, name, surname, avatar, city, gender, birthday, phonenumber);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "home.servlet";
	}

}
