package it.polimi.swim.servlets.msg;

import it.polimi.swim.entities.Conversation;
import it.polimi.swim.entities.Person;
import it.polimi.swim.sessionbeans.ConversationManager;
import it.polimi.swim.sessionbeans.ConversationManagerRemote;
import it.polimi.swim.utils.Configuration;

import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		InitialContext ctx = Configuration.getInitialContext();

		int convID = Integer.parseInt(request.getParameter("id"));
		Person user = (Person) request.getSession().getAttribute("person");

		ConversationManagerRemote convmgr;
		try {
			convmgr = (ConversationManagerRemote) ctx
					.lookup(ConversationManager.REMOTE);
			Conversation conv = convmgr.loadSpecificConversation(convID, user.getID());

			request.setAttribute("conv", conv);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher(
				"expandconv.view").forward(
				request, response);
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
