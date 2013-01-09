package it.polimi.swim.servlets.storing;

import javax.servlet.http.HttpServletRequest;

public class ADFrship implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {
	String ans=request.getParameter("ans");
		
		System.out.println("The friendship @id "+request.getParameter("id")+"has been "+ans);
		

	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "pfrships.servlet";
	}

}
