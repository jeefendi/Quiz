package services.interfaces;

import javax.ejb.Local;

import domain.Answer;

@Local
public interface AnswerMgtServicesLocal {
	Answer UpdateAnswer(Answer answer);

}
