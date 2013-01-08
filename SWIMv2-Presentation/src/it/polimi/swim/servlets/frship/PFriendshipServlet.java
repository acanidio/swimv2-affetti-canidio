package it.polimi.swim.servlets.frship;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temporaryClasses.Friendship;
import temporaryClasses.User;

/**
 * Servlet implementation class PFriendshipServlet
 */
public class PFriendshipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PFriendshipServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//looks if there are some pending friendships into the db
		
		List pfriendships = new ArrayList();
		
		Friendship f1 = new Friendship();
		f1.setFromU(new User("vanessa", "monterubbianesi"));
		f1.setToU(new User("lorenzo", "affetti"));
		f1.setPending(true);
		
		Friendship f2 = new Friendship();
		f2.setFromU(new User("matteo", "daniele"));
		f2.setToU(new User("lorenzo", "affetti"));
		f2.setPending(true);
		
		Friendship f3 = new Friendship();
		f3.setFromU(new User("andrea", "canidio"));
		f3.setToU(new User("lorenzo", "affetti"));
		f3.setPending(true);
		
		pfriendships.add(f1);
		pfriendships.add(f2);
		pfriendships.add(f3);
		
		request.setAttribute("pfriendships", pfriendships);
		
		RequestDispatcher disp = request.getRequestDispatcher("pfrships.view");
		disp.forward(request, response);
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
