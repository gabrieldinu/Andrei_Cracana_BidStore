package ro.fortech.BidStore.model;

import javax.persistence.*;


/**
 * The persistent class for the table_login database table.
 * 
 */
@Entity
@Table(name="table_login")
@NamedQuery(name=Login.FIND_BY_USERNAME, query="SELECT l FROM Login l WHERE l.user = :username")
public class Login {
	
	public static final String FIND_BY_USERNAME = "Login.findByUsername";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="login_id")
	private int Id;

	@Column(name="login_checked")
	private boolean checked;

	@Column(name="login_code")
	private String code;

	@Column(name="login_pass")
	private String pass;

	@Column(name="login_user")
	private String user;

	//bi-directional one-to-one association to Profile
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Profile tableProfile;

	public Login() {
	}

	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public boolean getChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Profile getTableProfile() {
		return this.tableProfile;
	}

	public void setTableProfile(Profile tableProfile) {
		this.tableProfile = tableProfile;
	}
}