package ro.fortech.BidStore.service;

import javax.ejb.Remote;
import javax.enterprise.inject.Alternative;

import ro.fortech.BidStore.model.Profile;

@Remote
public interface RegistrationRemote {

	@Alternative
	public void register(Profile profile);
	
}
