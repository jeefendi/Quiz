package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: PassQuiz
 *
 */
@Entity
public class Session implements Serializable {

	private Integer score;
	private SessionId sessionId;

	private Quiz quiz;
	private Player player;

	private List<Answer> reponses;

	private static final long serialVersionUID = 1L;

	public Session() {
	}

	public Session(Player player, Quiz quiz, Date date) {
		this.player = player;
		this.quiz = quiz;
		this.sessionId = new SessionId(player.getId(), quiz.getId(), date);
	}

	public Session(Integer score, SessionId sessionId) {
		this.score = score;
		this.sessionId = sessionId;
	}

	public Session(Integer score, SessionId sessionId, Quiz quiz,
			Player player, List<Answer> reponses) {
		this.score = score;
		this.sessionId = sessionId;
		this.quiz = quiz;
		this.player = player;
		this.setReponses(reponses);
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@EmbeddedId
	public SessionId getSessionId() {
		return sessionId;
	}

	public void setSessionId(SessionId sessionId) {
		this.sessionId = sessionId;
	}

	@ManyToOne
	@JoinColumn(name = "idQuiz", referencedColumnName = "id", updatable = false, insertable = false)
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	@ManyToOne
	@JoinColumn(name = "idPlayer", referencedColumnName = "id", updatable = false, insertable = false)
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@ManyToMany
	public List<Answer> getReponses() {
		return reponses;
	}

	public void setReponses(List<Answer> reponses) {
		this.reponses = reponses;
		if (reponses != null)
			for (Answer r : reponses) {
				if (r.getSessions() == null)
					r.setSessions(new ArrayList<Session>());
				r.getSessions().add(this);
			}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Session) {
			Session session = (Session) obj;
			if (session.getSessionId().equals(this.getSessionId()))
				return true;
		}
		return false;
	}

}
