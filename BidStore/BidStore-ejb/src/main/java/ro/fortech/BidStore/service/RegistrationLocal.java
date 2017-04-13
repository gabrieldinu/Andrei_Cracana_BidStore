package ro.fortech.BidStore.service;

import javax.ejb.Local;

import ro.fortech.BidStore.model.Profile;

@Local
public interface RegistrationLocal {

	public void register(Profile profile);
	
}
