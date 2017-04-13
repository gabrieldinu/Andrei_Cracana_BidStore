package ro.fortech.BidStore.validationBeans;

import java.io.Serializable;
import java.util.Random;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ro.fortech.BidStore.model.Login;
import ro.fortech.BidStore.model.Profile;
import ro.fortech.BidStore.service.Registration;

@Named(value="vvRegisterBean")
@SuppressWarnings("serial")
public class RegisterBean implements Serializable {
	
    @Inject
    private FacesContext facesContext;

    @Inject
    private Registration registration;

    @Inject
    private Profile profile;
    @Inject
    private Login login;
    
    @PostConstruct
    public void initNewMember() {
        profile = new Profile();
        login = new Login();
        login.setUser(user);
        login.setPass(password);
        login.setChecked(false);
        login.setCode("randomrandomrandomra");
        profile.setName(name);
        profile.setSurname(surname);
        profile.setEmail(email);
        profile.setTableLogin(login);
    }
    
	public String register() {
		registration.register(profile);
		return "login";
	}

	private String name;
	
	private String surname;
	
	private String email;
	
	private String user;
	
	private String password;

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
