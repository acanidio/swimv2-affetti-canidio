package it.polimi.swim.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: HelpRequest
 * 
 */
@Entity
public class HelpRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;

	private String title;

	private String city;

	private String description;

	private Date date;

	private Time hour;

	@ManyToOne(targetEntity = User.class, optional = false)
	private User sender;

	@ManyToOne(targetEntity = Ability.class, optional = false)
	private Ability ability;
	
	@OneToMany(mappedBy = "request", fetch = FetchType.EAGER)
	private Set<Reply> replies;

	public HelpRequest() {
		super();
	}

	public int getID() {
		return ID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getHour() {
		return hour;
	}

	public void setHour(Time hour) {
		this.hour = hour;
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

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof HelpRequest)) {
			return false;
		}
		HelpRequest helpRequest = (HelpRequest) obj;
		if(ID == helpRequest.getID()) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return ID;
	}
}
