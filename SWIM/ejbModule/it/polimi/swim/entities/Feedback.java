package it.polimi.swim.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Feedback
 *
 */
@Entity

public class Feedback implements Serializable {

	
	private static final String REPLY = "IDReplyFeedback";

	private static final String ABILITY = "IDAbilityFeedback";

	private static final String SENDER = "IDSenderFeedback";

	private static final long serialVersionUID = 1L;
	
	@Id
	private int ID;
	
	private int mark;
	
	private String description;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	@JoinColumn(name = SENDER)
	private User sender;
	
	@ManyToOne(targetEntity = Ability.class, optional = false)
	@JoinColumn(name = ABILITY)
	private Ability ability;
	
	@ManyToOne(targetEntity = Reply.class, optional = false)
	@JoinColumn(name = REPLY)
	private Reply reply;

	public Feedback() {
		super();
		ID = (int) System.nanoTime();
	}
	
	public int getID() {
		return ID;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Ability getAbility() {
		return ability;
	}

	public void setAbility(Ability ability) {
		this.ability = ability;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}
}