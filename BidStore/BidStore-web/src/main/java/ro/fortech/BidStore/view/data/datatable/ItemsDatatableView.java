package ro.fortech.BidStore.view.data.datatable;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;

import ro.fortech.BidStore.domain.Category;
import ro.fortech.BidStore.domain.Item;
import ro.fortech.BidStore.service.CategoryService;
import ro.fortech.BidStore.service.ItemService;
import ro.fortech.BidStore.validationBeans.LoginBean;

@ManagedBean(name="itemsDatatable")
@ViewScoped
public class ItemsDatatableView implements Serializable {

	private List<Item> items;
	private Item selectedItem;
	private Item newItem;
	@Inject
	LoginBean loginBean;
	
	private boolean toSell = true;
	
	@ManagedProperty("#{itemService}")
	private ItemService service;
	
	//for category selection
	private TreeNode root;
    private TreeNode selectedNode;
    @ManagedProperty("#{categoryService}")
    private CategoryService serviceTree;
    
    public TreeNode getRoot() {
        return root;
    }
 
    public TreeNode getSelectedNode() {
        return selectedNode;
    }
 
    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
 
    public void setServiceTree(CategoryService serviceTree) {
        this.serviceTree = serviceTree;
    }
     
    public void onNodeExpand(NodeExpandEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    public void onNodeCollapse(NodeCollapseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    public void onNodeSelect(NodeSelectEvent event) {
    	String id = ((Category)event.getTreeNode().getData()).getctgrId();
    	System.out.println("Selected "+id);
    	newItem.setCategory(id);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected "+id, event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);

    }
    
    public void onNodeUnselect(NodeUnselectEvent event) {
    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
	@PostConstruct
	public void init() {
		this.items = service.createItems(100, this.toSell);
		root = serviceTree.createCategories();
		newItem = new Item();
		System.out.println(newItem);
	}
	
	public void switchSellBuy() {
		this.init();
	}
	
	public void defineNew() {
		newItem.setId(-1);
		newItem.setBestBidValue(0);
		newItem.setNumberOfBids(0);
		newItem.setWinner(loginBean.getUsername());
		newItem.setToSell(true);
		System.out.println(newItem);
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
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

	public boolean getToSell() {
		return toSell;
	}

	public void setToSell(boolean toSell) {
		this.toSell = toSell;
	}
	
	private boolean skip;
	
    public String onFlowProcess(FlowEvent event) {
    	System.out.println("onFlowProcess called - skip="+skip);
    	System.out.println(newItem);
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

	public Item getNewItem() {
		return newItem;
	}

	public void setNewItem(Item newItem) {
		this.newItem = newItem;
	}
}
