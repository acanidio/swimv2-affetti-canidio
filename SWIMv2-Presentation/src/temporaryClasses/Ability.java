package temporaryClasses;

public class Ability {
	private String name;
	private boolean pending;
	private int id;

	public Ability(String name) {
		super();
		this.name = name;
		pending = false;
		id = (int) (Math.random()*100);
	}

	public Ability() {
		super();
		id = (int) (Math.random()*100);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
