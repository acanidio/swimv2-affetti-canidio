package it.polimi.swim.servlets;

import it.polimi.swim.entities.HelpRequest;
import it.polimi.swim.entities.User;
import it.polimi.swim.sessionbeans.HelpRequestManager;
import it.polimi.swim.sessionbeans.HelpRequestManagerRemote;
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
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String type = (String) request.getSession().getAttribute("type");

		if (type != null && type.equals(User.TYPE)) {
			InitialContext ctx = Configuration.getInitialContext();
			
			HelpRequestManagerRemote hrmgr;
			try {
				hrmgr = (HelpRequestManagerRemote) ctx.lookup(HelpRequestManager.REMOTE);
				
				List<HelpRequest> helpreqs = hrmgr.getHelpRequests();
				request.setAttribute("helpreqs", helpreqs);
				
			} catch (NamingException e) {
				e.printStackTrace();
			}

			request.getRequestDispatcher("userhome.view").forward(request, response);
		} else {
			
			request.getRequestDispatcher("guestadminhome.view").forward(
					request, response);
		}
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
