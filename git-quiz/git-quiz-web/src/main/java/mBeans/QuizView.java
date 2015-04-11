package mBeans;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import services.impl.AdministrationManagementServices;
import domain.Answer;
import domain.Category;
import domain.Question;
import domain.Quiz;

 
@ManagedBean(name = "QV")
@ViewScoped
public class QuizView {
     
  
	private TreeNode root;
	
	private Category category = new Category();
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Quiz quiz = new Quiz();
	
	
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	public Question question = new Question();

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public Answer answer = new Answer();
	

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	private Boolean correct;
	
	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
	
	@EJB
	private AdministrationManagementServices administrationManagementServices;
     
    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Quiz", null);
        TreeNode cat = new DefaultTreeNode(category.getNom(), root);
         
        TreeNode quizname1 = new DefaultTreeNode(quiz.getTitle(), cat);
        
         
        TreeNode quest = new DefaultTreeNode(question.getText(), quizname1);
        TreeNode rep1 = new DefaultTreeNode("#{CB.rep1}", quest);

         
        rep1.getChildren().add(new DefaultTreeNode(answer.getCorrect()));
       
    }
 
    public TreeNode getRoot() {
        return root;
    }
    
    public void doAddQuiz(){
    	
    	Question quest = new Question(question.getText());

		List<Answer> answers = new ArrayList<>();
		answers.add(new Answer(null, answer.getText()));
		answers.add(new Answer(null, answer.getText()));
		
		quest.setAnswers(answers);

    	Quiz quizz=new Quiz(quiz.getTitle());
    	List<Question> questions=new ArrayList<Question>();
    	questions.add(quest);
    	quizz.setQuestions(questions);
    	administrationManagementServices.AddQuiz(quizz);
    	
    	List<Quiz> quizs = new ArrayList<>();
    	quizs.add(quizz);
    	
    	Category category=administrationManagementServices.findCategoryById(1);
    	category.setQuizs(quizs);
    	administrationManagementServices.updatecat(category);
    	
    	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Quiz " +quiz.getTitle()+ " Ajouté","");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    	
    }

    public String ChoixReponse() {
    	doAddQuiz();
        return "ReponseCorrect.xhtml";
    }
    
    public String WorkflowQuiz() {
    	doAddQuiz();
        return "QuisView.xhtml";
    }
    
}