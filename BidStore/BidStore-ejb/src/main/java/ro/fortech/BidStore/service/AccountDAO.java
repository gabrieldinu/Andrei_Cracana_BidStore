package ro.fortech.BidStore.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import ro.fortech.BidStore.entities.Address;
import ro.fortech.BidStore.entities.Category;
import ro.fortech.BidStore.entities.Login;
import ro.fortech.BidStore.entities.PhoneNumber;
import ro.fortech.BidStore.model.AccountDTO;
import ro.fortech.BidStore.model.AddressDTO;
import ro.fortech.BidStore.model.PhoneNumberDTO;

/**
 * Session Bean implementation class AccountDAO
 */
@Stateless
public class AccountDAO {

	@Inject
	 private EntityManager em;
	
	public AccountDTO getUserInformation(String username) {
		AccountDTO account = new AccountDTO();
		
		TypedQuery<Login> query = em.createNamedQuery(Login.FIND_BY_USERNAME, Login.class);
		query.setParameter("username", username);
		try {
			Login entity = query.getSingleResult();
			
			account.setUser(entity.getUser());
			account.setPass(entity.getPass());		
			account.setRank(entity.getRank());
			account.setName(entity.getTableProfile().getName());
			account.setSurname(entity.getTableProfile().getSurname());
			account.setEmail(entity.getTableProfile().getEmail());
			
			List<AddressDTO> al = new ArrayList<AddressDTO>();
			for (Address address : entity.getAdresses()) {
				AddressDTO adto = new AddressDTO(address.getId(), address.getName(), address.getStreet(), 
						address.getZipcode(), address.getCity(), address.getUser_id());
				al.add(adto);
			}
			account.setAddresses(al);
			
			List<PhoneNumberDTO> pnl = new ArrayList<PhoneNumberDTO>();
			for (PhoneNumber phoneNumber : entity.getPhoneNumbers()) {
				PhoneNumberDTO pndto = new PhoneNumberDTO(phoneNumber.getId(), phoneNumber.getName(), phoneNumber.getNumber(), phoneNumber.getUser_id());
				pnl.add(pndto);
			}
			account.setPhoneNumbers(pnl);
			
			account.setUser_id(entity.getId());
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return account;
	}

	public boolean saveAddress(AddressDTO newAdrDTO) {
		
		Address entity = new Address();
		
		if (newAdrDTO.getId() != -1) entity = em.find(Address.class, newAdrDTO.getId());
		else entity.setUser_id(newAdrDTO.getUser_id());
		
		entity.setName(newAdrDTO.getName());
		entity.setStreet(newAdrDTO.getStreet());
		entity.setZipcode(newAdrDTO.getZipcode());
		entity.setCity(newAdrDTO.getCity());	
		
		try	{
			em.persist(entity);
			}
		catch (PersistenceException e) {
			e.printStackTrace();
			return false;
			}
		
		return true;
	}

	public boolean deleteAddress(int id) {
		
		Address entity = em.find(Address.class, id);
		
		try {
			em.remove(entity);
		}
		catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean saveNumber(PhoneNumberDTO newNumberDTO) {
		
		PhoneNumber entity = new PhoneNumber();
		
		if (newNumberDTO.getId() != -1) entity = em.find(PhoneNumber.class, newNumberDTO.getId());
		else entity.setUser_id(newNumberDTO.getUser_id());
		
		entity.setName(newNumberDTO.getName());
		entity.setNumber(newNumberDTO.getNumber());
		
		try	{
			em.persist(entity);
			}
		catch (PersistenceException e) {
			e.printStackTrace();
			return false;
			}
		
		return true;
	}

	public boolean deleteNumber(int id) {

		PhoneNumber entity = em.find(PhoneNumber.class, id);
		
		try {
			em.remove(entity);
		}
		catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean updateBasic(AccountDTO accDTO) {

		Login entity = new Login();
		
		entity = em.find(Login.class, accDTO.getUser_id());
		
		entity.getTableProfile().setName(accDTO.getName());
		entity.getTableProfile().setSurname(accDTO.getSurname());
		entity.getTableProfile().setEmail(accDTO.getEmail());
		
		if (accDTO.getPass() != null && !(accDTO.getPass().equals(""))) entity.setPass(accDTO.getPass());
		
		try	{
			em.persist(entity);
			}
		catch (PersistenceException e) {
			e.printStackTrace();
			return false;
			}
		
		return true;
	}

}
