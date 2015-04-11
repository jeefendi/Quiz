package services.interfaces;

import javax.ejb.Remote;

import domain.User;

@Remote
public interface UserManagementServicesRemote {
	User findById(int id);

}
