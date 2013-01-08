package it.polimi.swim.servlets.storing;

import javax.servlet.http.HttpServletRequest;

public interface DataStorer {
	
	public void store(HttpServletRequest request);
	
	public String getForwardingPath(HttpServletRequest request);

}