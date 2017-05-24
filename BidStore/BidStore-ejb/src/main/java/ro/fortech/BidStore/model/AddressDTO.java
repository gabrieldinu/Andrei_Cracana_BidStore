package ro.fortech.BidStore.model;

public class AddressDTO {

	private int id;
	private String name;
	private String street;
	private String zipcode;
	private String city;
	private int user_id;
	
	public AddressDTO() {
		// TODO Auto-generated constructor stub
	}

	public AddressDTO(int id, String name, String street, String zipcode, String city, int user_id) {
		super();
		this.id = id;
		this.name = name;
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
