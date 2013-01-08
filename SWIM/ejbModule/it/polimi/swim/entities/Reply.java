package it.polimi.swim.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Reply
 *
 */
@Entity

public class Reply implements Serializable {

	
	private static final String REQUEST = "IDRequestReply";

	private static final String SENDER = "IDSenderReply";

	private static final long serialVersionUID = 1L;
	
	@Id
	private int ID;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	@JoinColumn(name = SENDER)
	private User sender;
	
	@ManyToOne(targetEntity = HelpRequest.class, optional = false)
	@JoinColumn(name = REQUEST)
	private HelpRequest request;
	
	private boolean best;

	public Reply() {
		super();
		ID = (int) System.nanoTime();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public HelpRequest getRequest() {
		return request;
	}

	public void setRequest(HelpRequest request) {
		this.request = request;
	}

	public boolean isBest() {
		return best;
	}

	public void setBest(boolean best) {
		this.best = best;
	}
   
}
