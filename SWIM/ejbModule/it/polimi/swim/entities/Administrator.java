package it.polimi.swim.entities;

import it.polimi.swim.entities.Person;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrator
 *
 */
@NamedQueries(value ={
		@NamedQuery(name = "Administrator.getByID",
				query = "SELECT a " +
						"FROM Administrator a " +
						"WHERE a.ID = :ID"
		)
})

@Entity
@DiscriminatorValue(Administrator.TYPE)
public class Administrator extends Person implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	public static final String TYPE = "ADMINISTRATOR";

	public Administrator() {
		super();
	}
   
}