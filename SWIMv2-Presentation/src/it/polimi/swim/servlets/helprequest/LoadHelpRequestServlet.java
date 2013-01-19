package it.polimi.swim.servlets.helprequest;

import it.polimi.swim.entities.HelpRequest;
import it.polimi.swim.entities.Person;
import it.polimi.swim.entities.Reply;
import it.polimi.swim.sessionbeans.HelpRequestManager;
import it.polimi.swim.sessionbeans.HelpRequestManagerRemote;
import it.polimi.swim.utils.Configuration;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Person user = (Person) request.getSession().getAttribute("person");
		int hrID = Integer.parseInt(request.getParameter("id"));

		InitialContext ctx = Configuration.getInitialContext();

		HelpRequestManagerRemote hrmgr;
		try {
			hrmgr = (HelpRequestManagerRemote) ctx
					.lookup(HelpRequestManager.REMOTE);

			HelpRequest hr = hrmgr.getHelpRequest(hrID);
			request.setAttribute("hr", hr);
			
			boolean canReply = hrmgr.checkCanReplyToHelpRequest(user.getID(), hrID);
			boolean hasBR = hrmgr.hasBestReply(hrID);
			boolean hasFeed = hrmgr.hasFeedback(hrID);
			boolean postedByMe = hrmgr.postedByMe(user.getID(), hrID);
			
			Reply bestReply = hrmgr.getBestReply(hrID);
			
			if(bestReply!=null){
				hr.getReplies().remove(bestReply);
			}
			
			request.setAttribute("bestreply", bestReply);
			
			request.setAttribute("canReply", Boolean.valueOf(canReply));
			request.setAttribute("hasBR", Boolean.valueOf(hasBR));
			request.setAttribute("hasFeed", Boolean.valueOf(hasFeed));
			request.setAttribute("postedByMe", Boolean.valueOf(postedByMe));
			
			/*
			System.out.println("can reply: "+canReply);
			System.out.println("has br: "+hasBR);
			System.out.println("has feedback: "+hasFeed);
			System.out.println("posted by me: "+postedByMe);
			System.out.println("HR ID: "+hrID);
			System.out.println("USER ID: "+user.getID());
			*/

		} catch (NamingException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("hr.view").forward(request, response);
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
