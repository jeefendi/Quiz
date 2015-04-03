package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Entity implementation class for Entity: Administrator
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Administrator extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	public Administrator() {
		super();
	}

	public Administrator(String login, String password, String name) {
		super(login, password, name);
		// TODO Auto-generated constructor stub
	}

}
