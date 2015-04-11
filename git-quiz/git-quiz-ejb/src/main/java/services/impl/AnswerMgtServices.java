package services.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import services.interfaces.AnswerMgtServicesLocal;
import services.interfaces.AnswerMgtServicesRemote;
import domain.Answer;

/**
 * Session Bean implementation class AnswerMgtServices
 */
@Stateless
@LocalBean
public class AnswerMgtServices implements AnswerMgtServicesRemote,
		AnswerMgtServicesLocal {
	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AnswerMgtServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Answer UpdateAnswer(Answer answer) {
		return entityManager.merge(answer);
	}

}
