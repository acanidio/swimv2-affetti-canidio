package it.polimi.swim.servlets.storing;

import javax.servlet.http.HttpServletRequest;

public class FeedbackStorer implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {
		String description = request.getParameter("description");

		System.out.println("Feedback stored: " + description);
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "loadhr.servlet?id=" + request.getParameter("id");
	}

}
