package ro.fortech.BidStore.domain;

import java.sql.Date;

public class Item {

	private int id;
	private String name;
	private String photo;
	private String description;
	private int bestBidValue;
	private Date bidClosingDate;
	private int numberOfBids;
	
	Item() {
		
	}
	
	public Item(int id, String name, String photo, String description, int bestBidValue, Date bidClosingDate,
			int numberOfBids) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.description = description;
		this.bestBidValue = bestBidValue;
		this.bidClosingDate = bidClosingDate;
		this.numberOfBids = numberOfBids;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getBestBidValue() {
		return bestBidValue;
	}
	public void setBestBidValue(int bestBidValue) {
		this.bestBidValue = bestBidValue;
	}
	public Date getBidClosingDate() {
		return bidClosingDate;
	}
	public void setBidClosingDate(Date bidClosingDate) {
		this.bidClosingDate = bidClosingDate;
	}
	public int getNumberOfBids() {
		return numberOfBids;
	}
	public void setNumberOfBids(int numberOfBids) {
		this.numberOfBids = numberOfBids;
	}
	
}
