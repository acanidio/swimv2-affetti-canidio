package it.polimi.swim.servlets.storing;

import it.polimi.swim.entities.Person;
import it.polimi.swim.sessionbeans.ConversationManager;
import it.polimi.swim.sessionbeans.ConversationManagerRemote;
import it.polimi.swim.utils.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class MessageStorer implements DataStorer {
	private Integer convID;
	private String forwardingPath = "";

	@Override
	public void store(HttpServletRequest request) {
		String recipient = request.getParameter("friend");
		String text = request.getParameter("text");
		String stringedConvid = request.getParameter("convid");
		int recID;

		Person user = (Person) request.getSession().getAttribute("person");
		
		if((recipient==null || recipient.isEmpty()) && (stringedConvid==null || stringedConvid.isEmpty())){
			request.setAttribute("error", "You cannot send a message to nobody!");
			forwardingPath = "error.view";
			return;
		}

		InitialContext ctx = Configuration.getInitialContext();

		try {
			ConversationManagerRemote convmgr = (ConversationManagerRemote) ctx
					.lookup(ConversationManager.REMOTE);

			if (recipient != null && !recipient.isEmpty()) {
				recID = Integer.parseInt(recipient);
				convID = convmgr.existConversationBetween(user.getID(), recID);
				
				if (convID == null) {
					convID = convmgr.createConversation(user.getID(), recID);
				}
			} else {

				convID = Integer.parseInt(stringedConvid);

				int tempRec = convmgr.getConversation(convID)
						.getReceiver().getID();
				int tempSend = convmgr.getConversation(convID)
						.getSender().getID();
				if (user.getID() == tempRec) {
					recID = tempSend;
				} else {
					recID = tempRec;
				}
			}

			convmgr.sendMessage(convID, user.getID(), text);

		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		if(!forwardingPath.equals("error.view")){
			forwardingPath = "expandconv.servlet?id="+convID;
		}
		
		return forwardingPath;
	}

}
