package ro.fortech.BidStore.service;

import java.util.Random;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import ro.fortech.BidStore.model.Login;
import ro.fortech.BidStore.model.Profile;
import ro.fortech.BidStore.model.RegistrationModel;

@Stateless
public class RegistrationService {

	 @Inject
	 private Logger log;

	 @Inject
	 private EntityManager em;
	 
	 @Resource(mappedName = "java:jboss/mail/gmail")
	 private Session mailSession;
	 
	 private final static Random rG = new Random();
	 
	 
	 private String randomString(final int length) {
		    StringBuilder sb = new StringBuilder();
		    for(int i = 0; i < length; i++) {
		        char c = (char)(48+rG.nextInt(10)+rG.nextInt(2)*( //if nextInt(2) is 0 here, a digit will be generated ASCII 48-57
		        								17+rG.nextInt(17)+rG.nextInt(2)* //if nextInt(2) is 0 here, a big char will be generated ASCII 65-90
		        										32	//if all nextInt(2) are 1, a small char will be generated ASCII 97-122
		        										
		        								)
		        				);
		        sb.append(c);
		    }
		    return sb.toString();
	 }
	 
	 private String createEmailContent(Login loginEntity) {
		 String content = "Welcome, ";
		 content += loginEntity.getTableProfile().getName() + " " + loginEntity.getTableProfile().getSurname() + "!\n";
		 content += "Your account is: " + loginEntity.getUser() +"\n";
		 content += "In order to activate it and verify your email address, please click the following link:\n";
		 content += "link \n";
		 content += "Best regards!\n";
		 content += "BidStore Team";
		 return content;
	 }
	 
	 private void sendEmail(Login loginEntity) {
		 	String to = loginEntity.getTableProfile().getEmail();
		 	String from = "andrei91c@gmail.com";
//		 	String host = "localhost";
//		 	Properties properties = System.getProperties();
//		 	properties.setProperty("mail.smtp.host", host);
//		 	//implement email sending, misssing Mail API, can't create Session
//		 	Session session = Session.getDefaultInstance(properties);
		 	
		 	try {
		 		MimeMessage message = new MimeMessage(mailSession);
		 		message.setFrom(new InternetAddress(from));
		 		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		 		message.setSubject("Email Confirmation for BidStore");
		 		message.setText(createEmailContent(loginEntity));
		 		Transport.send(message);
		 		System.out.println("Message succesfully sent!");
		 	} catch (MessagingException mex) {
		 		log.info("Sending message failed!");
		 		mex.printStackTrace();
		 	}
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
    	
    	sendEmail(loginEntity);
    	
    	return true;
	 }
	 
	 public String login(String username, String password) {
		 TypedQuery<Login> verifyQuery = em.createNamedQuery(Login.FIND_BY_USERNAME, Login.class);
		 verifyQuery.setParameter("username", username);
		 
		 try 
		 {
			 Login loginToVerify = verifyQuery.getSingleResult();
			 if (!loginToVerify.getChecked()) return "Your email has not been verified!";
			 if (!loginToVerify.getPass().equals(password)) return "Your password is incorect!";
			 return "";
		 }
		 catch (NoResultException e) {
			 e.printStackTrace();
			 return "Username not found!";
		 }
	 }
	 
}
