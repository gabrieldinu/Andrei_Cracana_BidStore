package ro.fortech.BidStore.model;

public class CategoryDTO {
	
	private int id;
	private String name;
	private String description;
	private int parent_id;

	public CategoryDTO() {
		
	}
	
	public CategoryDTO(int id, String name, String description, int parent_id) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.parent_id = parent_id;
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

	public String getDescription() {
		return description;
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
