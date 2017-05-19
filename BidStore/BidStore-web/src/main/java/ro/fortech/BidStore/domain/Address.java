package ro.fortech.BidStore.domain;

public class Address {

	String name;
	String street;
	String zipcode;
	String city;
	
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
	
}
