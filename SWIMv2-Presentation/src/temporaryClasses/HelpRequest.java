package temporaryClasses;

import java.util.Calendar;
import java.util.List;

public class HelpRequest {
	private int id;
	private User user;
	private String subject;
	private Ability ability;
	private String date;
	private String hour;
	private List<Reply> replies;

	public HelpRequest() {
		super();
	}

	public HelpRequest(User user, String subject, Ability ability) {
		super();
		this.user = user;
		this.subject = subject;
		this.ability = ability;
		Calendar now = Calendar.getInstance();
		this.date = now.MONTH + "-" + now.DAY_OF_MONTH;
		this.hour = now.HOUR_OF_DAY + ":" + now.MINUTE;
		this.id = (int) (Math.random() * 100);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Ability getAbility() {
		return ability;
	}

	public void setAbility(Ability ability) {
		this.ability = ability;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

}
