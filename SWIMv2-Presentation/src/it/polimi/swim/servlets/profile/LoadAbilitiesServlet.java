package it.polimi.swim.servlets.profile;

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
		String forwardingPath= request.getParameter("path");
		
		InitialContext ctx = Configuration.getInitialContext();
		AbilityManagerRemote abilitymgr;
		try {
			abilitymgr = (AbilityManagerRemote) ctx.lookup(AbilityManager.REMOTE);
			
			List<Ability> abilities = abilitymgr.getAbilityList();
			
			request.setAttribute("abilities", abilities);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
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
