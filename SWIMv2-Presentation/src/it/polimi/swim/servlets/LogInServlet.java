package it.polimi.swim.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temporaryClasses.User;

/**
 * Servlet implementation class LogInServlet
 */
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public LogInServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");

		User loggedUser = new User(name, "");

		request.getSession().setAttribute("user", loggedUser);

		if (name.equals("admin")) {
			request.getSession().setAttribute("type", "admin");
		} else {
			request.getSession().setAttribute("type", "user");
		}

		request.setAttribute("log", "you are logged as " + loggedUser.getId()
				+ "-" + name);

		request.getRequestDispatcher("home.servlet").forward(request, response);
	}

	// these methods looks into the database (using beans).
	private boolean isAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isUser() {
		// TODO Auto-generated method stub
		return true;
	}

}
