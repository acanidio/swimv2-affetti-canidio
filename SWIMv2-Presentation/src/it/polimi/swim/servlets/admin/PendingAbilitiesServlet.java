package it.polimi.swim.servlets.admin;

import it.polimi.swim.entities.Ability;
import it.polimi.swim.sessionbeans.AbilityManager;
import it.polimi.swim.sessionbeans.AbilityManagerRemote;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InitialContext ctx = Configuration.getInitialContext();

		AbilityManagerRemote abmgr;
		try {
			abmgr = (AbilityManagerRemote) ctx.lookup(AbilityManager.REMOTE);

			List<Ability> abilities = abmgr.loadPendingAbilities();
			
			if(abilities==null || abilities.isEmpty()){
				request.setAttribute("info", "You don't have pending abilities.");
			}
			
			request.setAttribute("pendAbilities", abilities);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("pendabilities.view").forward(request,
				response);
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
