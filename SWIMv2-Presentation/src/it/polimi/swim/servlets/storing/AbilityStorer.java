package it.polimi.swim.servlets.storing;

import javax.servlet.http.HttpServletRequest;

public class AbilityStorer implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {
		String ability = request.getParameter("newability");

		System.out.println("Abilty stored: " + ability);
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		if (request.getSession().getAttribute("type").equals("user")) {
			return "modifyprofile.view";
		}
		return "home.servlet";
	}

}
