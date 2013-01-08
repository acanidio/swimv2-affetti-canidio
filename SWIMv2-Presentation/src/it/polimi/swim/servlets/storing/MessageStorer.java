package it.polimi.swim.servlets.storing;

import javax.servlet.http.HttpServletRequest;

public class MessageStorer implements DataStorer {

	@Override
	public void store(HttpServletRequest request) {
		String recipient = request.getParameter("recipient");
		String text = request.getParameter("text");
		
		System.out.println("Message stored:");
		System.out.println(recipient);
		System.out.println(text);
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "expandconv.servlet?id="+request.getParameter("id");
	}

}