package it.polimi.swim.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import temporaryClasses.Configuration;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Configuration.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		handleRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		handleRequest(request, response);
	}

	private void handleRequest(HttpServletRequest request,
			HttpServletResponse response) {

		String path = request.getServletPath();

		if (path.startsWith("/")) {
			path = path.substring(1);
		}

		String key = "";
		String forwardPath = "";
		String[] splittedPath = null;

		if (path.matches(".*view")) {
			splittedPath = path.split(".view");
			key = splittedPath[0];
			forwardPath = Configuration.getView(key);
		} else if (path.matches(".*servlet")) {
			splittedPath = path.split(".servlet");
			key = splittedPath[0];
			forwardPath = Configuration.getServlet(key);
		}

		// TODO remove these lines
		System.out.println(path);
		System.out.println(key);
		System.out.println(forwardPath);

		if (forwardPath == null) {
			key = "home";
			forwardPath = Configuration.getServlet(key);
		}

		for (int i = 1; i < splittedPath.length; i++) {
			forwardPath += splittedPath[i];
		}
		
		System.out.println(forwardPath);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher(forwardPath);

		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
