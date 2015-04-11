package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Response
 *
 */
@Entity
public class Answer implements Serializable {

	private Integer id;
	private Boolean correct;
	private String text;

	private Question question;
	private List<Session> sessions;

	private static final long serialVersionUID = 1L;

	public Answer() {
	}

	public Answer(Boolean correct, String text) {
		this.correct = correct;
		this.text = text;
	}

	public Answer(Boolean correct, String text, Question question,
			List<Session> sessions) {
		this.correct = correct;
		this.text = text;
		this.question = question;
		this.sessions = sessions;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getCorrect() {
		return this.correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@ManyToOne
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "reponses")
	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", correct=" + correct + ", text=" + text
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Answer) {
			Answer answer = (Answer) obj;
			if (this.getId() == answer.getId())
				return true;
		}
		return false;
	}

}
