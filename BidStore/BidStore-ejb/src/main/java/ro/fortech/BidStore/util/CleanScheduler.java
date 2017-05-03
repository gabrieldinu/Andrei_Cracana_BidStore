package ro.fortech.BidStore.util;

import java.sql.Timestamp;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import ro.fortech.BidStore.service.RegistrationServiceREST;

@Singleton
@Startup
public class CleanScheduler {

	@Inject
	private RegistrationServiceREST rsr;
	
	//every 30 minuyte
	@Schedule(hour="*", minute="*/30", persistent=false)
	private void cleanExpired() {
		rsr.clearDB(new Timestamp(System.currentTimeMillis()));
	}
	
}
