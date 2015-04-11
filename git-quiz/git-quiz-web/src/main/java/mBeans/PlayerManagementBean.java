package mBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import services.interfaces.UserManagementServicesLocal;
import domain.Player;

@ManagedBean
@RequestScoped
public class PlayerManagementBean {
	private Player player = new Player();
	@EJB
	private UserManagementServicesLocal userManagementServicesLocal;

	public String doAddPlayer() {
		userManagementServicesLocal.addPlayer(player);
		return "/login";
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
