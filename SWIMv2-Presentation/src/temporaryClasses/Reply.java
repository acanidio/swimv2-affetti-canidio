package temporaryClasses;

public class Reply {
	private User user;
	private boolean best;

	public Reply(User user, boolean best) {
		super();
		this.user = user;
		this.best = best;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isBest() {
		return best;
	}

	public void setBest(boolean best) {
		this.best = best;
	}

}
