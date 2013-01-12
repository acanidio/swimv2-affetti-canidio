package it.polimi.swim.servlets;

import it.polimi.swim.entities.Administrator;
import it.polimi.swim.entities.Person;
import it.polimi.swim.entities.User;
import it.polimi.swim.sessionbeans.LoginManager;
import it.polimi.swim.sessionbeans.LoginManagerRemote;
import it.polimi.swim.utils.Configuration;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		InitialContext ctx = Configuration.getInitialContext();
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			LoginManagerRemote loginmgr = (LoginManagerRemote) ctx
					.lookup(LoginManager.REMOTE);

			Integer id = loginmgr.validLogin(email, password);

			if (id != null) {
				Person p = loginmgr.loadPerson(id);
				
				request.getSession().setAttribute("person", p);

				if (p instanceof Administrator) {
					request.getSession().setAttribute("type", Administrator.TYPE);
				} else {
					request.getSession().setAttribute("type", User.TYPE);
				}
			} else {
				response.getWriter().println("Not valid log in!<br>");
				response.getWriter().println("<a href='home.servlet'>Back to home</a>");
				return;
			}

		} catch (NamingException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("home.servlet").forward(request, response);
	}
}
