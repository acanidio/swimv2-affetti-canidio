package it.polimi.swim.entities;

import it.polimi.swim.entities.Person;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

@NamedQueries(value = {
		@NamedQuery(name = "User.getList",
				query = "SELECT u " +
						"FROM User u"
		)
})
/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@DiscriminatorValue(User.TYPE)
public class User extends Person implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	public static final String TYPE = "USER";
	
	private String avatar;
	
	private String city;
	
	private char gender;
	
	private Date birthday;
	
	private String phonenumber;
	
	@ManyToMany(targetEntity = Ability.class)
	@JoinTable(name = "PersonAbility",
			joinColumns = @JoinColumn(name = "IDPerson", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name = "IDAbility", referencedColumnName = "ID")
	)
	private Set<Ability> abilities;
	
	@OneToMany(targetEntity = HelpRequest.class, mappedBy = "sender")
	private Set<HelpRequest> helprequests; 
	
	@OneToMany(targetEntity = Reply.class, mappedBy = "sender")
	private Set<Reply> replies;
	
	@OneToMany(targetEntity = Feedback.class, mappedBy = "sender")
	private Set<Feedback> feedbacks;
	
	@OneToMany(targetEntity = Message.class, mappedBy = "sender")
	private Set<Message> sendedMessages;
	
	@OneToMany(targetEntity = Message.class, mappedBy = "receiver")
	private Set<Message> receivedMessages;
	
	@OneToMany(targetEntity = Friendship.class, mappedBy = "sender")
	private Set<Friendship> sendedRequests;
	
	@OneToMany(targetEntity = Friendship.class, mappedBy = "receiver")
	private Set<Friendship> receivedRequests;
	
	public User() {
		super();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Set<Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(Set<Ability> abilities) {
		this.abilities = abilities;
	}

	public Set<HelpRequest> getHelprequests() {
		return helprequests;
	}

	public void setHelprequests(Set<HelpRequest> helprequests) {
		this.helprequests = helprequests;
	}

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Set<Message> getSendedMessages() {
		return sendedMessages;
	}

	public void setSendedMessages(Set<Message> sendedMessages) {
		this.sendedMessages = sendedMessages;
	}

	public Set<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(Set<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public Set<Friendship> getSendedRequests() {
		return sendedRequests;
	}

	public void setSendedRequests(Set<Friendship> sendedRequests) {
		this.sendedRequests = sendedRequests;
	}

	public Set<Friendship> getReceivedRequests() {
		return receivedRequests;
	}

	public void setReceivedRequests(Set<Friendship> receivedRequests) {
		this.receivedRequests = receivedRequests;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Administrator)) {
			return false;
		}
		User user = (User) obj;
		if(this.getID() == user.getID()) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.getID();
	}
}
