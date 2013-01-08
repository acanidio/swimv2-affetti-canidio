package it.polimi.swim.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Person
 *
 */
@NamedQueries(value ={
		@NamedQuery(name = "Person.getByID",
				query = "SELECT p " +
						"FROM Person p " +
						"WHERE p.ID = :ID"
		),
		@NamedQuery(name = "Person.getByEmail",
				query = "SELECT p " +
						"FROM Person p " +
						"WHERE p.email = :email"
		)
})

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
public class Person implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int ID;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String password;

	public Person() {
		super();
	}
	
	public int getID() {
		return ID;
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

	public String getUsername() {
		return email;
	}

	public void setUsername(String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
