package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Player
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Player extends User implements Serializable {

	private List<Session> sessions;

	private static final long serialVersionUID = 1L;

	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Player(String login, String password, String name) {
		super(login, password, name);
		// TODO Auto-generated constructor stub
	}

	@OneToMany(mappedBy = "player", cascade = { CascadeType.PERSIST })
	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
		for (Session s : sessions)
			s.setPlayer(this);
	}

}