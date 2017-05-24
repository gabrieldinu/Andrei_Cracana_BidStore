package ro.fortech.BidStore.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PhoneNumber
 *
 */
@Entity
@Table(name="table_phonenumber")

public class PhoneNumber {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="phonenumber_id")
	private int id;
	
	@Column(name="phonenumber_name")
	private String name;
	
	@Column(name="phonenumber_number")
	private String number;
	
	@Column(name="phonenumber_user_id")
	private int user_id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="phonenumber_user_id", referencedColumnName="login_id", insertable=false, updatable=false)
	private Login login;

	public PhoneNumber() {
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
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
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
