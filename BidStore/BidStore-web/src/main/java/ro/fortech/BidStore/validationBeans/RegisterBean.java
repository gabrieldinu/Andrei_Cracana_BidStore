package ro.fortech.BidStore.validationBeans;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Named(value="vvRegisterBean")
@SuppressWarnings("serial")
@RequestScoped
public class RegisterBean implements Serializable {
	
	@SuppressWarnings("unused")
	@Inject
    private FacesContext facesContext;

//REGISTER fields start
	@Size(max=50, message="Name is too long! (max 50 char)")
	@Pattern(regexp="[a-zA-Z]+", message="Your name can only contain letters!")
	private String name;
	
	@Size(max=50, message="Surname is too long! (max 50 char)")
	@Pattern(regexp="[a-zA-Z]+", message="Your surname can only contain letters!")
	private String surname;
	
	@Size(max=100, message="Email is too long! (max 100)")
	@Pattern(regexp="[a-zA-Z0-9_.]+@[a-zA-Z0-9_]+.[a-zA-Z0-9.]+", message="Your email must have an email pattern!")
	private String email;
	
	@Size(max=50, message="Username is too long! (max 50 char)")
	@Pattern(regexp="[a-zA-Z0-9]+", message="Your username can only contain letters, digits, underscore and dot!")
	private String user;
	
	@Size(max=50, message="Pasword is too long! (max 50 char)")
	@Pattern(regexp="[a-zA-Z0-9]+", message="Your password can only contain letters and digits!")
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
