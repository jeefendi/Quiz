package services.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import services.interfaces.QuestionMgtServicesLocal;
import services.interfaces.QuestionMgtServicesRemote;
import domain.Question;

/**
 * Session Bean implementation class QuestionMgtServices
 */
@Stateless
@LocalBean
public class QuestionMgtServices implements QuestionMgtServicesRemote,
		QuestionMgtServicesLocal {
	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public QuestionMgtServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Question getQuestionById(int id) {
		Question question = null;
		try {
			question = entityManager.find(Question.class, id);
		} catch (Exception e) {
			System.out.println(e);
		}
		return question;
	}

}
