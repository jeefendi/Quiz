package services.interfaces;

import javax.ejb.Local;

import domain.Player;
import domain.User;

@Local
public interface UserManagementServicesLocal {
	User login(String login, String password);

	public User findUserByLogin(String login);

	public Boolean addPlayer(Player player);

	User findById(int id);

}
