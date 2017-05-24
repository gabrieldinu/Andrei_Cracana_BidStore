package ro.fortech.BidStore.domain;

public class Address {

	int id;
	String name;
	String street;
	String zipcode;
	String city;
	int profile_id;
	
	public Address() {
		
	}

	public Address(String name, String street, String zipcode, String city) {
		this.name = name;
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}
	
}
