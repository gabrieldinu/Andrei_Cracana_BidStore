package ro.fortech.BidStore.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import ro.fortech.BidStore.domain.Category;
import ro.fortech.BidStore.model.CategoryDTO;
 
@ManagedBean(name = "categoryService")
@ApplicationScoped
public class CategoryService {
	
	@Inject
	private CategoryDAO categoryDAO;
     
    public TreeNode createCategories() {
    	
    	List<CategoryDTO> categoryTree = categoryDAO.getCategoryList();
    	
    	//mocking category list
    	
//    	TreeMap<Integer, Integer> categoryTree = new TreeMap<Integer, Integer>();
//    	categoryTree.put(1, 0);
//    	categoryTree.put(2, 0);
//    	categoryTree.put(3, 0);
//    	categoryTree.put(4, 1);
//    	categoryTree.put(5, 4);
//    	categoryTree.put(6, 4);
//    	categoryTree.put(7, 1);
//    	categoryTree.put(8, 2);
//    	categoryTree.put(9, 2);

    	TreeNode root = new DefaultTreeNode(new Category("Category", "0"), null);
    	
    	Map<Integer,TreeNode> categorylist = new HashMap<Integer,TreeNode>();
    	categorylist.put(0,root);
    	
    	for(CategoryDTO node : categoryTree) {
    	
    		TreeNode categoryNode = new DefaultTreeNode("last",new Category(node.getName(), node.getDescription(), Integer.toString(node.getId())), categorylist.get(node.getParent_id()));
    		categorylist.get(node.getParent_id()).setType("default");
    		categorylist.put(node.getId(),categoryNode);
    	} 
        
        return root;
    }
    
    public boolean addCategory(String name, String description, String id) {
    	
    	CategoryDTO newCategory = new CategoryDTO();
    	newCategory.setName(name);
    	newCategory.setDescription(description);
    	newCategory.setParent_id(Integer.parseInt(id));
    	
    	if (categoryDAO.insertCategory(newCategory)) return true;
    	else return false;  	
    	
    }
    
    public boolean removeCategory(String id) {
    	
    	CategoryDTO removeCategory = new CategoryDTO();
    	removeCategory.setId(Integer.parseInt(id));
    	
    	if (categoryDAO.deleteCategory(removeCategory)) return true;
    	else return false;  	
    	
    }

    public boolean updateCategory(String name, String description, String id) {
    	
    	CategoryDTO updateCategory = new CategoryDTO();
    	updateCategory.setName(name);
    	updateCategory.setDescription(description);
    	updateCategory.setId(Integer.parseInt(id));
    	
    	if (categoryDAO.updateCategory(updateCategory)) return true;
    	else return false;  	
    	
    }
}