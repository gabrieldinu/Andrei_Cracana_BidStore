package ro.fortech.BidStore.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import ro.fortech.BidStore.domain.Account;
import ro.fortech.BidStore.service.AccountService;
import ro.fortech.BidStore.validationBeans.LoginBean;

@ManagedBean(name="accountView")
@RequestScoped
public class AccountView {
	
	private Account account;
	
	@ManagedProperty("#{accountService}")
	private AccountService service;
	
	@Inject
	private LoginBean loginbean;
	
	@PostConstruct
	public void init() {
		this.account = service.getAccountInformation(loginbean.getUsername());
	}

	public void setService(AccountService service) {
		this.service = service;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
