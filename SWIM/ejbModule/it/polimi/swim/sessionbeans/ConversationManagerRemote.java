package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.Conversation;
import it.polimi.swim.entities.Message;

import javax.ejb.Remote;

@Remote
public interface ConversationManagerRemote {
	
	public Integer existConversationBetween(int IDFirstUser, int IDSecondUser);
	
	public Integer createConversation(int IDSender, int IDReceiver);
	
	public Integer sendMessage(int IDConversation, int IDSender, String text);
	
	public Conversation loadSpecificConversation(int IDConversation, int IDReceiver);
	
	public boolean isSender(int IDConversation, int IDUser);
	
	public Message getLastMessage(int IDConversation);
	
	public Conversation getConversation(int IDConversation);
}