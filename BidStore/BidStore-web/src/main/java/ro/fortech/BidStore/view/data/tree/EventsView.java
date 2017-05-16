package ro.fortech.BidStore.view.data.tree;

import java.awt.MenuItem;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.MenuActionEvent;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import ro.fortech.BidStore.domain.Category;
import ro.fortech.BidStore.service.CategoryService;
 
@ManagedBean(name="treeEventsView")
@ViewScoped
public class EventsView implements Serializable {
	
	private String newName;
	private String newDescription;
	private String parent_id;
	private boolean createNew;
     
    private TreeNode root;
     
    private TreeNode selectedNode;
     
    @ManagedProperty("#{categoryService}")
    private CategoryService service;
    
    private MenuModel breadcrumbModel;
    
    public MenuModel getBreadcrumbModel() {
		return breadcrumbModel;
	}

	public void setBreadcrumbModel(MenuModel breadcrumbModel) {
		this.breadcrumbModel = breadcrumbModel;
	}

	@PostConstruct
    public void init() {
        root = service.createCategories();
        breadcrumbModel = new DefaultMenuModel();
        DefaultMenuItem root = new DefaultMenuItem("root");
        root.setAjax(true);
        root.setCommand("#{treeEventsView.onNodeUnselect}");
        root.setUpdate(":categoryForm :searchForm:breadcrumb :categorySelected :categoryDescription");
        breadcrumbModel.addElement(root);
        parent_id = "0";
        createNew = false;
        newName = "New category";
        newDescription = "";
    }
 
    public TreeNode getRoot() {
        return root;
    }
 
    public TreeNode getSelectedNode() {
        return selectedNode;
    }
 
    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
 
    public void setService(CategoryService service) {
        this.service = service;
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
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        breadcrumbModel = new DefaultMenuModel();
        DefaultMenuItem root = new DefaultMenuItem("root");
        root.setAjax(true);
        root.setCommand("#{treeEventsView.onNodeUnselect}");
        root.setUpdate(":categoryForm :searchForm:breadcrumb :categorySelected :categoryDescription");
        breadcrumbModel.addElement(root);
        List<TreeNode> nh = new ArrayList<TreeNode>();
        nh=getNodeHierarchy(nh, event.getTreeNode());
        for (TreeNode node : nh) {
        	System.out.println("ADAUG NOD");
        	DefaultMenuItem dmi = new DefaultMenuItem(node.toString());
        	dmi.setAjax(true);
        	dmi.setCommand("#{treeEventsView.onNodeSelect}");
        	dmi.setUpdate(":categoryForm :searchForm:breadcrumb :categorySelected :categoryDescription");
        	breadcrumbModel.addElement(dmi);
        }
        
		parent_id = ((Category) event.getTreeNode().getData()).getctgrId();
		createNew = false;
    }
    
    public void onNodeSelect(MenuActionEvent event) {
    	breadcrumbModel = new DefaultMenuModel();
        DefaultMenuItem root = new DefaultMenuItem("root");
        root.setAjax(true);
        root.setCommand("#{treeEventsView.onNodeUnselect}");
        root.setUpdate(":categoryForm :searchForm:breadcrumb :categorySelected :categoryDescription");
        breadcrumbModel.addElement(root);
        List<TreeNode> nh = new ArrayList<TreeNode>();
        identifySelectedNodeFromBreadcrumb(event.getMenuItem().getValue().toString());
        nh=getNodeHierarchy(nh, selectedNode);
        for (TreeNode node : nh) {
        	DefaultMenuItem dmi = new DefaultMenuItem(node.toString());
        	dmi.setAjax(true);
        	dmi.setCommand("#{treeEventsView.onNodeSelect}");
        	dmi.setUpdate(":categoryForm:categoryTree :searchForm:breadcrumb :categorySelected :categoryDescription");
        	breadcrumbModel.addElement(dmi);
        }
    }
 
    private void identifySelectedNodeFromBreadcrumb(String value) {
    	do {
    		if(selectedNode.getData().toString().equals(value)) {
    			selectedNode.setSelected(true);
    			break;
    		}
    		else {
    			selectedNode.setSelected(false);
    			selectedNode = selectedNode.getParent();
    		}
    	}
    	while (!selectedNode.getParent().toString().equals("Category"));	
	}

	public void onNodeUnselect(NodeUnselectEvent event) {
    	System.out.println("UNSELECT NOD");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        breadcrumbModel = new DefaultMenuModel();
        DefaultMenuItem root = new DefaultMenuItem("root");
        root.setAjax(true);
        root.setCommand("#{treeEventsView.onNodeUnselect}");
        root.setUpdate(":categoryForm :searchForm:breadcrumb :categorySelected :categoryDescription");
        breadcrumbModel.addElement(root);	
        selectedNode.setSelected(false);
        parent_id = "0";
    }
    
	public void onNodeUnselect(MenuActionEvent event) {
    	System.out.println("UNSELECT NOD");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        breadcrumbModel = new DefaultMenuModel();
        DefaultMenuItem root = new DefaultMenuItem("root");
        root.setAjax(true);
        root.setCommand("#{treeEventsView.onNodeUnselect}");
        root.setUpdate(":categoryForm :searchForm:breadcrumb :categorySelected :categoryDescription");
        breadcrumbModel.addElement(root);	
        selectedNode.setSelected(false);
        parent_id = "0";
    }
	
    private List<TreeNode> getNodeHierarchy(List<TreeNode> nh, TreeNode endNode) { 	
    	nh.add(0,endNode);
    	if(!endNode.getParent().toString().equals("Category")) return getNodeHierarchy(nh, endNode.getParent());
    	else return nh; 	
    }
    
    public void updateCategory() {
    	if (!parent_id.equals("0")) if (service.updateCategory(((Category)selectedNode.getData()).getName(), ((Category)selectedNode.getData()).getdescription(), parent_id)) this.init();
    	else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Can't update category with selected name","Values="+selectedNode.toString()));
    	else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must select a category to update","Values="+newName+newDescription+parent_id));
    }
    
    public void addCategory() {
    	if (service.addCategory(newName, newDescription, parent_id)) this.init();
    	else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Can't insert category with the selected properties","Values="+newName+newDescription+parent_id));
    }
    
    public void removeCategory() {
    	if (!parent_id.equals("0")) if (service.removeCategory(parent_id)) this.init();
    	else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Can't remove category with selected criteria","Values="+newName+newDescription+parent_id));
    	else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must select a category to remove","Values="+newName+newDescription+parent_id));
    }

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewDescription() {
		return newDescription;
	}

	public void setNewDescription(String newDescription) {
		this.newDescription = newDescription;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public boolean isCreateNew() {
		return createNew;
	}

	public void setCreateNew(boolean createNew) {
		this.createNew = createNew;
	}
}
