package ro.fortech.BidStore.model;

import java.util.List;

public class AccountDTO {

	private String user;
	private String pass;
	private int rank;
	
	private String name;
	private String surname;
	private String email;
	
	private List<AddressDTO> addresses;
	private List<PhoneNumberDTO> phoneNumbers;
	private int user_id;
	
	public AccountDTO() {
		// TODO Auto-generated constructor stub
	}

	public AccountDTO(String user, String pass, int rank, String name, String surname, String email,
			List<AddressDTO> addresses, List<PhoneNumberDTO> phoneNumbers) {
		super();
		this.user = user;
		this.pass = pass;
		this.rank = rank;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.addresses = addresses;
		this.phoneNumbers = phoneNumbers;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
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

	public List<AddressDTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}

	public List<PhoneNumberDTO> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumberDTO> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
