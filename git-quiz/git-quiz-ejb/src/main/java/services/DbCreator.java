package services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.Answer;
import domain.Category;
import domain.Player;
import domain.Question;
import domain.Quiz;

/**
 * Session Bean implementation class DbCreator
 */
@Singleton
@Startup
public class DbCreator {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public DbCreator() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void initDb() {

		Question question1 = new Question("What is the capital of Tunisia ?");

		List<Answer> answersQ1 = new ArrayList<>();
		answersQ1.add(new Answer(true, "Tunis"));
		answersQ1.add(new Answer(false, "Sfax"));
		answersQ1.add(new Answer(false, "Sousse"));
		question1.setAnswers(answersQ1);

		Question question2 = new Question(
				"What is the northest capital in the world ?");

		List<Answer> answersQ2 = new ArrayList<>();
		answersQ2.add(new Answer(false, "Montreal"));
		answersQ2.add(new Answer(false, "Moscow"));
		answersQ2.add(new Answer(true, "Reykjavick"));
		question2.setAnswers(answersQ2);

		List<Question> questions1_2 = new ArrayList<>();
		questions1_2.add(question1);
		questions1_2.add(question2);

		Question question3 = new Question(
				"Which of these rivers is the longest ?");

		List<Answer> answersQ3 = new ArrayList<>();
		answersQ3.add(new Answer(false, "Nile"));
		answersQ3.add(new Answer(true, "Amazon"));
		answersQ3.add(new Answer(false, "Mejerda"));
		question3.setAnswers(answersQ3);

		List<Question> questions3 = new ArrayList<>();
		questions3.add(question3);

		Quiz quiz1 = new Quiz("Capitals of the world", questions1_2, null);

		Quiz quiz2 = new Quiz("Rivers", questions3, null);

		Category category1 = new Category("Geography");
		List<Quiz> quizs1_2 = new ArrayList<>();
		quizs1_2.add(quiz1);
		quizs1_2.add(quiz2);
		category1.setQuizs(quizs1_2);

		Category category2 = new Category("Sport");
		Category category3 = new Category("Science");

		entityManager.persist(category1);
		entityManager.persist(category2);
		entityManager.persist(category3);

		Player player1 = new Player("Anis", "anis", "anis");
		Player player2 = new Player("Bechir", "bechir", "bechir");
		Player player3 = new Player("Wassim", "wassim", "wassim");
		entityManager.persist(player1);
		entityManager.persist(player2);
		entityManager.persist(player3);
		// List<Answer> responses1 = new ArrayList<>();
		// responses1.add(answer1);
		// Session session = new Session(100, new SessionId(player1.getId(),
		// quiz.getId(), new Date()), quiz, player1, responses1);
		// entityManager.persist(session);

	}
}
