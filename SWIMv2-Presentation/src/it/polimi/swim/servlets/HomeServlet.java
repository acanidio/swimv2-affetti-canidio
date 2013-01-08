package it.polimi.swim.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temporaryClasses.Ability;
import temporaryClasses.HelpRequest;
import temporaryClasses.User;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String type = (String) request.getSession().getAttribute("type");

		if (type != null && type.equals("user")) {
			// load help requests from the db to fill the wall
			List<HelpRequest> helpreqs = new ArrayList<HelpRequest>();
			HelpRequest hr1 = new HelpRequest(new User("Lorenzo", "Affetti"),
					"I need somebody to love", new Ability("Be Freddy Mercury"));
			HelpRequest hr2 = new HelpRequest(new User("Andrea", "Canidio"),
					"I need a pizza boy", new Ability("Pizza boy"));

			helpreqs.add(hr1);
			helpreqs.add(hr2);

			request.setAttribute("helpreqs", helpreqs);

			request.getRequestDispatcher("userhome.view").forward(request, response);
		} else {
			request.getRequestDispatcher("guestadminhome.view").forward(
					request, response);
		}
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
