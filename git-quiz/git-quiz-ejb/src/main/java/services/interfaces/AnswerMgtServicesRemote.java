package services.interfaces;

import javax.ejb.Remote;

import domain.Answer;

@Remote
public interface AnswerMgtServicesRemote {
	Answer UpdateAnswer(Answer answer);
}
