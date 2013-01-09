package it.polimi.swim.servlets.helprequest;

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
		List<HelpRequest> hrs = new ArrayList<HelpRequest>();
		hrs.add(new HelpRequest(new User("pinco", "pallino"), "Filtered help request", new Ability("Plumber")));
		
		request.setAttribute("hrs", hrs);
		request.getRequestDispatcher("filteredwall.view").forward(request, response);
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
