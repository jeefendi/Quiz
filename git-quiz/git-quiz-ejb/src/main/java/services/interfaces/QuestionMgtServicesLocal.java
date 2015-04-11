package services.interfaces;

import javax.ejb.Local;

import domain.Question;

@Local
public interface QuestionMgtServicesLocal {
	Question getQuestionById(int id);
}
