package it.polimi.swim.servlets.profile;

import it.polimi.swim.entities.Ability;
import it.polimi.swim.entities.Friendship;
import it.polimi.swim.entities.Person;
import it.polimi.swim.entities.User;
import it.polimi.swim.sessionbeans.FriendshipManager;
import it.polimi.swim.sessionbeans.FriendshipManagerRemote;
import it.polimi.swim.sessionbeans.UserDataManager;
import it.polimi.swim.sessionbeans.UserDataManagerRemote;
import it.polimi.swim.utils.Configuration;

import java.io.IOException;
import java.util.Hashtable;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InitialContext ctx = Configuration.getInitialContext();
		int otherUserID = Integer.parseInt(request.getParameter("id"));

		UserDataManagerRemote usermgr;
		try {
			usermgr = (UserDataManagerRemote) ctx
					.lookup(UserDataManager.REMOTE);

			Person user = usermgr.loadProfile(otherUserID);

			if (user instanceof User) {
				Hashtable<Ability, Float> abilities = usermgr
						.loadUserAbilities(user.getID());
				request.setAttribute("userAbilities", abilities);
				
				/*
				//TODO delete
				for(Ability a : abilities.keySet()){
					System.out.println(a.getName()+" - "+abilities.get(a));
				}
				*/
			}
			
			
			

			request.setAttribute("user", user);

			Person loggedUser = (Person) request.getSession().getAttribute(
					"person");

			FriendshipManagerRemote frmgr = (FriendshipManagerRemote) ctx
					.lookup(FriendshipManager.REMOTE);

			boolean reqExists = frmgr.haveFriendshipRequestBetween(
					loggedUser.getID(), otherUserID);
			Friendship fr = frmgr.getFriendshipRequest(loggedUser.getID(),
					otherUserID);
			boolean accepted = false;
			Integer frID = null;
			
			if (fr != null) {
				accepted = fr.isAccepted();
				frID = fr.getID();
			}

			request.setAttribute("reqexists", Boolean.valueOf(reqExists));
			request.setAttribute("accepted", Boolean.valueOf(accepted));
			request.setAttribute("frid", frID);

		} catch (NamingException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("userprofile.view").forward(request,
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
