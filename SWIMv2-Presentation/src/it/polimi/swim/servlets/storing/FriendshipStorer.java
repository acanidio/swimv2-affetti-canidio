package it.polimi.swim.servlets.storing;

import javax.servlet.http.HttpServletRequest;

public class FriendshipStorer implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println("Friendship stored: id " + id);
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "loaduser.servlet?id=" + request.getParameter("id");
	}

}
