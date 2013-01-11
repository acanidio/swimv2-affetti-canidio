package it.polimi.swim.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Reply
 *
 */
@Entity

public class Reply implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	private User sender;
	
	@ManyToOne(targetEntity = HelpRequest.class, optional = false)
	private HelpRequest request;

	@OneToOne(targetEntity = Feedback.class, mappedBy = "reply", optional = false)
	private Feedback feedback;
	
	private boolean best;

	public Reply() {
		super();
		best = false;
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

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
   
}
