package it.polimi.swim.servlets.search;

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
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		InitialContext ctx = Configuration.getInitialContext();

		String username = request.getParameter("username");
		String ability = request.getParameter("ability0");
		String city = request.getParameter("city");
		Integer abID = null;

		if (ability != null && !ability.equals("none")) {
			abID = Integer.parseInt(ability);
		}

		try {
			UserDataManagerRemote usermgr = (UserDataManagerRemote) ctx
					.lookup(UserDataManager.REMOTE);

			List<User> results = usermgr.searchUsers(username, city, abID);

			if (results == null || results.isEmpty()) {
				request.setAttribute("log", "No result found.");
			}

			request.setAttribute("results", results);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("searchres.view").forward(request,
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
