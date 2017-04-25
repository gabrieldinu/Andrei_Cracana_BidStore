package ro.fortech.BidStore.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

//import ro.fortech.BidStore.model.RegistrationModel;
import ro.fortech.BidStore.rest.RegistrationModelREST;
//import ro.fortech.BidStore.service.RegistrationService;
import ro.fortech.BidStore.util.WebResources;
import ro.fortech.BidStore.validationBeans.LoginBean;
import ro.fortech.BidStore.validationBeans.RegisterBean;

@Named(value="userController")
@SuppressWarnings("serial")
@SessionScoped
public class UserController implements Serializable {

	@Inject
    private FacesContext facesContext;
	
	@Inject
	private RegisterBean registerBean;
	
	@Inject
	private LoginBean loginBean;
	
//	@Inject
//	private RegistrationModel registrationModel;
//	
//	@Inject
//	private RegistrationService registrationService;
	
	@Inject 
	RegistrationModelREST registrationModelREST;
	
	Client restClient = ClientBuilder.newClient();
	
	public String registerUserREST() {
		
		registrationModelREST.setName(registerBean.getName());
		registrationModelREST.setSurname(registerBean.getSurname());
		registrationModelREST.setEmail(registerBean.getEmail());
		registrationModelREST.setUser(registerBean.getUser());
		registrationModelREST.setPassword(registerBean.getPassword());

		WebTarget registrationServiceREST = restClient.target(WebResources.rootAddress + "/rest/registration/register");
		
		String registerResponse = registrationServiceREST.request(MediaType.TEXT_PLAIN).post(Entity.entity(registrationModelREST, MediaType.APPLICATION_XML),String.class);
		
		if (registerResponse.equalsIgnoreCase("true")) 
		{
			facesContext.addMessage(null, new FacesMessage("REST Register succeded! An email has been sent to you in order to confirm your account!"));
			return "login";
		}
		else {
			facesContext.addMessage(null, new FacesMessage("REST Register failed! Please try again!"));
			return "register";
		}
	}
	
	public String loginUserREST() {
		
		WebTarget registrationServiceREST = restClient.target(WebResources.rootAddress + "/rest/registration/login");
		
//		registrationServiceREST.queryParam("username", loginBean.getUsername());
//		registrationServiceREST.queryParam("password", loginBean.getPassword());
		
		Form loginForm = new Form();
		loginForm.param("username", loginBean.getUsername());
		loginForm.param("password", loginBean.getPassword());
		
		String loginResponse = registrationServiceREST.request(MediaType.TEXT_PLAIN).post(Entity.form(loginForm), String.class);
		
		if (loginResponse.equals("")) {
			System.out.println("SUNT LOGAT SAU NU?" + (loginBean.getLoggedIn()?"DA":"NU"));
			loginBean.setLoggedIn(true);
			System.out.println("SUNT LOGAT SAU NU?" + (loginBean.getLoggedIn()?"DA":"NU"));
			return "secured/index.xhtml?faces-redirect=true";
		}
		else {
			facesContext.addMessage(null, new FacesMessage(loginResponse));
			loginBean.setLoggedIn(false);
			return "login";
		}

	}
	
//	public String registerUser() {
//		registrationModel.setName(registerBean.getName());
//		registrationModel.setSurname(registerBean.getSurname());
//		registrationModel.setEmail(registerBean.getEmail());
//		registrationModel.setUser(registerBean.getUser());
//		registrationModel.setPassword(registerBean.getPassword());
//		
//		if (registrationService.register(registrationModel)) 
//		{
//			facesContext.addMessage(null, new FacesMessage("Register succeded! An email has been sent to you in order to confirm your account!"));
//			return "login";
//		}
//		else {
//			facesContext.addMessage(null, new FacesMessage("Register failed! Please try again!"));
//			return "register";
//		}
//	}
//	
//	public String loginUser() {
//		
//		String loginResponse = registrationService.login(loginBean.getUsername(),loginBean.getPassword());
//		
//		if (loginResponse.equals("")) {
//			loginBean.setLoggedIn(true);
//			return "secured/index.xhtml?faces-redirect=true";
//		}
//		else {
//			facesContext.addMessage(null, new FacesMessage(loginResponse));
//			loginBean.setLoggedIn(false);
//			return "login";
//		}
//
//	}
	
	public String logOutUser() {
		System.out.println("SUNT LOGAT SAU NU?" + (loginBean.getLoggedIn()?"DA":"NU"));
		loginBean.setLoggedIn(false);
		System.out.println("SUNT LOGAT SAU NU?" + (loginBean.getLoggedIn()?"DA":"NU"));
		return "../login.xhtml?faces-redirect=true";
	}

}
