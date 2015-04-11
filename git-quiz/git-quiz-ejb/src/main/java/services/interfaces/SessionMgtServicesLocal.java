package services.interfaces;

import java.util.Date;

import javax.ejb.Local;

import domain.Player;
import domain.Quiz;
import domain.Session;

@Local
public interface SessionMgtServicesLocal {
	Session AddSession(Player player, Quiz quiz, Date date);

	Session UpdateSession(Session session);

	Session findSession(Session session);

}
