package it.polimi.swim.servlets.helprequest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temporaryClasses.Ability;
import temporaryClasses.HelpRequest;
import temporaryClasses.User;

/**
 * Servlet implementation class LoadHelpRequestServlet
 */
public class LoadHelpRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadHelpRequestServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//loads the right help request from the db using request.getParameter("id");
		
		HelpRequest hr = new HelpRequest(new User("davide", "brambilla"), "Qualcuno mi formatti il pc plz", new Ability("Tecnico"));
		
		request.setAttribute("hr", hr);
		
		request.getRequestDispatcher("hr.view").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
