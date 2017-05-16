package ro.fortech.BidStore.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import ro.fortech.BidStore.entities.Category;
import ro.fortech.BidStore.model.CategoryDTO;

@Stateless
public class CategoryDAO {

	 @Inject
	 private EntityManager em;
	 
	 public List<CategoryDTO> getCategoryList() {
		 List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		 
		 TypedQuery<Category> query = em.createNamedQuery(Category.FIND_ALL, Category.class);
		 List<Category> entitylist = query.getResultList();
		 
		 for (Category entity : entitylist) 
			 list.add(new CategoryDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getParent_id()));
		 
		 return list;
	 }
	 
	 public boolean insertCategory(CategoryDTO categoryDTO) {
		 
		 Category entity = new Category();
		 
		 entity.setName(categoryDTO.getName());
		 entity.setDescription(categoryDTO.getDescription());
		 entity.setParent_id(categoryDTO.getParent_id());
		 
		 try {
		 em.persist(entity);
		 }
		 catch (PersistenceException e) {
			 e.printStackTrace();
			 return false;
		 }
		 
		 return true;
	 }
	 
	 public boolean deleteCategory(CategoryDTO categoryDTO) {
		 
		 Category entity = em.find(Category.class, categoryDTO.getId());
		 
		 try {
		 em.remove(entity);
		 }
		 catch (PersistenceException e) {
			 e.printStackTrace();
			 return false;
		 }
		 
		 return true;
	 }
	 
	 public boolean updateCategory(CategoryDTO categoryDTO) {
		 
		 Category entity = em.find(Category.class, categoryDTO.getId());
		 entity.setName(categoryDTO.getName());
		 entity.setDescription(categoryDTO.getDescription());
		 try {
		 em.persist(entity);
		 }
		 catch (PersistenceException e) {
			 e.printStackTrace();
			 return false;
		 }
		 
		 return true;
	 }
}
