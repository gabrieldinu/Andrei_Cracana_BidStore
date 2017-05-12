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
import javax.faces.component.UICommand;
import javax.faces.event.ActionEvent;

import ro.fortech.BidStore.domain.Item;
import ro.fortech.BidStore.service.ItemService;
 
@ManagedBean
@SessionScoped
public class DataListView implements Serializable {
	
	
	//Data
    private List<Item> items;
    private int totalRows;
    private Item selectedItem;
    //Paging
    private int firstRow;
    private int rowsPerPage;
    private int totalPages;
    private int pageRange;
    private Integer[] pages;
    private int currentPage;
    
    //Sorting
    private String sortField;
    private boolean sortAscending;
    
    private Map<String,String> sortBy;
    String sortColumn;   
    String sortOrder;
    
    @ManagedProperty("#{itemService}")
    private ItemService service;
 
    @PostConstruct
    public void init() {
    	//get items
        items = service.createItems(50);
        
        //pagination
        rowsPerPage = 10;
        pageRange = 10;
        
        //sort
        //sortField = "id";
        sortAscending = true;
        
        //sort old
        sortBy = new HashMap<String,String>();
        for (Field field : Item.class.getDeclaredFields()) sortBy.put(field.getName(),field.getName());
        
        sortOrder = "ascending";
    }
    
    //Paging options
    public void pageFirst() {
    	page(0);
    }
    
    public void pageNext() {
    	page(firstRow + rowsPerPage);
    }
    
    public void pagePrevious() {
        page(firstRow - rowsPerPage);
    }

    public void pageLast() {
        page(totalRows - ((totalRows % rowsPerPage != 0) ? totalRows % rowsPerPage : rowsPerPage));
    }
    
    public void page(ActionEvent event) {
        page(((Integer) ((UICommand) event.getComponent()).getValue() - 1) * rowsPerPage);
    }

    private void page(int firstRow) {
        this.firstRow = firstRow;
        loadDataList(); // Load requested page.
    }
    
    //Sort actions
    public void sort(ActionEvent event) {
        String sortFieldAttribute = (String) event.getComponent().getAttributes().get("sortField");

        // If the same field is sorted, then reverse order, else sort the new field ascending.
        if (sortField.equals(sortFieldAttribute)) {
            sortAscending = !sortAscending;
        } else {
            sortField = sortFieldAttribute;
            sortAscending = true;
        }

        pageFirst(); // Go to first page and load requested page.
    }
    
    //Loader
    private void loadDataList() {

        // Load list and totalCount.
//        try {
//            dataList = dao.list(firstRow, rowsPerPage, sortField, sortAscending);
//            totalRows = dao.count();
//        } catch (DAOException e) {
//            throw new RuntimeException(e); // Handle it yourself.
//        }
    	

        // Set currentPage, totalPages and pages.
        currentPage = (totalRows / rowsPerPage) - ((totalRows - firstRow) / rowsPerPage) + 1;
        totalPages = (totalRows / rowsPerPage) + ((totalRows % rowsPerPage != 0) ? 1 : 0);
        int pagesLength = Math.min(pageRange, totalPages);
        pages = new Integer[pagesLength];

        // firstPage must be greater than 0 and lesser than totalPages-pageLength.
        int firstPage = Math.min(Math.max(0, currentPage - (pageRange / 2)), totalPages - pagesLength);

        // Create pages (page numbers for page links).
        for (int i = 0; i < pagesLength; i++) {
            pages[i] = ++firstPage;
        }
    }
    
    //Getters
    public List<Item> getItems() {
        return items;
    }
 
    public Item getSelectedItem() {
        return selectedItem;
    }
 
    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }
 
    public void setService(ItemService service) {
        this.service = service;
    }
    
    public int getTotalRows() {
        return totalRows;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public Integer[] getPages() {
        return pages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    // Setters ------------------------------------------------------------------------------------

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }
    
    ////////
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
		return sortAscending;
	}

	public void setSortAsc(boolean sortAsc) {
		this.sortAscending = sortAsc;
	}
	public void changeSortOrder(){
		sortOrder = sortAscending ? "ascending" : "descending";
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
}