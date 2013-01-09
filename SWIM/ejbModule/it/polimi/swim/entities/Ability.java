package it.polimi.swim.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ability
 *
 */

@Entity
public class Ability implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int ID;
	
	private String name;
	
	private boolean pending;
	
	@OneToMany(targetEntity = HelpRequest.class, mappedBy = "ability")
	private List<HelpRequest> helprequests;
	
	@OneToMany(targetEntity = Feedback.class, mappedBy = "ability")
	private List<Feedback> feedbacks;

	public Ability() {
		super();
	}
	
	public int getID(){
		return ID;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HelpRequest> getHelprequests() {
		return helprequests;
	}

	public void setHelprequests(List<HelpRequest> helprequests) {
		this.helprequests = helprequests;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
   
}
