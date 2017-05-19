package ro.fortech.BidStore.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import ro.fortech.BidStore.entities.Category;
import ro.fortech.BidStore.entities.Login;
import ro.fortech.BidStore.entities.Profile;
import ro.fortech.BidStore.model.UsersDTO;

@Stateless
public class UsersDAO {

	@Inject
	 private EntityManager em;
	
	public List<UsersDTO> getUsersList() {
		
		 List<UsersDTO> list = new ArrayList<UsersDTO>();
		 
		 TypedQuery<Profile> query = em.createNamedQuery(Profile.GET_USER_LIST, Profile.class);
		 List<Profile> entitylist = query.getResultList();
		 
		 for (Profile entity : entitylist) {
			 UsersDTO user = new UsersDTO();
			 
			 user.setId(entity.getProfileId());
			 user.setName(entity.getName()+" "+entity.getSurname());
			 user.setAccount(entity.getTableLogin().getUser());
			 user.setEmail(entity.getEmail());
			 //set real items after Item table is created AC91
			 user.setItemPlaced(0);
			 user.setItemSold(0);
			 user.setItemBought(0);
			 user.setAdmin(entity.getTableLogin().getAdmin());
			 user.setEnabled(entity.getTableLogin().getEnabled());
			 
			 list.add(user);
		 }
		 
		 return list;
	}

	public boolean updateUser(UsersDTO updateUser) {
		
		Login entity = em.find(Login.class, updateUser.getId());
		entity.setAdmin(updateUser.isAdmin());
		entity.setEnabled(updateUser.isEnabled());
		try {
		em.persist(entity);
		}
		catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		 }
		
		 return true;
	}
}
