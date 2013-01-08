package temporaryClasses;

public class Message {

	private String text;
	private User fromU;
	private User toU;

	public Message(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
