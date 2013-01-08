package it.polimi.swim.servlets.storing;

import javax.servlet.http.HttpServletRequest;

public class BestReplyStorer implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {
		//sets the attribute best to true
		
		System.out.println("Best reply stored");
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "loadhr.servlet?id="+request.getParameter("replyid");
	}

}
