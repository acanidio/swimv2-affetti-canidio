package it.polimi.swim.servlets.storing;

import javax.servlet.http.HttpServletRequest;

public class ReplyStorer implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {

		String hrid = request.getParameter("hrid");

		System.out.println("Reply stored:");
		System.out.println("hr id: " + hrid);
		System.out.println("by user: "
				+ request.getSession().getAttribute("user"));

	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "loadhr.servlet?id=" + request.getParameter("hrid");
	}

}
