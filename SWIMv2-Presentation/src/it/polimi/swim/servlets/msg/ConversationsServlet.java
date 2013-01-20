package it.polimi.swim.servlets.msg;

import it.polimi.swim.entities.Conversation;
import it.polimi.swim.entities.Person;
import it.polimi.swim.sessionbeans.UserDataManager;
import it.polimi.swim.sessionbeans.UserDataManagerRemote;
import it.polimi.swim.utils.Configuration;

import java.io.IOException;
import java.util.List;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ConversationsServlet
 */
public class ConversationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConversationsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		InitialContext ctx = Configuration.getInitialContext();
		
		Person user = (Person) request.getSession().getAttribute("person");
		
		UserDataManagerRemote usermgr;
		try {
			usermgr = (UserDataManagerRemote) ctx.lookup(UserDataManager.REMOTE);
			
			List<Conversation> convs = usermgr.loadConversations(user.getID());
			
			request.setAttribute("convs", convs);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher disp = request.getRequestDispatcher("conversations.view");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
