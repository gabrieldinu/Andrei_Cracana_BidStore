package ro.fortech.BidStore.service;

import java.sql.Timestamp;
import java.util.Random;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ro.fortech.BidStore.model.Login;
import ro.fortech.BidStore.model.Profile;
import ro.fortech.BidStore.model.RegistrationModelREST;

@Path("/registration")
public class RegistrationServiceREST {
	
	 @Inject
	 private Logger log;

	 @Inject
	 private EntityManager em;
	 
	 @Resource(mappedName = "java:jboss/mail/gmail")
	 private Session mailSession;
	 
	 private final static Random rG = new Random();
	 
	 private final static String rootAddress = "http://192.168.215.106:8080/BidStore-web";
	 
	 private final static long ONE_DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
	 
	 
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
		 content += loginEntity.getTableProfile().getName() + " " + loginEntity.getTableProfile().getSurname() + "!\n\n";
		 content += "Your account is: " + loginEntity.getUser() +"\n";
		 content += "In order to activate it and verify your email address, please click the following link:\n";
		 content += rootAddress + "/rest/registration/activate?code=" + loginEntity.getCode() +" \n\n";
		 content += "Best regards!\n";
		 content += "BidStore Team";
		 return content;
	 }
	 
	 private void sendEmail(Login loginEntity) throws MessagingException {
		 	String to = loginEntity.getTableProfile().getEmail();
		 	String from = "andrei91c@gmail.com";
		 	
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
	 
	 @GET
	 @Path("/activate")
	 @Transactional
	 public String activate(@QueryParam(value="code") String code) {
		 
		 TypedQuery<Login> activateQuery = em.createNamedQuery(Login.FIND_BY_CODE, Login.class);
		 activateQuery.setParameter("code", code);
		 
		 try 
		 {
			 Login loginToActivate = activateQuery.getSingleResult();
			 if (loginToActivate.getChecked()) return "Your email was already verified!";
			 else {
				 loginToActivate.setChecked(true);
				 em.persist(loginToActivate);
			 }
			 return "Your email has been succesfully verified and your account is now activated!";
		 }
		 catch (NoResultException e) {
			 e.printStackTrace();
			 return "Your activation code is invalid or not found!";
		 }
		 catch (PersistenceException e) {
			 e.printStackTrace();
			 return "There was a problem activating your account!";
		 }
	 }
	 
	 @POST
	 @Path("/register")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.TEXT_PLAIN)
	 @Transactional
	 public boolean register(RegistrationModelREST registrationModel) {
    	log.info("Registering new member with REST!");
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
    	loginEntity.setExpiration(new Timestamp(System.currentTimeMillis() + ONE_DAY_IN_MILLIS));
    	loginEntity.setTableProfile(profileEntity);
    	
    	//insert into table
    	try {
    		em.persist(loginEntity);
    		sendEmail(loginEntity);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
  
    	return true;
	 }
	 
	 @POST
	 @Path("/login")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 @Transactional
	 public String login(@FormParam("username")String username, @FormParam("password")String password) {
		 log.info("Login with REST! + username="+username+" + password="+password+" <<<");
		 TypedQuery<Login> verifyQuery = em.createNamedQuery(Login.FIND_BY_USERNAME, Login.class);
		 verifyQuery.setParameter("username", username);
		 
		 try 
		 {
			 Login loginToVerify = verifyQuery.getSingleResult();
			 if (!loginToVerify.getChecked()) return "Your email has not been verified! REST";
			 if (!loginToVerify.getPass().equals(password)) return "Your password is incorect! REST";
			 return "";
		 }
		 catch (NoResultException e) {
			 e.printStackTrace();
			 return "Username not found! REST";
		 }
	 }
	 
	 public void clearDB(Timestamp ts) {
		 log.info("Entering cleaner!");
		 Query cleanQuery = em.createNamedQuery(Login.DELETE_NOT_ACTIVATED_ON_TIME);
		 cleanQuery.setParameter("expiration", ts);
		 int delRec = cleanQuery.executeUpdate();
		 log.info(delRec + " records successfully deleted!");
		 log.info("Exiting cleaner!");
	 }
}
