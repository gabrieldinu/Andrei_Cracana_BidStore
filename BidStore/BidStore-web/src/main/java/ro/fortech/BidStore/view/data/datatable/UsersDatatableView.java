package ro.fortech.BidStore.view.data.datatable;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ro.fortech.BidStore.domain.User;
import ro.fortech.BidStore.service.UserService;

@ManagedBean(name="userDatatable")
@SessionScoped
public class UsersDatatableView implements Serializable {

	private List<User> users;
	private User selectedUser;

	@ManagedProperty("#{userService}")
	private UserService service;
	
	@PostConstruct
	public void init() {
		users = service.getUsers();
	}
	
	public void setService(UserService service) {
		this.service = service;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	public void updateUser() {
		if(service.updateUser(selectedUser)) this.init();
		else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Can't update user", null));
	}
	
}
