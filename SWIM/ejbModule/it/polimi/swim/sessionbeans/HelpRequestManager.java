package it.polimi.swim.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class HelpRequestManager
 */
@Stateless
@RemoteBinding(jndiBinding = HelpRequestManager.REMOTE)
public class HelpRequestManager implements HelpRequestManagerRemote {

    public static final String REMOTE = "HelpRequestManager/remote";
    
    @PersistenceContext(unitName = "SWIMPU")
    private EntityManager manager;
}
