package it.polimi.swim.entities;

import it.polimi.swim.entities.Person;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrator
 *
 */
@NamedQueries(value ={

})

@Entity
@DiscriminatorValue(Administrator.TYPE)
public class Administrator extends Person implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	public static final String TYPE = "ADMINISTRATOR";

	public Administrator() {
		super();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Administrator)) {
			return false;
		}
		Administrator administrator = (Administrator) obj;
		if(this.getID() == administrator.getID()) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.getID();
	}
}
