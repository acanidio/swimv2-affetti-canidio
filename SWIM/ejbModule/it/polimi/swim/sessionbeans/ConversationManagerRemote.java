package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Conversation;

import javax.ejb.Remote;

@Remote
public interface ConversationManagerRemote {
	
	public Integer existConversationBetween(int IDFirstUser, int IDSecondUser);
	
	public Integer createConversation(int IDSender, int IDReceiver);
	
	public Integer sendMessage(int IDConversation, int IDSender, String text);
	
	public Conversation loadSpecificConversation(int IDConversation);
	
	public boolean isSender(int IDConversation, int IDUser);
}