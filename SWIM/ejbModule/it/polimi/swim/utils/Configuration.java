package it.polimi.swim.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;

public class Configuration {

	private static final String SERVLETS_PATH = "WEB-INF/servlets.properties";
	private static final String VIEWS_PATH = "WEB-INF/views.properties";
	private static final String STORING_PATH = "WEB-INF/storing.properties";
	private static Properties views;
	private static Properties servlets;
	private static Properties storing;
	
	public static InitialContext getInitialContext() {
		try {
			Properties env = new Properties();
			env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			env.put(Context.PROVIDER_URL, "localhost:1099");
			env.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming");
			return new InitialContext(env);
		} catch (NamingException e) {
			return null;
		}
	}

	public static void init(ServletConfig config) {

		String viewsFile = config.getServletContext().getRealPath(VIEWS_PATH);
		String servletsFile = config.getServletContext().getRealPath(
				SERVLETS_PATH);
		String storingFile = config.getServletContext().getRealPath(STORING_PATH);

		views = new Properties();
		servlets = new Properties();
		storing = new Properties();

		try {

			views.load(new FileInputStream(viewsFile));
			servlets.load(new FileInputStream(servletsFile));
			storing.load(new FileInputStream(storingFile));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * for(Iterator params = servlets.keySet().iterator(); params.hasNext();
		 * ){ String next = (String) params.next(); System.out.println(next+ " "
		 * + servlets.getProperty(next)); }
		 */

	}

	public static String getView(String view) {
		return views.getProperty(view);
	}

	public static String getServlet(String servlet) {
		return servlets.getProperty(servlet);
	}
	
	public static String getStoreClass(String storeClass){
		return storing.getProperty(storeClass);
	}
}
