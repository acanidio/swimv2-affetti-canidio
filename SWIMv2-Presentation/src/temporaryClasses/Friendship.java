package temporaryClasses;

public class Friendship {

	private boolean pending;
	private User fromU;
	private User toU;
	private int id;

	public Friendship() {
		this.id = (int) (Math.random() * 100);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

	public User getFromU() {
		return fromU;
	}

	public void setFromU(User fromU) {
		this.fromU = fromU;
	}

	public User getToU() {
		return toU;
	}

	public void setToU(User toU) {
		this.toU = toU;
	}

}
