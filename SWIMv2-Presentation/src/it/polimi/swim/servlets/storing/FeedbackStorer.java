package it.polimi.swim.servlets.storing;

import javax.servlet.http.HttpServletRequest;

public class FeedbackStorer implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {
		String description = request.getParameter("description");
		String replyid = request.getParameter("replyid");

		System.out.println("Feedback stored @reply"+replyid+": " + description);
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "loadhr.servlet?id=" + request.getParameter("id");
	}

}
