package services.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import services.interfaces.QuizMgtServicesLocal;
import services.interfaces.QuizMgtServicesRemote;
import domain.Quiz;

/**
 * Session Bean implementation class QuizMgtBean
 */
@Stateless
@LocalBean
public class QuizMgtServices implements QuizMgtServicesRemote, QuizMgtServicesLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public QuizMgtServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Quiz getQuizById(int id) {
		Quiz quiz = null;
		try {
			quiz = entityManager.find(Quiz.class, id);
		} catch (Exception e) {
			System.out.println(e);
		}
		return quiz;
	}

	@Override
	public Quiz getQuizByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
