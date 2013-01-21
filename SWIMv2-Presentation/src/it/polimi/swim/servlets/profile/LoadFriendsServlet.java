package it.polimi.swim.servlets.profile;

import it.polimi.swim.entities.Person;
import it.polimi.swim.entities.User;
import it.polimi.swim.sessionbeans.UserDataManager;
import it.polimi.swim.sessionbeans.UserDataManagerRemote;
import it.polimi.swim.utils.Configuration;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadFriendsServlet
 */
public class LoadFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadFriendsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forwardingPath = request.getParameter("path");

		Person user = (Person) request.getSession().getAttribute("person");

		InitialContext ctx = Configuration.getInitialContext();
		UserDataManagerRemote usermgr;
		try {
			usermgr = (UserDataManagerRemote) ctx
					.lookup(UserDataManager.REMOTE);

			List<User> friends = usermgr.loadFriends(user.getID());
			
			if(friends==null || friends.isEmpty()){
				request.setAttribute("log", "You don't have any friend.");
			}

			request.setAttribute("friends", friends);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher(forwardingPath).forward(request, response);
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
