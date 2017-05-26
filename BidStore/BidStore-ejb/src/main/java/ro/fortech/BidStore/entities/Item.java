package ro.fortech.BidStore.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="table_item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
	private int id;
	
	@Column(name="item_name")
	private String name;
	
	@Column(name="item_category")
	private String category;
	
	@Column(name="item_description")
	private String description;
	
	@Column(name="item_photo")
	private String photo;
	
	@Column(name="item_initial_price")
	private int initialPrice;
	
	@Column(name="item_opening_date")
	private Date bidOpeningDate;
	
	@Column(name="item_closing_date")
	private Date bidClosingDate;
	
	@Column(name="item_abandoned")
	private boolean abandoned;
	
	@Column(name="item_winner")
	private int winner;
	
	@Column(name="item_owner")
	private int owner;
}
