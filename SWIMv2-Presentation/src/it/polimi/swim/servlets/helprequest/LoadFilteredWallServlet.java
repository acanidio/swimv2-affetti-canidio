package it.polimi.swim.servlets.helprequest;

import it.polimi.swim.entities.HelpRequest;
import it.polimi.swim.entities.Person;
import it.polimi.swim.sessionbeans.HelpRequestManager;
import it.polimi.swim.sessionbeans.HelpRequestManagerRemote;
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
 * Servlet implementation class LoadFilteredWallServlet
 */
public class LoadFilteredWallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadFilteredWallServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String friends = request.getParameter("friends");
		boolean checked = false;
		String city = request.getParameter("city");
		String ability = request.getParameter("ability0");
		Integer abID = null;

		Person user = (Person) request.getSession().getAttribute("person");

		if (ability != null && !ability.equals("none")) {
			abID = Integer.parseInt(ability);
		}

		if (friends != null && friends.equals("true")) {
			checked = true;
		}
		InitialContext ctx = Configuration.getInitialContext();

		try {
			HelpRequestManagerRemote hrmgr = (HelpRequestManagerRemote) ctx
					.lookup(HelpRequestManager.REMOTE);

			List<HelpRequest> hrs = hrmgr.searchHelpRequests(checked,
					user.getID(), city, abID);

			if (hrs == null || hrs.isEmpty()) {
				request.setAttribute("log",
						"No result found.");
			}

			request.setAttribute("hrs", hrs);

		} catch (NamingException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("filteredwall.view").forward(request,
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
