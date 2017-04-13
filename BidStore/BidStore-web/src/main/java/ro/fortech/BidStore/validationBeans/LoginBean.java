package ro.fortech.BidStore.validationBeans;

import java.io.Serializable;

import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named(value="vvLoginBean")
@SuppressWarnings("serial")
public class LoginBean implements Serializable {

	@NotNull(message = "Username can't be blank!")
	private String username;
	
	@NotNull(message = "Password can't be blank!")
	private String password;

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
