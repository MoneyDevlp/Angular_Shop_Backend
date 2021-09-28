package com.app.tclothes.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.tclothes.entity.Product;
import com.app.tclothes.entity.Report;

public interface ProductDao extends JpaRepository<Product, Integer>{
	
	@Query("SELECT p FROM Product p WHERE p.deleteFlag=0 ORDER BY p.productId DESC")
    Page<Product> findAllDesc(Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.productId=?1 and p.deleteFlag=0")
	Product findProductByIdAndFlag(Integer productId);
	
	@Query("SELECT COUNT(p) FROM Product p WHERE p.deleteFlag=0")
	long findProductSize();
	
	@Query("SELECT p FROM Product p WHERE p.category.categoryId =?1")
	List<Product> findProductByCategory(Integer categoryId);
	
	@Query(value = "SELECT new Report(o.category,sum(o.unitPrice),count(o))" + " FROM Product o" + " GROUP BY o.category"
            + " ORDER BY sum(o.unitPrice) DESC")
	List<Report> getInventoryByCategory();
	
	@Query(value = "SELECT c.name, SUM(p.unitPrice), COUNT(p.quantity) FROM Product p "
			+ "INNER JOIN Category c ON p.category.categoryId = c.categoryId GROUP BY c.name")
	List<Object[]> getReportByCategory();
	
	@Query(value = "SELECT p.name, p.quantity, p.unitPrice FROM Product p "
			+ "WHERE DAY(p.enteredDate)=?1 AND MONTH(p.enteredDate)=?2 AND YEAR(p.enteredDate)=?3")
	List<Object[]> getProductByDay(Integer day,Integer month, Integer year);
	
	@Query(value = "SELECT p.name, p.quantity, p.unitPrice FROM Product p "
			+ "WHERE p.enteredDate=?1")
	List<Object[]> getProductByEnteredDate(java.sql.Date enteredDate);
	
	@Query(value = "SELECT p.name, p.quantity, p.unitPrice, p.enteredDate, p.description, c.name FROM Product p "
			+ "INNER JOIN Category c ON p.category.categoryId = c.categoryId WHERE p.productId =?1")
	List<Object[]> getProductDetail(Integer id);
}
