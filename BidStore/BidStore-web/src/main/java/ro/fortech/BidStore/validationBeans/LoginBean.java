package ro.fortech.BidStore.validationBeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named(value="vvLoginBean")
@SuppressWarnings("serial")
@SessionScoped
public class LoginBean implements Serializable {
	
	@Inject
    private FacesContext facesContext;

// LOGIN fields start
	@NotNull(message = "Username can't be blank!")
	private String username;
	
	@NotNull(message = "Password can't be blank!")
	private String password;
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
	
	public String login() {
		return "index";
	}
	
}
