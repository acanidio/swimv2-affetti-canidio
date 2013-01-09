package it.polimi.swim.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temporaryClasses.Ability;

/**
 * Servlet implementation class LoadAbilitiesServlet
 */
public class LoadAbilitiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAbilitiesServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//loads abilities from database
		String forwardingPath= request.getParameter("path");
		
		List<Ability> abilities = new ArrayList<Ability>();
		Ability a1 = new Ability("Cooker");
		Ability a2 = new Ability("Musician");
		abilities.add(a1);
		abilities.add(a2);
		
		request.setAttribute("abilities", abilities);
		
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
