package it.polimi.swim.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Friendship
 *
 */
@Entity

public class Friendship implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	
	private boolean accepted;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	private User sender;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	private User receiver;
	
	public Friendship() {
		super();
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
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Friendship)) {
			return false;
		}
		Friendship friendship = (Friendship) obj;
		if(ID == friendship.getID()) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return ID;
	}
}
