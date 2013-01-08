package it.polimi.swim.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Message
 *
 */

@Entity
public class Message implements Serializable {

	
	private static final String CONVERSATION = "IDConversation";

	private static final String SENDER = "IDSenderMessage";

	private static final String RECEIVER = "IDReceiverMessage";

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int ID;
	
	private String text;
	
	private boolean unreaded;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	@JoinColumn(name = SENDER)
	private User sender;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	@JoinColumn(name = RECEIVER)
	private User receiver;
	
	@ManyToOne(targetEntity = Conversation.class, optional = false)
	@JoinColumn(name = CONVERSATION)
	private Conversation conversation;

	public Message() {
		super();
		unreaded = true;
	}
	
	public int getID() {
		return ID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isUnreaded() {
		return unreaded;
	}

	public void setUnreaded(boolean unreaded) {
		this.unreaded = unreaded;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
   
}
