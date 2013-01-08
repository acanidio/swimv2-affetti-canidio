package it.polimi.swim.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temporaryClasses.User;

/**
 * Servlet implementation class LoadUserServlet
 */
public class LoadUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//searches for the user specified into the URL
		
		//request.setAttribute("log", "You where looking for user: "+request.getParameter("id"));
		
		//TODO sample user
		User sampleU = new User("sample", "user");
		sampleU.setId(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("user", sampleU);
		
		request.getRequestDispatcher("userprofile.view").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
