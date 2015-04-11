package services.impl;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import services.interfaces.SessionMgtServicesLocal;
import services.interfaces.SessionMgtServicesRemote;
import domain.Player;
import domain.Quiz;
import domain.Session;

/**
 * Session Bean implementation class SessionMgtServices
 */
@Stateless
@LocalBean
public class SessionMgtServices implements SessionMgtServicesRemote,
		SessionMgtServicesLocal {
	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public SessionMgtServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Session AddSession(Player player, Quiz quiz, Date date) {
		Session session = new Session(player, quiz, date);
		return entityManager.merge(session);

	}

	@Override
	public Session UpdateSession(Session session) {
		try {
			session = entityManager.merge(session);
		} catch (Exception e) {
			System.out.println(e);
		}
		return session;
	}

	@Override
	public Session findSession(Session session) {
		Session sessionFound = null;
		try {
			sessionFound = entityManager.find(Session.class,
					session.getSessionId());
		} catch (Exception e) {
			System.out.println(e);
		}
		return sessionFound;
	}

}
