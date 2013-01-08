package temporaryClasses;

public class User {

	private static int ID = 0;
	private int id;
	private String name;
	private String surname;
	private String email = "sampleEmail@swim.it";

	public User() {
		this.id = ID;
		ID++;
	}

	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
		this.id = (int) (Math.random() * 100);
	}

	public static int getID() {
		return ID;
	}

	public static void setID(int iD) {
		ID = iD;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
