package mBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import services.interfaces.UserManagementServicesLocal;
import domain.Player;
import domain.User;

@ManagedBean(name = "lb")
@SessionScoped
public class LoginBean {
	private User user = new User();
	private String result;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@EJB
	private UserManagementServicesLocal userManagementServicesLocal;

	public String doLogin() {
		User userFounded = userManagementServicesLocal.login(user.getLogin(),
				user.getPassword());
		if (userFounded != null) {
			user = userFounded;
			if (userFounded instanceof Player)
				return "/pages/player";
			else
				return "/admin.xhtml";
		}
		result = "ERROR";
		return "Login incorrect";
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
