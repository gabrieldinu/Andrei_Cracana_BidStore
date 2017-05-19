package ro.fortech.BidStore.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import ro.fortech.BidStore.domain.Account;
import ro.fortech.BidStore.domain.Address;
import ro.fortech.BidStore.domain.PhoneNumber;

@ManagedBean(name="accountService")
@ApplicationScoped
public class AccountService {

	public Account getAccountInformation(String username) {
		Account acc = new Account();
		
		//retrieve account information from database
		
		//mocking data for frontend testing
		// phone list
		List<PhoneNumber> pnl = new ArrayList<PhoneNumber>();
		PhoneNumber pn = new PhoneNumber("numar de telefon 1 (ex: mobil)", "0749109711");
		pnl.add(pn);
		pn = new PhoneNumber("numa de telefon 2 (ex: fix)", "0332/800815");
		pnl.add(pn);
		//address list
		List<Address> al = new ArrayList<Address>();
		Address ma = new Address("addresa 1 - home", "strada home", "cod postal home", "oras home");
		al.add(ma);
		ma = new Address("addresa 2 - billing", "strada billing", "cod postal billing", "oras billing");
		al.add(ma);
		ma = new Address("addresa 3 - shipping", "strada shipping", "cod postal shipping", "oras shipping");
		al.add(ma);
		
		//basic info
		acc.setUser(username);
		acc.setName("Prenume");
		acc.setSurname("Nume");
		acc.setEmail("Adresa de email");
		acc.setPassword("parola");
		
		acc.setPhoneNumberList(pnl);
		acc.setAddressList(al);
		
		return acc;
	}
}
