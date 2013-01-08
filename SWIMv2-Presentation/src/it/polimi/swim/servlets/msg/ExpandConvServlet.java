package it.polimi.swim.servlets.msg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temporaryClasses.Message;
import temporaryClasses.User;

/**
 * Servlet implementation class ExpandConvServlet
 */
public class ExpandConvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExpandConvServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Message> messages = new ArrayList<Message>();

		User fromuser = new User("andrea", "canidio");
		fromuser.setId(Integer.parseInt(request.getParameter("id")));
		User me = new User("lorenzo", "affetti");

		Message message1 = new Message("Hi user with id "
				+ request.getParameter("id"));
		message1.setFromU(fromuser);

		Message message2 = new Message("Hi man!");
		message2.setFromU(me);

		messages.add(message1);
		messages.add(message2);

		// loads from the db

		request.setAttribute("messages", messages);

		request.getRequestDispatcher("expandconv.view").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
