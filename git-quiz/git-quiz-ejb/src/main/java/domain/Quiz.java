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
 * Entity implementation class for Entity: Quiz
 *
 */
@Entity
public class Quiz implements Serializable {

	private Integer id;
	private String title;
	private List<Question> questions;
	private Category category;

	private static final long serialVersionUID = 1L;

	public Quiz() {
	}

	public Quiz(String title) {
		this.title = title;
	}

	public Quiz(String title, List<Question> questions, Category category) {
		this.title = title;
		this.questions = questions;
		this.category = category;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST)
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
		for (Question q : questions)
			q.setQuiz(this);
	}

	@ManyToOne
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
