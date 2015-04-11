package services.interfaces;

import java.util.Date;

import javax.ejb.Remote;

import domain.Player;
import domain.Quiz;
import domain.Session;

@Remote
public interface SessionMgtServicesRemote {
	Session AddSession(Player player, Quiz quiz, Date date);

	Session UpdateSession(Session session);

	Session findSession(Session session);
}
