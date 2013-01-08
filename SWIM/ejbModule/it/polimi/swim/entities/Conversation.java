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
	private User first;
	
	@ManyToOne(targetEntity = User.class, optional = false)
	@JoinColumn(name = SECOND)
	private User second;
	
	@OneToMany(targetEntity = Message.class, mappedBy = "conversation")
	private List<Message> messages;
	
	public int getID() {
		return ID;
	}

	public User getFirst() {
		return first;
	}

	public void setFirst(User first) {
		this.first = first;
	}

	public User getSecond() {
		return second;
	}

	public void setSecond(User second) {
		this.second = second;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
