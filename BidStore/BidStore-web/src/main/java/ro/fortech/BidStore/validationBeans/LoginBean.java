package ro.fortech.BidStore.validationBeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Named(value="vvLoginBean")
@SuppressWarnings("serial")
@SessionScoped
public class LoginBean implements Serializable {
	
	@SuppressWarnings("unused")
	@Inject
    private FacesContext facesContext;

// LOGIN fields start
	@Size(max=50, message="Username is too long! (max 50 char)")
	@Pattern(regexp="[a-zA-Z0-9]+", message="Your username can only contain letters, digits, underscore and dot!")
	private String username;
	
	@Size(max=50, message="Pasword is too long! (max 50 char)")
	@Pattern(regexp="[a-zA-Z0-9]+", message="Your password can only contain letters and digits!")
	private String password;
	
	private boolean loggedIn;
	private boolean admin;
// LOGIN field end
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
