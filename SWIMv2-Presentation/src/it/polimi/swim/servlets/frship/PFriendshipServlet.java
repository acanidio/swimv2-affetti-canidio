package it.polimi.swim.servlets.frship;

import it.polimi.swim.entities.Friendship;
import it.polimi.swim.entities.Person;
import it.polimi.swim.sessionbeans.UserDataManager;
import it.polimi.swim.sessionbeans.UserDataManagerRemote;
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
 * Servlet implementation class PFriendshipServlet
 */
public class PFriendshipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PFriendshipServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InitialContext ctx = Configuration.getInitialContext();

		Person user = (Person) request.getSession().getAttribute("person");
		
		UserDataManagerRemote usermgr;
		try {
			usermgr = (UserDataManagerRemote) ctx.lookup(UserDataManager.REMOTE);

			List<Friendship> pfriendships = usermgr.loadPendingFriendships(user.getID());
			
			request.setAttribute("pfriendships", pfriendships);
		} catch (NamingException e) {
			e.printStackTrace();
		}
				
		RequestDispatcher disp = request.getRequestDispatcher("pfrships.view");
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
