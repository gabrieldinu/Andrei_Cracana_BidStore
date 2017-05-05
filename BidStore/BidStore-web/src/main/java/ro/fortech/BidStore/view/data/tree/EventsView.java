package ro.fortech.BidStore.view.data.tree;

import java.awt.MenuItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import ro.fortech.BidStore.service.CategoryService;
 
@ManagedBean(name="treeEventsView")
@ViewScoped
public class EventsView implements Serializable {
     
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
        breadcrumbModel.addElement(new DefaultMenuItem("root"));
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
        breadcrumbModel.addElement(new DefaultMenuItem("root"));
        List<TreeNode> nh = new ArrayList<TreeNode>();
        nh=getNodeHierarchy(nh, event.getTreeNode());
        for (TreeNode node : nh) {
        	System.out.println("ADAUG NOD");
        	breadcrumbModel.addElement(new DefaultMenuItem(node.toString()));
        }
    }
 
    public void onNodeUnselect(NodeUnselectEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        breadcrumbModel = new DefaultMenuModel();
        breadcrumbModel.addElement(new DefaultMenuItem("root"));
    }
    
    private List<TreeNode> getNodeHierarchy(List<TreeNode> nh, TreeNode endNode) { 	
    	nh.add(0,endNode);
    	if(!endNode.getParent().toString().equals("Category")) return getNodeHierarchy(nh, endNode.getParent());
    	else return nh; 	
    }
}
