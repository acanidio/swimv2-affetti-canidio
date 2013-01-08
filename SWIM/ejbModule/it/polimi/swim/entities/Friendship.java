package it.polimi.swim.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Friendship
 *
 */
@Entity

public class Friendship implements Serializable {

	
	private static final String RECEIVER = "IDReceiverFriendship";

	private static final String SENDER = "IDSenderFriendship";

	private static final long serialVersionUID = 1L;
	
	@Id
	private int ID;
	
	private boolean accepted;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	@JoinColumn(name = SENDER)
	private User sender;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	@JoinColumn(name = RECEIVER)
	private User receiver;

	public Friendship() {
		super();
		ID = (int) System.nanoTime();
	}
	
	public int getID() {
		return ID;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
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
	
}
