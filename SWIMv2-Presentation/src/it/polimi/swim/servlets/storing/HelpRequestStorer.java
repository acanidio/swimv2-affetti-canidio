package it.polimi.swim.servlets.storing;

import javax.servlet.http.HttpServletRequest;

import temporaryClasses.HelpRequest;

public class HelpRequestStorer implements DataStorer {
	
	private HelpRequest hr;

	@Override
	public void store(HttpServletRequest request) {
		hr = new HelpRequest();
		System.out.println("The help request has been stored");
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "loadhr.servlet?id="+hr.getId();
	}

}
