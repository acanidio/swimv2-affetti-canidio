package it.polimi.swim.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temporaryClasses.Ability;

/**
 * Servlet implementation class PendingAbilitiesServlet
 */
public class PendingAbilitiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PendingAbilitiesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//loads pending abilities from the db
		
		List<Ability> abilities = new ArrayList<Ability>();
		abilities.add(new Ability("Plumber"));
		abilities.add(new Ability("Cooker"));
		abilities.add(new Ability("Musician"));
		abilities.add(new Ability("DeathEater"));
		
		request.setAttribute("pendAbilities", abilities);
		
		request.getRequestDispatcher("pendabilities.view").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request, response);	}

}
