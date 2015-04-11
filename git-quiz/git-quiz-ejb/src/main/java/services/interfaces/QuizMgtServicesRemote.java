package services.interfaces;

import javax.ejb.Remote;

import domain.Quiz;

@Remote
public interface QuizMgtServicesRemote {
	Quiz getQuizById(int id);

	Quiz getQuizByTitle(String title);

}
