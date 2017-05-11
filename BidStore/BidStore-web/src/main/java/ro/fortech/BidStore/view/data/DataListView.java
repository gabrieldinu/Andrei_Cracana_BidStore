package ro.fortech.BidStore.view.data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import ro.fortech.BidStore.domain.Item;
import ro.fortech.BidStore.service.ItemService;
 
@ManagedBean
@SessionScoped
public class DataListView implements Serializable {
	
    private List<Item> items;
    
    private Item selectedItem;
     
    private List<Item> filteredItems;
     
    private Map<String,String> sortBy;
    String sortColumn;
    boolean sortAsc;
    String sortOrder;
    
    @ManagedProperty("#{itemService}")
    private ItemService service;
 
    @PostConstruct
    public void init() {
        items = service.createItems(50);
        sortBy = new HashMap<String,String>();
        for (Field field : Item.class.getDeclaredFields()) sortBy.put(field.getName(),field.getName());
        sortAsc = true;
        sortOrder = "ascending";
    }
     
    public List<Item> getItems() {
        return items;
    }
 
    public List<Item> getFilteredItems() {
        return filteredItems;
    }
 
    public Item getSelectedItem() {
        return selectedItem;
    }
 
    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }
 
    public void setFilteredItems(List<Item> filteredItems) {
        this.filteredItems = filteredItems;
    }
 
    public void setService(ItemService service) {
        this.service = service;
    }

	public Map<String, String> getSortBy() {
		return sortBy;
	}

	public void setSortBy(Map<String, String> sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public boolean isSortAsc() {
		return sortAsc;
	}

	public void setSortAsc(boolean sortAsc) {
		this.sortAsc = sortAsc;
	}
	public void changeSortOrder(){
		sortOrder = sortAsc ? "ascending" : "descending";
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
}