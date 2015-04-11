package mBeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import services.interfaces.SessionMgtServicesLocal;
import domain.Session;

@ManagedBean(name = "rb")
@ViewScoped
public class ResultBean {
	@EJB
	private SessionMgtServicesLocal sessionMgtServicesLocal;
	private Session session;

	@ManagedProperty(value = "#{qb}")
	QuizBean quizBean;

	@PostConstruct
	public void Init() {
		System.out.println(this.getClass().getCanonicalName());
		session = quizBean.getSession();
		System.out.println(session);
		System.out.println(session.getScore());
	}

	public String Replay() {
		return "/quiz/Quiz.jsf";
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public QuizBean getQuizBean() {
		return quizBean;
	}

	public void setQuizBean(QuizBean quizBean) {
		this.quizBean = quizBean;
	}

}
