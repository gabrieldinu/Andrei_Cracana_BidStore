package ro.fortech.BidStore.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ro.fortech.BidStore.domain.Account;
import ro.fortech.BidStore.domain.Address;
import ro.fortech.BidStore.domain.PhoneNumber;
import ro.fortech.BidStore.model.AccountDTO;
import ro.fortech.BidStore.model.AddressDTO;
import ro.fortech.BidStore.model.PhoneNumberDTO;

@ManagedBean(name="accountService")
@ApplicationScoped
public class AccountService {
	
	@Inject
	AccountDAO accountDAO;

	public Account getAccountInformation(String username) {
		Account acc = new Account();
		
		//retrieve account information from database
		AccountDTO accDTO = accountDAO.getUserInformation(username);
		
		acc.setUser(accDTO.getUser());
		acc.setPassword(accDTO.getPass());
		acc.setName(accDTO.getName());
		acc.setSurname(accDTO.getSurname());
		acc.setEmail(accDTO.getEmail());
		
		List<Address> adrList = new ArrayList<Address>(); 
		for (AddressDTO adrDTO : accDTO.getAddresses()) {
			Address adr = new Address(adrDTO.getName(), adrDTO.getStreet(), adrDTO.getZipcode(), adrDTO.getCity());
			adr.setId(adrDTO.getId());
			adr.setProfile_id(adrDTO.getUser_id());
			adrList.add(adr);
		}
		acc.setAddressList(adrList);
		
		List<PhoneNumber> pnList = new ArrayList<PhoneNumber>(); 
		for (PhoneNumberDTO pnDTO : accDTO.getPhoneNumbers()) {
			PhoneNumber pn = new PhoneNumber(pnDTO.getName(), pnDTO.getNumber());
			pn.setId(pnDTO.getId());
			pn.setProfile_id(pnDTO.getUser_id());
			pnList.add(pn);
		}
		acc.setPhoneNumberList(pnList);
		
		acc.setUser_id(accDTO.getUser_id());
		
		//mocking data for frontend testing
		// phone list
//		List<PhoneNumber> pnl = new ArrayList<PhoneNumber>();
//		PhoneNumber pn = new PhoneNumber("numar de telefon 1 (ex: mobil)", "0749109711");
//		pn.setId(1);
//		pn.setProfile_id(0);
//		pnl.add(pn);
//		pn = new PhoneNumber("numa de telefon 2 (ex: fix)", "0332/800815");
//		pnl.add(pn);
//		pn.setId(2);
//		pn.setProfile_id(0);
//		//address list
//		List<Address> al = new ArrayList<Address>();
//		Address ma = new Address("addresa 1 - home", "strada home", "cod postal home", "oras home");
//		ma.setId(1);
//		ma.setProfile_id(0);
//		al.add(ma);
//		ma = new Address("addresa 2 - billing", "strada billing", "cod postal billing", "oras billing");
//		ma.setId(2);
//		ma.setProfile_id(0);
//		al.add(ma);
//		ma = new Address("addresa 3 - shipping", "strada shipping", "cod postal shipping", "oras shipping");
//		ma.setId(3);
//		ma.setProfile_id(0);
//		al.add(ma);
//		
//		//basic info
//		acc.setUser(username);
//		acc.setName("Prenume");
//		acc.setSurname("Nume");
//		acc.setEmail("Adresa de email");
//		acc.setPassword("parola");
//		
//		acc.setPhoneNumberList(pnl);
//		acc.setAddressList(al);
		
		return acc;
	}

	public boolean saveAddress(Address newAddress) {
		
		AddressDTO newAdrDTO = new AddressDTO();
		newAdrDTO.setId(newAddress.getId());
		newAdrDTO.setName(newAddress.getName());
		newAdrDTO.setStreet(newAddress.getStreet());
		newAdrDTO.setZipcode(newAddress.getZipcode());
		newAdrDTO.setCity(newAddress.getCity());
		newAdrDTO.setUser_id(newAddress.getProfile_id());
		
		if(accountDAO.saveAddress(newAdrDTO)) return true;
		else return false;
	}

	public boolean deleteAddress(int id) {
		
		if(accountDAO.deleteAddress(id)) return true;
		else return false;
	}

	public boolean saveNumber(PhoneNumber newNumber) {

		PhoneNumberDTO newNumberDTO = new PhoneNumberDTO();
		newNumberDTO.setId(newNumber.getId());
		newNumberDTO.setName(newNumber.getName());
		newNumberDTO.setNumber(newNumber.getNumber());
		newNumberDTO.setUser_id(newNumber.getProfile_id());
		
		if(accountDAO.saveNumber(newNumberDTO)) return true;
		else return false;
	}

	public boolean deleteNumber(int id) {

		if(accountDAO.deleteNumber(id)) return true;
		else return false;
	}

	public boolean updateBasic(Account account) {
		
		AccountDTO accDTO = new AccountDTO();
		accDTO.setUser_id(account.getUser_id());
		accDTO.setName(account.getName());
		accDTO.setSurname(account.getSurname());
		accDTO.setEmail(account.getEmail());
		accDTO.setPass(account.getPassword());
		
		if (accountDAO.updateBasic(accDTO)) return true;
		else return false;
	}
}
