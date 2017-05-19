package ro.fortech.BidStore.domain;

import java.util.List;
import java.util.Map;

public class Account {

	//login user
	String user;

	
	//profile info
	String name;
	String surname;
	String email;
	
	//phone list
	List<PhoneNumber> phoneNumberList;
	
	//addresses
	List<Address> addressList;
	
	//login password
	String password;
	
	public Account() {
		
	}

	public Account(String user, String name, String surname, String email, List<PhoneNumber> phoneNumberList,
			List<Address> addressList, String password) {
		this.user = user;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phoneNumberList = phoneNumberList;
		this.addressList = addressList;
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PhoneNumber> getPhoneNumberList() {
		return phoneNumberList;
	}

	public void setPhoneNumberList(List<PhoneNumber> phoneNumberList) {
		this.phoneNumberList = phoneNumberList;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
