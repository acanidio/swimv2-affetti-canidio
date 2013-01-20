package it.polimi.swim.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conversation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	private User sender;

	@ManyToOne(targetEntity = User.class, optional = false)
	private User receiver;
	
	@OneToMany(targetEntity = Message.class, mappedBy = "conversation", fetch = FetchType.EAGER)
	private Set<Message> messages;
	
	public Conversation() {
		super();
	}
	
	public int getID() {
		return ID;
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

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	
	public Message getLastMessage() {
		int maxID = 0;
		Message lastMessage = null;
		for(Message m: messages) {
			if(m.getID()>maxID) {
				lastMessage = m;
			}
		}
		return lastMessage;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Conversation)) {
			return false;
		}
		Conversation cnv = (Conversation) obj;
		if(ID == cnv.getID()) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return ID;
	}
}
