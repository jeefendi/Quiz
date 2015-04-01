package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Question
 *
 */
@Entity
public class Question implements Serializable {

	private Integer id;
	private String text;

	private List<Answer> answers;
	private Quiz quiz;

	private static final long serialVersionUID = 1L;

	public Question() {
	}

	public Question(String text) {
		this.text = text;
	}

	public Question(String text, List<Answer> answers, Quiz quiz) {
		this.text = text;
		this.setAnswers(answers);
		this.quiz = quiz;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
		for (Answer answer : answers)
			answer.setQuestion(this);
	}

	@ManyToOne
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

}
