package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Theme
 *
 */
@Entity
public class Category implements Serializable {

	private Integer id;
	private String nom;

	private List<Quiz> quizs;

	private static final long serialVersionUID = 1L;

	public Category() {
	}

	public Category(String nom) {
		this.nom = nom;
	}

	public Category(String nom, List<Quiz> quizs) {
		this.nom = nom;
		this.setQuizs(quizs);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
	public List<Quiz> getQuizs() {
		return quizs;
	}

	public void setQuizs(List<Quiz> quizs) {
		this.quizs = quizs;
		for (Quiz q : quizs)
			q.setCategory(this);
	}
}
