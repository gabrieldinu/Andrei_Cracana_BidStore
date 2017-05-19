package ro.fortech.BidStore.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ro.fortech.BidStore.domain.User;
import ro.fortech.BidStore.model.UsersDTO;

@ManagedBean(name="userService")
@ApplicationScoped
public class UserService {
	
	@Inject
	UsersDAO usersDAO;
	
	public List<User> getUsers() {
		
		List<User> userList = new ArrayList<User>();
		
		List<UsersDTO> dbUserList = usersDAO.getUsersList();
		
		for (UsersDTO rec : dbUserList) {
			User user = new User();
			user.setId(rec.getId());
			user.setName(rec.getName());
			user.setAccount(rec.getAccount());
			user.setEmail(rec.getEmail());
			user.setItemPlaced(rec.getItemPlaced());
			user.setItemSold(rec.getItemSold());
			user.setItemBought(rec.getItemBought());
			user.setAdmin(rec.isAdmin());
			user.setEnabled(rec.isEnabled());
			userList.add(user);
		}
		
//		for (int i=1; i<=50; i++) {
//			User user = new User();
//			user.setId(i);
//			user.setName("Name"+i);
//			user.setAccount("Account"+i);
//			user.setEmail("emil"+i+"@test.ro");
//			user.setItemPlaced(2*(51-i));
//			user.setItemSold(51-i);
//			user.setItemBought(51-i);
//			user.setAdmin((i%5==0)?true:false);
//			user.setEnabled((i%6==0)?false:true);
//			
//			userList.add(user);
//		}
		
		return userList;
	}

	public boolean updateUser(User selectedUser) {
		
		UsersDTO updateUser = new UsersDTO();
		updateUser.setId(selectedUser.getId());
		updateUser.setAdmin(selectedUser.isAdmin());
		updateUser.setEnabled(selectedUser.isEnabled());
		
		if(usersDAO.updateUser(updateUser)) return true;
		else return false;
		
	}
}
