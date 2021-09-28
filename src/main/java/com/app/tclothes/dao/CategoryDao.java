package com.app.tclothes.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.tclothes.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{
	
	@Query(value = "SELECT c.name, p.name, p.quantity, p.unitPrice FROM Product p "
			+ "INNER JOIN Category c ON p.category.categoryId = c.categoryId WHERE c.categoryId = ?1")
	List<Object[]> findProductByCategory(Integer id );
	
	@Query("SELECT c FROM Category c WHERE c.deleteFlag=0 ORDER BY c.categoryId DESC")
    Page<Category> findAllDesc(Pageable pageable);
	
	@Query("SELECT c FROM Category c WHERE c.deleteFlag=0")
    List<Category> findAllDescNoPage();
	
	@Query("SELECT COUNT(c) FROM Category c WHERE c.deleteFlag=0")
	long findCategorySize();
	
	@Query("SELECT c FROM Category c WHERE c.categoryId=?1 and c.deleteFlag=0")
    Category findCategoryByIdAndFlag(Integer categoryId);
}
