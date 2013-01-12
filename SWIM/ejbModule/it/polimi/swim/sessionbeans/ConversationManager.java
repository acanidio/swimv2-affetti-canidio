package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Conversation;
import it.polimi.swim.entities.Message;
import it.polimi.swim.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class ConversationManager
 */
@Stateless
@RemoteBinding(jndiBinding = ConversationManager.REMOTE)
public class ConversationManager implements ConversationManagerRemote {

	public static final String REMOTE = "ConversationManager/remote";

	@PersistenceContext(unitName = "SWIMPU")
	private EntityManager manager;
	
	@Override
	public Integer existConversationBetween(int IDFirstUser, int IDSecondUser) {
		Conversation c = null;
		try {
			Query q = manager.createQuery("SELECT c " +
										"FROM c JOIN c.sender u1 JOIN c.receiver u2 " +
										"WHERE (u1.ID = :IDFirstUser AND u2.ID = :IDSecondUser) OR " +
										"(u2.ID = :IDFirstUser AND u1.ID = :IDSecondUser)");
			c = (Conversation) q.setParameter("IDFirstUser", IDFirstUser)
					.setParameter("IDSecondUser", IDSecondUser)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		return c.getID();
	}

	@Override
	public Integer sendMessage(int IDConversation, int IDSender, String text) {
		Message m = null;
		try {
			Conversation c = manager.find(Conversation.class, IDConversation);
			User sender = manager.find(User.class, IDSender);
			// The sender is not either the conversation's sender or the conversation's receiver
			if(!sender.equals(c.getSender())||!sender.equals(c.getReceiver())) {
				return null;
			}
			User receiver;
			if(sender.equals(c.getSender())) {
				receiver = c.getReceiver();
			} else {
				receiver = c.getSender();
			}
			m = new Message();
			m.setConversation(c);
			m.setSender(sender);
			m.setReceiver(receiver);
			m.setText(text);
			manager.persist(m);
		} catch (Exception e) {
			return null;
		}
		return m.getID();
	}
	
	@Override
	public Conversation loadSpecificConversation(int IDConversation, int IDReceiver) {
		Conversation conversation = null;
		try {
			conversation = manager.find(Conversation.class, IDConversation);
			User receiver = manager.find(User.class, IDReceiver);
			for(Message currentMessage: conversation.getMessages()) {
				if(currentMessage.getReceiver().equals(receiver) && currentMessage.isUnreaded()) {
					currentMessage.setUnreaded(false);
					manager.merge(currentMessage);
				}
			}
		} catch (Exception e) {
			return null;
		}
		return conversation;
	}

	@Override
	public Integer createConversation(int IDSender, int IDReceiver) {
		Conversation c = null;
		try {
			c = new Conversation();
			c.setSender(manager.find(User.class, IDSender));
			c.setReceiver(manager.find(User.class, IDReceiver));
			manager.persist(c);
		} catch (Exception e) {
			return null;
		}
		return c.getID();
	}
}
