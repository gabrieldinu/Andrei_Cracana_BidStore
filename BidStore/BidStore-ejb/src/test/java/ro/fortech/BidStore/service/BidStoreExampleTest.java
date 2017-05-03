package ro.fortech.BidStore.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.mockito.Matchers;
import org.mockito.junit.MockitoJUnitRunner;

import ro.fortech.BidStore.model.Login;
import ro.fortech.BidStore.model.Profile;
import ro.fortech.BidStore.model.RegistrationModel;
import ro.fortech.BidStore.model.RegistrationModelREST;
import ro.fortech.BidStore.service.RegistrationService;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class BidStoreExampleTest {

	final static String TEST_USERNAME = "AC91";
	final static String TEST_PASSWORD = "chicken";
	
	final static String TEST_NAME = "Andrei";
	final static String TEST_SURNAME = "Cracana";
	final static String TEST_EMAIL = "sest_ak47@yahoo.com";
	
	final static String LOGIN_SUCCESFUL = "";
	final static String LOGIN_FAILED_USER_NOT_FOUND ="Username not found!";
	
	static RegistrationService registrationService = mock(RegistrationService.class);
	static EntityManager em = mock(EntityManager.class);
	@SuppressWarnings("unchecked")
	static TypedQuery<Login> mockQuery = (TypedQuery<Login>) mock(TypedQuery.class);
	static Login mockLogEnt = mock(Login.class);
	
	Profile pe = new Profile();
	Login le = new Login();
	final static int RANDOM_LENGTH = 20;
	final static String RANDOM20 = registrationService.randomString(20);
	
	@BeforeClass
	public static void initServiceProperties() {
		
		
		mockLogEnt.setChecked(false);
		mockLogEnt.setUser(TEST_USERNAME);
		mockLogEnt.setPass(TEST_PASSWORD);
		
		
		when(mockQuery.getSingleResult()).thenReturn(mockLogEnt);
		when(mockQuery.setParameter(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockQuery);
		
		when(em.createNamedQuery(Login.FIND_BY_USERNAME, Login.class)).thenReturn(mockQuery);
		
		registrationService.setEntityManager(em);
		
	}
	
	@Test
	public void testRandomString() {
		assertTrue("Random String is not String", registrationService.randomString(RANDOM_LENGTH) instanceof String);
		assertEquals("Length wrong", RANDOM_LENGTH, registrationService.randomString(RANDOM_LENGTH).length());
	}
	
	@Test
	public void createAndUpdateReigstrationModel() {
		RegistrationModel rm = new RegistrationModel();
		rm.setName(TEST_NAME);
		rm.setSurname(TEST_SURNAME);
		rm.setEmail(TEST_EMAIL);
		rm.setUser(TEST_USERNAME);
		rm.setPassword(TEST_PASSWORD);
		
		assertFalse("NULL MODEL", rm == null);
		assertTrue("WRONG MODEL", rm instanceof RegistrationModel);
		assertEquals("Wrong name", TEST_NAME, rm.getName());
		assertEquals("Wrong surname", TEST_SURNAME, rm.getSurname());
		assertEquals("Wrong email", TEST_EMAIL, rm.getEmail());
		assertEquals("Wrong username", TEST_USERNAME, rm.getUser());
		assertEquals("Wrong password", TEST_PASSWORD, rm.getPassword());
		
	}
		
	@Test
	public void createAndUpdateReigstrationModelREST() {
		RegistrationModelREST rmr = new RegistrationModelREST();
		rmr.setName(TEST_NAME);
		rmr.setSurname(TEST_SURNAME);
		rmr.setEmail(TEST_EMAIL);
		rmr.setUser(TEST_USERNAME);
		rmr.setPassword(TEST_PASSWORD);
		
		assertFalse("NULL MODEL", rmr == null);
		assertTrue("WRONG MODEL", rmr instanceof RegistrationModelREST);
		assertEquals("Wrong name", TEST_NAME, rmr.getName());
		assertEquals("Wrong surname", TEST_SURNAME, rmr.getSurname());
		assertEquals("Wrong email", TEST_EMAIL, rmr.getEmail());
		assertEquals("Wrong username", TEST_USERNAME, rmr.getUser());
		assertEquals("Wrong password", TEST_PASSWORD, rmr.getPassword());
		
	}
	
	@Test
	public void createAndUpdateEntityLogin() {
		
		le.setUser(TEST_USERNAME);
		le.setPass(TEST_PASSWORD);
		le.setCode(RANDOM20);
		
		le.setTableProfile(pe);
		le.setId(0);
		
		assertFalse("NULL ENTITY", le == null);
		assertTrue("WRONG ENTITY", le instanceof Login);
		assertEquals("Wrong username", TEST_USERNAME, le.getUser());
		assertEquals("Wrong password", TEST_PASSWORD, le.getPass());
		assertEquals("Checked not initialized with false", false, le.getChecked());
		le.setChecked(true);
		assertEquals("Checked setter doesn't work", true, le.getChecked());
		assertEquals("Random code is not of length 20", RANDOM20, le.getCode());
		
		assertEquals("Wrong profile object", pe, le.getTableProfile());
		
		assertEquals("Id is not 0", 0, le.getId());
	}
	
	@Test
	public void createAndUpdateEntityProfile() {
		
		pe.setName(TEST_NAME);
		pe.setSurname(TEST_SURNAME);
		pe.setEmail(TEST_EMAIL);
		
		pe.setTableLogin(le);
		
		pe.setProfileId(0);
		
		assertFalse("NULL ENTITY", pe == null);
		assertTrue("WRONG ENTITY", pe instanceof Profile);
		assertEquals("Wrong name", TEST_NAME, pe.getName());
		assertEquals("Wrong surname", TEST_SURNAME, pe.getSurname());
		assertEquals("Wrong email", TEST_EMAIL, pe.getEmail());

		assertEquals("Wrong login object", le, pe.getTableLogin());
		
		assertEquals("Id is not 0", 0, pe.getProfileId());
	}
	
	@Test
	public void requestLoginWithEJB() {
		
		//when(registrationService.login(TEST_USERNAME, TEST_PASSWORD)).thenReturn("");
			
		assertEquals("Login Failed!","",registrationService.login(TEST_USERNAME, TEST_PASSWORD));
		
	}

	@Test
	public void requestLoginWithREST() {
		
		RegistrationServiceREST registrationServiceREST = mock(RegistrationServiceREST.class);
		
		when(registrationServiceREST.login(TEST_USERNAME, TEST_PASSWORD)).thenReturn("");
			
		assertEquals("Login Failed!","",registrationServiceREST.login(TEST_USERNAME, TEST_PASSWORD));
	}

}
