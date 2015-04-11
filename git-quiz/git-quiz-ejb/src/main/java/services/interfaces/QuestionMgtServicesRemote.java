package services.interfaces;

import javax.ejb.Remote;

import domain.Question;

@Remote
public interface QuestionMgtServicesRemote {
	Question getQuestionById(int id);
}
