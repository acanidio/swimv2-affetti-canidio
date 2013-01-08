package it.polimi.swim.servlets.msg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temporaryClasses.Conversation;
import temporaryClasses.User;

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
		//loads from DB the personal conversation of the user contained in this session...
		
		//up to now we create sample conversations...
		List convs = new ArrayList();
		
		Conversation conv1 = new Conversation();
		conv1.setUser(new User("Gianni", "Pinotto"));
		conv1.setMessage("Hi man! I'm Gianni, wanna enjoy swim?");
		
		Conversation conv2 = new Conversation();
		conv2.setUser(new User("Pinotto", "Gianni"));
		conv2.setMessage("Hi man! I'm Pinotto, wanna enjoy swim?");
		
		convs.add(conv1);
		convs.add(conv2);
		
		request.setAttribute("convs", convs);
		
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
