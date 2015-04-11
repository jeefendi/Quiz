package mBeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import services.impl.AdministrationManagementServices;
import domain.Answer;
import domain.Category;
import domain.Question;
import domain.Quiz;

@ManagedBean(name = "CB")
@SessionScoped

public class CategoryBean {
	
	private Integer ide;
	private String nome;

	private String rep1;
	private String rep2;
	private String rep3;
	

	public String getRep1() {
		return rep1;
	}

	public void setRep1(String rep1) {
		this.rep1 = rep1;
	}

	public String getRep2() {
		return rep2;
	}

	public void setRep2(String rep2) {
		this.rep2 = rep2;
	}

	public Integer getIdCat() {
		return IdCat;
	}

	public void setIdCat(Integer idCat) {
		IdCat = idCat;
	}

	public String getRep3() {
		return rep3;
	}

	public void setRep3(String rep3) {
		this.rep3 = rep3;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}



	private Integer IdCat;
	
	public Integer getIde() {
		return ide;
	}

	public void setIde(Integer ide) {
		this.ide = ide;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
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
	
	private Boolean checked;
	
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	private boolean disabled;
	
    
	private TreeNode root;
	
	Answer answer1=new Answer();
	Answer answer2=new Answer();
	Answer answer3=new Answer();
	
	

	
	
	public Answer getAnswer1() {
		return answer1;
	}

	public void setAnswer1(Answer answer1) {
		this.answer1 = answer1;
	}

	public Answer getAnswer2() {
		return answer2;
	}

	public void setAnswer2(Answer answer2) {
		this.answer2 = answer2;
	}

	public Answer getAnswer3() {
		return answer3;
	}

	public void setAnswer3(Answer answer3) {
		this.answer3 = answer3;
	}



	@EJB
	private AdministrationManagementServices administrationManagementServices;

	
	
	
	public CategoryBean() {
		administrationManagementServices = new AdministrationManagementServices();
		
	}
	
	public List<Category> getListCategory() {
        return administrationManagementServices.findAllCategory();
        
    }
	
	public List<SelectItem> getAllCatagories(){

		   List<SelectItem> items = new ArrayList<SelectItem>();
		   List<Category> categoryList = administrationManagementServices.findAllCategory();
		    for(Category category: categoryList){
		       items.add(new SelectItem(category.getId(), category.getNom()));
		   }
		   return items;
		}
	
	public String doAddTheme() {
		
		if(this.category.getId()!=null && this.category.getId()!=0)
		{
			this.updatecat();
		}
		else
		{
		administrationManagementServices.AddCategory(category.getNom());
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Category", "" + category.getNom() + " Added");
        FacesContext.getCurrentInstance().addMessage(null, msg);
		category.setNom("");
		
		}
		return "";
	}
	
	public String doRomveTheme(Category Ctg) {
	      
		
        administrationManagementServices.deleteCategory(Ctg.getId());
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Category" ," Removed");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return"/AdCategory.xhtml";

   }
	
	//load Category
	public String LoadCategory (Category cat){
		this.setIde(cat.getId());
		this.setNome(cat.getNom());
		return "/EdCategory.xhtml";
		
	}
	
	public void RecupCategoryId(Category categ){
		this.setIdCat(categ.getId());
	}
	
	public void doNewCategory(){
		this.category = new Category();
	}

    public void updatecat(){
    	administrationManagementServices.updatecat(category);
    	
    }
    
    public String doUpdateCategory(){
    	administrationManagementServices.updateCategory(ide, nome);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Theme " +nome+ " Changed","");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    	return"/AdCategory.xhtml";
    }
    

    
    

    public String doAddQuiz(){
//    	administrationManagementServices.AddQuizToCategory(quiz.getTitle(),category.getNom());
//    	administrationManagementServices.AddQuestionToQuiz(question.getText(), quiz.getTitle());
//    	administrationManagementServices.AddAnswerToQuestion(question.getText(), answer.getText(), answer.getText(), answer.getText());
    	
    	
    
//    	Answer answer1=new Answer(answer.getCorrect(), rep1);
//    	Answer answer2=new Answer(answer.getCorrect(), rep2);
//    	Answer answer3=new Answer(answer.getCorrect(), rep3);
    	List<Answer> answers = new ArrayList<>();
    	answers.add(answer1);
    	answers.add(answer2);
    	answers.add(answer3);
    	Question questionaa=new Question(question.getText());
    	questionaa.setAnswers(answers);
    	Quiz quizaa=new Quiz(quiz.getTitle());
    	List<Question> questions=new ArrayList<Question>();
    	questions.add(questionaa);
    	quizaa.setQuestions(questions);
    	administrationManagementServices.AddQuiz(quizaa);
    	
    	List<Quiz> quizs = new ArrayList<>();
    	quizs.add(quizaa);
    	
    	
    	
    	Category category=administrationManagementServices.findCategoryById(1);
    	category.setQuizs(quizs);
    	administrationManagementServices.updatecat(category);
    	
    	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Quiz " +quiz.getTitle()+ " Ajouté","");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        quiz.setTitle("");
        question.setText("");
        answer1=null;
        answer2=null;
        answer3=null;
        return "/admin.xhtml";
        
        
        
        
    	
    }

    public String ChoixReponse() {
    	
        return "ReponseCorrect.xhtml";
    }

    public String WorkflowQuiz() {
    	
        return "QuisView.xhtml";
    }
    
    
    public void treeQuiz() {
        root = new DefaultTreeNode("Quiz", null);
        TreeNode cat = new DefaultTreeNode(category.getNom(), root);
         
        TreeNode quizname1 = new DefaultTreeNode(quiz.getTitle(), cat);
        
         
        TreeNode quest = new DefaultTreeNode(question.getText(), quizname1);
        TreeNode rep1 = new DefaultTreeNode(answer.getText(), quest);

         
        rep1.getChildren().add(new DefaultTreeNode(null));
       
    }
 
    public TreeNode getRoot() {
        return root;
    }


	
}
