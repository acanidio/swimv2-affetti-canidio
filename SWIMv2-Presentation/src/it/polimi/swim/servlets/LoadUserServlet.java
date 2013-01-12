package it.polimi.swim.servlets;

import it.polimi.swim.entities.Person;
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
 * Servlet implementation class LoadUserServlet
 */
public class LoadUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InitialContext ctx = Configuration.getInitialContext();
		UserDataManagerRemote usermgr;
		try {
			usermgr = (UserDataManagerRemote) ctx.lookup(UserDataManager.REMOTE);
			
			Person user = usermgr.loadProfile(Integer.parseInt(request.getParameter("id")));
			
			request.setAttribute("user", user);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("userprofile.view").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
