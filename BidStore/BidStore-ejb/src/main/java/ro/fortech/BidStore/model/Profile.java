package ro.fortech.BidStore.model;

import javax.persistence.*;


/**
 * The persistent class for the table_profile database table.
 * 
 */
@Entity
@Table(name="table_profile")
public class Profile {

	@Id
	private int profileId;

	@Column(name="profile_email")
	private String email;

	@Column(name="profile_name")
	private String name;

	@Column(name="profile_surname")
	private String surname;

	//bi-directional one-to-one association to Login
	@MapsId
	@OneToOne(mappedBy="tableProfile")
	@JoinColumn(name="profile_id")
	private Login tableLogin;

	public Profile() {
	}

	public int getProfileId() {
		return this.profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Login getTableLogin() {
		return this.tableLogin;
	}

	public void setTableLogin(Login tableLogin) {
		this.tableLogin = tableLogin;
	}

}