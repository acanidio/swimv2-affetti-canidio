package it.polimi.swim.servlets.profile;

import it.polimi.swim.entities.Person;
import it.polimi.swim.entities.User;
import it.polimi.swim.sessionbeans.UserDataManager;
import it.polimi.swim.sessionbeans.UserDataManagerRemote;
import it.polimi.swim.utils.Configuration;

import java.io.IOException;
import java.sql.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProfileDataServlet
 */
public class ModifyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyProfileServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("person");

		InitialContext ctx = Configuration.getInitialContext();

		try {
			UserDataManagerRemote usermgr = (UserDataManagerRemote) ctx
					.lookup(UserDataManager.REMOTE);

			String password = request.getParameter("password");

			if (password == null || password.isEmpty()) {
				password = user.getPassword();
			}
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String city = request.getParameter("city");
			String bday = request.getParameter("birthday");

			Date birthday = user.getBirthday();

			if (bday != null && !bday.isEmpty()) {
				birthday = Date.valueOf(bday);
			}

			String phonenumber = request.getParameter("phonenumber");

			usermgr.modifyUser(user.getID(), password, name, surname,
					city, birthday, phonenumber);

			String sabilityID = request.getParameter("ability0");

			for (int i = 1; sabilityID != null && !sabilityID.isEmpty(); i++) {
				if (!sabilityID.equals("none")) {
					int abilityID = Integer.parseInt(sabilityID);
					usermgr.addAbilityToUser(user.getID(), abilityID);
				}

				sabilityID = request.getParameter("ability" + i);
			}

			Person updatedUser = usermgr.loadProfile(user.getID());
			
			request.setAttribute("log", "Profile modified succesfully.");
			request.getSession().setAttribute("person", updatedUser);

		} catch (NamingException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("loaduser.servlet?id=" + user.getID())
				.forward(request, response);
	}

}
