package services.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.Answer;
import domain.Category;
import domain.Question;
import domain.Quiz;

/**
 * Session Bean implementation class AdministrationManagementServices
 */
@Stateless
@LocalBean
public class AdministrationManagementServices {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AdministrationManagementServices() {
		// TODO Auto-generated constructor stub
	}

	public Category AddCategory(String nom) {
		Category category = new Category();

		category.setNom(nom);
		try {
			entityManager.persist(category);

		} catch (Exception e) {
			category = null;
		}
		return category;

	}

	public List<Category> findAllCategory() {
		List<Category> listcategory;
		String jpql = "select c from Category c";
		Query query = entityManager.createQuery(jpql);
		listcategory = query.getResultList();
		return listcategory;
	}

	public Category findCategoryById(Integer id) {
		Category c = entityManager.find(Category.class, id);
		return c;
	}

	public void updateCategory(Integer id, String nom) {

		Category c = findCategoryById(id);
		c.setNom(nom);
		entityManager.merge(c);

	}

	public void updatecat(Category category) {
		entityManager.merge(category);
	}

	public void deleteCategory(Integer id) {
		Category c = findCategoryById(id);
		entityManager.remove(c);

	}

	public Quiz AddQuizToCategory(String titlequiz, String nomcategory) {
		Quiz quiz = new Quiz();
		quiz.setTitle(titlequiz);

		Category category = new Category();
		category.setNom(nomcategory);

		quiz.setCategory(category);
		entityManager.persist(quiz);
		return quiz;
	}

	public Question AddQuestionToQuiz(String title, String Q) {
		Question question = new Question();
		question.setText(Q);

		Quiz quiz = new Quiz();
		quiz.setTitle(title);

		question.setQuiz(quiz);

		entityManager.persist(question);
		return question;
	}

	public Answer AddAnswerToQuestion(String Q, String R1, String R2, String R3) {
		Answer answer = new Answer();
		answer.setText(R1);
		answer.setText(R2);
		answer.setText(R3);
		Question question = new Question();
		question.setText(Q);
		answer.setQuestion(question);
		entityManager.persist(answer);
		return answer;
	}

	public void AddQuiz(Quiz quiz) {
		entityManager.persist(quiz);
	}

}
