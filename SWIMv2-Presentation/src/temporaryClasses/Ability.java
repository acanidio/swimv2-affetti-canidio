package temporaryClasses;

public class Ability {
	private String name;
	private boolean pending;

	public Ability(String name) {
		super();
		this.name = name;
		pending = false;
	}

	public Ability() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

}
