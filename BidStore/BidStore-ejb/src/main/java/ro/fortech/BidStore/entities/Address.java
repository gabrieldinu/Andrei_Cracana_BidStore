package ro.fortech.BidStore.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Address
 *
 */
@Entity
@Table(name="table_address")

public class Address {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private int id;
	
	@Column(name="address_name")
	private String name;
	
	@Column(name="address_street")
	private String street;
	
	@Column(name="address_zipcode")
	private String zipcode;
	
	@Column(name="address_city")
	private String city;
	
	@Column(name="address_user_id")
	private int user_id;

	@ManyToOne(optional=false)
	@JoinColumn(name="address_user_id", referencedColumnName="login_id", insertable=false, updatable=false)
	private Login login;
	
	public Address() {
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}   
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}   
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}   
	public int getUser_id() {
		return this.user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
   
}
