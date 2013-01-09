package temporaryClasses;

public class Conversation {

	private User user;
	private String message;
	private int id;

	public Conversation() {
		id = (int) (Math.random() * 100);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
