package ro.fortech.BidStore.entities;

import java.sql.Timestamp;

import javax.persistence.*;


/**
 * The persistent class for the table_login database table.
 * 
 */
@Entity
@Table(name="table_login")
@NamedQueries({
		@NamedQuery(name=Login.FIND_BY_USERNAME, query="SELECT l FROM Login l WHERE l.user = :username"),
		@NamedQuery(name=Login.FIND_BY_CODE, query="SELECT l FROM Login l WHERE l.code = :code"),
		@NamedQuery(name=Login.DELETE_NOT_ACTIVATED_ON_TIME, query="DELETE FROM Login l WHERE l.checked = false AND l.expiration <= :expiration")
})
public class Login {
	
	public static final String FIND_BY_USERNAME = "Login.findByUsername";
	public static final String FIND_BY_CODE = "Login.findByCode";
	public static final String DELETE_NOT_ACTIVATED_ON_TIME = "Login.deleteNotActivated";

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
	
	@Column(name="login_expiration")
	private Timestamp expiration;
	
	@Column(name="login_admin")
	private boolean admin;
	
	@Column(name="login_enabled")
	private boolean enabled;
	
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
	
	public Timestamp getExpiration() {
		return this.expiration;
	}

	public void setExpiration(Timestamp expiration) {
		this.expiration = expiration;
	}

	public Profile getTableProfile() {
		return this.tableProfile;
	}

	public void setTableProfile(Profile tableProfile) {
		this.tableProfile = tableProfile;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}