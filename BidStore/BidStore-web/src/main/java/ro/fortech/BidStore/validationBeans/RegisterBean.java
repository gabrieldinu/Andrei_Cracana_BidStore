package ro.fortech.BidStore.validationBeans;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value="vvRegisterBean")
@SuppressWarnings("serial")
@RequestScoped
public class RegisterBean implements Serializable {
	
	@Inject
    private FacesContext facesContext;

//REGISTER fields start
	private String name;
	
	private String surname;
	
	private String email;
	
	private String user;
	
	private String password;
//REGISTER fields end
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}