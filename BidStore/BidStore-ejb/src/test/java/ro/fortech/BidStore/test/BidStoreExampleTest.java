package ro.fortech.BidStore.test;

import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ro.fortech.BidStore.model.RegistrationModelREST;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class BidStoreExampleTest {

	@Mock
	RegistrationModelREST registrationModelRESTmock;
	
	@Test
	public void test() {
		registrationModelRESTmock.setName("Gigi");
		verify(registrationModelRESTmock).setName("Gigi");
	}

}
