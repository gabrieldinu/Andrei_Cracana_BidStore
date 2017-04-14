package ro.fortech.BidStore.service;

import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.junit.rules.Verifier;

import ro.fortech.BidStore.model.Login;
import ro.fortech.BidStore.model.Profile;
import ro.fortech.BidStore.model.RegistrationModel;

@Stateless
public class RegistrationService {

	 @Inject
	 private Logger log;

	 @Inject
	 private EntityManager em;
	 
	 
	 private String randomString(final int length) {
		    Random r = new Random(); // perhaps make it a class variable so you don't make a new one every time
		    StringBuilder sb = new StringBuilder();
		    for(int i = 0; i < length; i++) {
		        char c = (char)(r.nextInt((int)(Character.MAX_VALUE)));
		        sb.append(c);
		    }
		    return sb.toString();
	 }
	 
	 private void sendEmail(String email) {
		 	String to = email;
		 	String from = "BidStoreEmail";
		 	String host = "localhost";
		 	Properties properties = System.getProperties();
		 	properties.setProperty("mail.smtp.host", host);
		 	//implement email sending, misssing Mail API, can't create Session
	 }
    
	 public boolean register(RegistrationModel registrationModel) {
    	log.info("Registering new member!");
    	//create new login user
    	Login loginEntity = new Login();
    	
    	//create new profile
    	Profile profileEntity = new Profile();
    	profileEntity.setName(registrationModel.getName());
    	profileEntity.setSurname(registrationModel.getSurname());
    	profileEntity.setEmail(registrationModel.getEmail());
    	profileEntity.setTableLogin(loginEntity);
    	
    	//set login information
    	loginEntity.setUser(registrationModel.getUser());
    	loginEntity.setPass(registrationModel.getPassword());
    	loginEntity.setChecked(false);
    	loginEntity.setCode(randomString(20));
    	loginEntity.setTableProfile(profileEntity);
    	
    	//insert into table
    	try {
    		em.persist(loginEntity);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    	
    	sendEmail(profileEntity.getEmail());
    	
    	return true;
	 }
	 
	 public String login(String username, String password) {
		 TypedQuery<Login> verifyQuery = em.createNamedQuery(Login.FIND_BY_USERNAME, Login.class);
		 verifyQuery.setParameter("username", username);
		 
		 try 
		 {
			 Login loginToVerify = verifyQuery.getSingleResult();
			 if (!loginToVerify.getChecked()) return "Your email has no been verified!";
			 if (!loginToVerify.getPass().equals(password)) return "Your password is incorect!";
			 return "";
		 }
		 catch (NoResultException e) {
			 e.printStackTrace();
			 return "Username not found!";
		 }
	 }
	 
}
