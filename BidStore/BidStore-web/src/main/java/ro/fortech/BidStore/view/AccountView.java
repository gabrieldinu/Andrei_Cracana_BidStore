package ro.fortech.BidStore.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import ro.fortech.BidStore.domain.Account;
import ro.fortech.BidStore.domain.Address;
import ro.fortech.BidStore.domain.PhoneNumber;
import ro.fortech.BidStore.service.AccountService;
import ro.fortech.BidStore.validationBeans.LoginBean;

@ManagedBean(name="accountView")
@SessionScoped
public class AccountView implements Serializable {
	
	private Account account;
	
	private Address selectedAddress;
	private PhoneNumber selectedNumber;
	
	private Address newAddress;
	private PhoneNumber newNumber;
	
	@ManagedProperty("#{accountService}")
	private AccountService service;
	
	@Inject
	private LoginBean loginbean;
	
	@PostConstruct
	public void init() {
		this.account = service.getAccountInformation(loginbean.getUsername());
		newAddress = new Address();
		newNumber = new PhoneNumber();
	}

	public void setService(AccountService service) {
		this.service = service;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public void updateBasic() {
		if (service.updateBasic(account)) {
			this.init();
			System.out.println("Basic account info update");
		}
		else System.out.println("Error updating account basic info");
	}
	
	public void addAddress() {
		newAddress.setId(-1);
		newAddress.setName("New Name");
		newAddress.setStreet("New Street");
		newAddress.setZipcode("New Zipcode");
		newAddress.setCity("New City");
		newAddress.setProfile_id(account.getUser_id());
		System.out.println("New address added");
	}
	
	public void editAddress() {
		newAddress.setId(selectedAddress.getId());
		newAddress.setName(selectedAddress.getName());
		newAddress.setStreet(selectedAddress.getStreet());
		newAddress.setZipcode(selectedAddress.getZipcode());
		newAddress.setCity(selectedAddress.getCity());
		newAddress.setProfile_id(selectedAddress.getProfile_id());
		System.out.println("Address edit");
	}
	
	public void saveAddress() {
		if(service.saveAddress(newAddress)) {
			this.init();
			System.out.println("Address saved");
		}
		else System.out.println("Error saving address");
	}
	
	public void deleteAddress() {
		if(service.deleteAddress(selectedAddress.getId())) {
			this.init();
			System.out.println("Address deleted");
		}
		else System.out.println("Error deleting address");
	}
	
	public void addNumber() {
		newNumber.setId(-1);
		newNumber.setName("New Name");
		newNumber.setNumber("New Number");
		newNumber.setProfile_id(account.getUser_id());
		System.out.println("New number added");
	}
	
	public void editNumber() {
		newNumber.setId(selectedNumber.getId());
		newNumber.setName(selectedNumber.getName());
		newNumber.setNumber(selectedNumber.getNumber());
		newNumber.setProfile_id(account.getUser_id());
		System.out.println("Number edit");
	}
	
	public void saveNumber() {
		if(service.saveNumber(newNumber)) {
			this.init();
			System.out.println("Number saved");
		}
		else System.out.println("Error saving Number");
	}
	
	public void deleteNumber() {
		if(service.deleteNumber(selectedNumber.getId())) {
			this.init();
			System.out.println("Number deleted");
		}
		else System.out.println("Error deleting Number");
	}

	public Address getSelectedAddress() {
		return selectedAddress;
	}

	public void setSelectedAddress(Address selectedAddress) {
		this.selectedAddress = selectedAddress;
	}

	public PhoneNumber getSelectedNumber() {
		return selectedNumber;
	}

	public void setSelectedNumber(PhoneNumber selectedNumber) {
		this.selectedNumber = selectedNumber;
	}

	public Address getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(Address newAddress) {
		this.newAddress = newAddress;
	}

	public PhoneNumber getNewNumber() {
		return newNumber;
	}

	public void setNewNumber(PhoneNumber newNumber) {
		this.newNumber = newNumber;
	}
	 
}
