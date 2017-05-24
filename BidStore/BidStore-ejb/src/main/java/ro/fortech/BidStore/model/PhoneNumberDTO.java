package ro.fortech.BidStore.model;

public class PhoneNumberDTO {
	
	private int id;
	private String name;
	private String number;
	private int user_id;
	
	public PhoneNumberDTO() {
		// TODO Auto-generated constructor stub
	}

	public PhoneNumberDTO(int id, String name, String number, int user_id) {
		this.id = id;
		this.name = name;
		this.number = number;
		this.user_id = user_id;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
