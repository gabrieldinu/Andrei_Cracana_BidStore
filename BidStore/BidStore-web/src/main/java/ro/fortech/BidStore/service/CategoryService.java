package ro.fortech.BidStore.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import ro.fortech.BidStore.domain.Category;
 
@ManagedBean(name = "categoryService")
@ApplicationScoped
public class CategoryService {
     
    public TreeNode createCategories() {
    	
    	//mocking category list
    	
    	TreeMap<Integer, Integer> categoryTree = new TreeMap<Integer, Integer>();
    	categoryTree.put(1, 0);
    	categoryTree.put(2, 0);
    	categoryTree.put(3, 0);
    	categoryTree.put(4, 1);
    	categoryTree.put(5, 4);
    	categoryTree.put(6, 4);
    	categoryTree.put(7, 1);
    	categoryTree.put(8, 2);
    	categoryTree.put(9, 2);

    	TreeNode root = new DefaultTreeNode(new Category("Category"), null);
    	
    	List<TreeNode> categorylist = new ArrayList<TreeNode>();
    	categorylist.add(root);
    	
    	for(Map.Entry node:categoryTree.entrySet()) {
    	
    		TreeNode categoryNode = new DefaultTreeNode("last",new Category( "Category"+node.getKey()), categorylist.get((int) node.getValue()));
    		categorylist.get((int) node.getValue()).setType("default");
    		categorylist.add(categoryNode);
    	} 
        
        return root;
    }
}