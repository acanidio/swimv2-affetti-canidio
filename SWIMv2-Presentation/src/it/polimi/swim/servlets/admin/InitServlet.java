package it.polimi.swim.servlets.admin;

import it.polimi.swim.sessionbeans.AbilityManager;
import it.polimi.swim.sessionbeans.AbilityManagerRemote;
import it.polimi.swim.sessionbeans.UserDataManager;
import it.polimi.swim.sessionbeans.UserDataManagerRemote;
import it.polimi.swim.utils.Configuration;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class initServlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InitialContext ctx = Configuration.getInitialContext();

		String pw = request.getParameter("pw");
		String log = "";

		if (pw == null || !pw.equals("swim")) {
			log += "You don't have the permission to view this page.";
			request.setAttribute("log", log);
			request.getRequestDispatcher("init.view")
					.forward(request, response);
			return;
		}

		try {
			UserDataManagerRemote usermgr = (UserDataManagerRemote) ctx
					.lookup(UserDataManager.REMOTE);
			AbilityManagerRemote abmgr = (AbilityManagerRemote) ctx
					.lookup(AbilityManager.REMOTE);

			boolean created = usermgr.createAdmin();
			if (!created) {
				log += "Administrator already created.<br/>";
			} else {
				log += "Administrator correctly added to the database.<br/>";
			}
			
			String ability = "Cooker";

			if (abmgr.verifyNewAbility(ability)) {
				log += "Ability already into the database.";
				
			} else {
				
				abmgr.createNewAcceptedAbility(ability);
				log += "Ability correctly added to the database.";
			}

			request.setAttribute("log", log);

		} catch (NamingException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("init.view").forward(request, response);

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
