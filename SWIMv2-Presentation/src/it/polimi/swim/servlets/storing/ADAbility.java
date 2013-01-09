package it.polimi.swim.servlets.storing;

import javax.servlet.http.HttpServletRequest;

public class ADAbility implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {
		String ans=request.getParameter("ans");
		
		System.out.println("The ability @id "+request.getParameter("id")+"has been "+ans);
		
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "pabilities.servlet";
	}

}
