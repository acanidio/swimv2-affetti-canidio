package it.polimi.swim.servlets.msg;

import it.polimi.swim.entities.Message;
import it.polimi.swim.entities.Person;
import it.polimi.swim.sessionbeans.UserDataManager;
import it.polimi.swim.utils.Configuration;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

InitialContext ctx = Configuration.getInitialContext();
		
		Person user = (Person) request.getAttribute("person");
		
		UserDataManager usermgr;
		try {
			usermgr = (UserDataManager) ctx.lookup(UserDataManager.REMOTE);
			
			List<Message> messages = usermgr.loadNewReceivedMessages(user.getID());
			
			request.setAttribute("incoming", messages);
		} catch (NamingException e) {
			e.printStackTrace();
		}

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
