package ro.fortech.BidStore.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ro.fortech.BidStore.model.RegistrationModel;
import ro.fortech.BidStore.service.RegistrationService;
import ro.fortech.BidStore.validationBeans.LoginBean;
import ro.fortech.BidStore.validationBeans.RegisterBean;

@Named(value="userController")
@SuppressWarnings("serial")
@RequestScoped
public class UserController implements Serializable {

	@Inject
    private FacesContext facesContext;
	
	@Inject
	private RegisterBean registerBean;
	
	@Inject
	private LoginBean loginBean;
	
	@Inject
	private RegistrationModel registrationModel;
	
	@Inject
	private RegistrationService registrationService;
	
	public String registerUser() {
		registrationModel.setName(registerBean.getName());
		registrationModel.setSurname(registerBean.getSurname());
		registrationModel.setEmail(registerBean.getEmail());
		registrationModel.setUser(registerBean.getUser());
		registrationModel.setPassword(registerBean.getPassword());
		
		if (registrationService.register(registrationModel)) 
		{
			facesContext.addMessage(null, new FacesMessage("Register succeded! An email has been sent to you in order to confirm your account!"));
			return "login";
		}
		else {
			facesContext.addMessage(null, new FacesMessage("Register failed! Please try again!"));
			return "register";
		}
	}
	
	public String loginUser() {
		
		String loginResponse = registrationService.login(loginBean.getUsername(),loginBean.getPassword());
		
		if (loginResponse.equals("")) {
			return "index";
		}
		else {
			facesContext.addMessage(null, new FacesMessage(loginResponse));
			return "login";
		}

	}
}
