package ro.fortech.BidStore.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity
@Table(name="table_category")
@NamedQueries({
	@NamedQuery(name=Category.FIND_ALL, query="SELECT c FROM Category c WHERE c.parent_id IS NOT NULL ORDER BY c.id"),
	@NamedQuery(name=Category.FIND_BY_NAME, query="SELECT c FROM Category c WHERE c.name = :name")
})
public class Category {
	
	public static final String FIND_ALL = "Category.findAll";
	public static final String FIND_BY_NAME = "Category.findByName";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private int id;
	
	@Column(name="category_name")
	private String name;
	
	@Column(name="category_description")
	private String description;
	
	@Column(name="category_parent_id")
	private int parent_id;
	
	public Category() {
		
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}   
   
}
