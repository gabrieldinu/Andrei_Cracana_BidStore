package ro.fortech.BidStore.service;

import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ro.fortech.BidStore.model.Profile;

/**
 * Session Bean implementation class Registration
 */
@Stateless(mappedName = "Registration")
@LocalBean
public class Registration implements RegistrationRemote, RegistrationLocal {

	 @Inject
	 private Logger log;

	 @Inject
	 private EntityManager em;
    
    public void register(Profile profile) {
    	log.info("Registering new member!");
    	em.persist(profile);
    }
}
