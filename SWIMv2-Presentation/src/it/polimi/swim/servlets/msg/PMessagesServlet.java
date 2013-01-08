package it.polimi.swim.servlets.msg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temporaryClasses.Message;
import temporaryClasses.User;

/**
 * Servlet implementation class PMessagesServlet
 */
public class PMessagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PMessagesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List messages = new ArrayList();

		Message mess1 = new Message(
				"Custom Tags are JSP elements which helps in maintaining reusable code in JSP.");
		mess1.setFromU(new User("andrea", "canidio"));

		Message mess2 = new Message(
				"I pizzoccheri alla valtellinese, primo piatto tradizionale della Valtellina, sono un gustoso piatto unico molto nutriente e facile da realizzare.");
		mess2.setFromU(new User("andrea", "brancaleoni"));

		messages.add(mess1);
		messages.add(mess2);

		request.setAttribute("incoming", messages);

		RequestDispatcher disp = request
				.getRequestDispatcher("incomingmsgs.view");
		disp.forward(request, response);

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
