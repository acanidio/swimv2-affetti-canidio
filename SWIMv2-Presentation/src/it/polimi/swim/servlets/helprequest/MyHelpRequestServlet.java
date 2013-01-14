package it.polimi.swim.servlets.helprequest;

import it.polimi.swim.entities.HelpRequest;
import it.polimi.swim.entities.Person;
import it.polimi.swim.sessionbeans.UserDataManager;
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
 * Servlet implementation class MyHelpRequestServlet
 */
public class MyHelpRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyHelpRequestServlet() {
		super();
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

			List<HelpRequest> myhrs = usermgr
					.loadUserHelpRequests(user.getID());
			request.setAttribute("myhrs", myhrs);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("myhrs.view").forward(request, response);
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
