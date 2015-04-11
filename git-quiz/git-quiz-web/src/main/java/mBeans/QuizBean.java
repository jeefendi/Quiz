package mBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import services.interfaces.AnswerMgtServicesLocal;
import services.interfaces.QuizMgtServicesLocal;
import services.interfaces.SessionMgtServicesLocal;
import services.interfaces.UserManagementServicesLocal;
import domain.Answer;
import domain.Player;
import domain.Question;
import domain.Quiz;
import domain.Session;

@ManagedBean(name = "qb")
@SessionScoped
public class QuizBean {

	@EJB
	private QuizMgtServicesLocal quizMgtBeanLocal;
	@EJB
	private SessionMgtServicesLocal sessionMgtServicesLocal;
	@EJB
	private UserManagementServicesLocal userManagementServicesLocal;
	@EJB
	private AnswerMgtServicesLocal answerMgtServicesLocal;

	private Quiz quiz = new Quiz();
	private Player player = new Player();
	private Date date = new Date();
	private Session session;
	private Answer selectedAnswer;

	@PostConstruct
	public void Init() {

		if (session != null)
			quiz = session.getQuiz();
		else
			// quiz à récupérer depuis la page sélection de quiz
			quiz = quizMgtBeanLocal.getQuizById(1);

		player = (Player) userManagementServicesLocal.findById(2);
		session = sessionMgtServicesLocal.AddSession(player, quiz, date);
		session.setReponses(new ArrayList<Answer>());
	}

	public String SelectAnswer() {
		Question currentQuestion = selectedAnswer.getQuestion();
		List<Answer> possibleAnswers = currentQuestion.getAnswers();

		for (Answer a : possibleAnswers) {
			if (session.getReponses().contains(a))
				session.getReponses().remove(a);
			List<Session> sessions = a.getSessions();
			if (sessions != null)
				if (sessions.contains(session))
					sessions.remove(session);

		}
		session.getReponses().add(selectedAnswer);
		selectedAnswer.getSessions().add(session);

		if (quiz.getQuestions().indexOf(currentQuestion) < quiz.getQuestions()
				.size() - 1) {
			RequestContext
					.getCurrentInstance()
					.execute(
							"PF('dataList').getPaginator().setPage(PF('dataList').getPaginator().cfg.page+1)");
			System.out.println("Question "
					+ quiz.getQuestions().indexOf(currentQuestion) + " of "
					+ "Size = " + quiz.getQuestions().size());
			System.out.println("Move To Next");
		}

		else {
			System.out.println("end");
			Integer correctAnswers = 0;
			for (Answer a : session.getReponses())
				if (a.getCorrect())
					correctAnswers++;
			session.setScore(correctAnswers);
			session = sessionMgtServicesLocal.UpdateSession(session);
			answerMgtServicesLocal.UpdateAnswer(selectedAnswer);
			return "/quiz/QuizResult.jsf";
		}
		return null;

	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Answer getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(Answer selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public QuizBean() {
		System.out.println(quizMgtBeanLocal);
	}

}
