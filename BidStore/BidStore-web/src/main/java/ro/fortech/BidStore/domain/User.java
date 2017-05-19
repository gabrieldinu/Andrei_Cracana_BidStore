package ro.fortech.BidStore.domain;

public class User {

	int id;
	String name;
	String account;
	String email;
	int itemPlaced;
	int itemSold;
	int itemBought;
	boolean isAdmin;
	boolean isEnabled;
	
	public User() {
		
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getItemPlaced() {
		return itemPlaced;
	}

	public void setItemPlaced(int itemPlaced) {
		this.itemPlaced = itemPlaced;
	}

	public int getItemSold() {
		return itemSold;
	}

	public void setItemSold(int itemSold) {
		this.itemSold = itemSold;
	}

	public int getItemBought() {
		return itemBought;
	}

	public void setItemBought(int itemBought) {
		this.itemBought = itemBought;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	
}
