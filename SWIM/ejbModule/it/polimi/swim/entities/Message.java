package it.polimi.swim.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Message
 *
 */

@Entity
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	
	private String text;
	
	private boolean unreaded;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	private User sender;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	private User receiver;
	
	@ManyToOne(targetEntity = Conversation.class, optional = false)
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
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Message)) {
			return false;
		}
		Message message = (Message) obj;
		if(ID == message.getID()) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return ID;
	}
}
