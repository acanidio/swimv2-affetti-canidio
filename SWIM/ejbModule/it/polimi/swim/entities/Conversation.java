package it.polimi.swim.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conversation implements Serializable {

	private static final String SECOND = "IDSecondUser";

	private static final String FIRST = "IDFirstUser";

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int ID;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	@JoinColumn(name = FIRST)
	private User sender;
	
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

	@ManyToOne(targetEntity = User.class, optional = false)
	@JoinColumn(name = SECOND)
	private User receiver;
	
	@OneToMany(targetEntity = Message.class, mappedBy = "conversation")
	private List<Message> messages;
	
	public int getID() {
		return ID;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
