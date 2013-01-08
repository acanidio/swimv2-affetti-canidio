package it.polimi.swim.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.polimi.swim.sessionbeans.UserDataManager;
import it.polimi.swim.sessionbeans.UserDataManagerRemote;
import it.polimi.swim.utils.Configuration;

import javax.naming.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProfileDataManagerTest {

	private Context context;
	
	private UserDataManagerRemote manager;
	
	@Before
	public void setUp() throws Exception {
		context = Configuration.getInitialContext();
		manager = (UserDataManagerRemote) context.lookup(UserDataManager.REMOTE);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testVerifySubmittedUserRegistration() {
		assertTrue(manager.verifyUser("pippo@pluto.paperino"));
		
	}

	@Test
	public void testRegisterNewUser() {
		fail("Not yet implemented");
	}

}
