package services.interfaces;

import javax.ejb.Local;

import domain.Quiz;

@Local
public interface QuizMgtServicesLocal {
	Quiz getQuizById(int id);

	Quiz getQuizByTitle(String title);
	
	

}
