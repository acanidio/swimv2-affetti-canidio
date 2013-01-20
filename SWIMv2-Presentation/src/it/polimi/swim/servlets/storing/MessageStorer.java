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

	@Override
	public void store(HttpServletRequest request) {
		String recipient = request.getParameter("friends");
		String text = request.getParameter("text");
		String stringedConvid = request.getParameter("convid");
		int recID;
		
		Person user = (Person) request.getSession().getAttribute("person");
		
		
		InitialContext ctx = Configuration.getInitialContext();
		
		try {
			ConversationManagerRemote convmgr = (ConversationManagerRemote) ctx.lookup(ConversationManager.REMOTE);

		if (recipient!=null && !recipient.isEmpty()) {
			recID = Integer.parseInt(recipient);	
			
		}else{
			
			convID = Integer.parseInt(stringedConvid);
			
			int tempRec = convmgr.loadSpecificConversation(convID).getReceiver().getID();
			int tempSend = convmgr.loadSpecificConversation(convID).getSender().getID();
			if(user.getID() == tempRec){
			recID = tempSend;
			}else{
				recID = tempRec;
			}
		}
		
		convID = convmgr.existConversationBetween(user.getID(), recID);
		
		if(convID == null){
			convID = convmgr.createConversation(user.getID(), recID);
		}
		
		convmgr.sendMessage(convID, user.getID(), text);
		
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String getForwardingPath(HttpServletRequest request) {
		return "expandconv.servlet?id=" + convID;
	}

}
