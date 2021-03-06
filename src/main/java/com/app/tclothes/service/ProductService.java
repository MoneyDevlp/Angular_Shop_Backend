package com.app.tclothes.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.app.tclothes.entity.Product;
import com.app.tclothes.entity.Report;
import com.app.tclothes.request.ProductRequest;

public interface ProductService {

	void deleteById(Integer id);

	Optional<Product> findById(Integer id);

	List<Product> findAll();

	<S extends Product> S save(S entity);

	Page<Product> getAllProducts(int page, int size);

	long getAllProductSize();

	List<Report> getInventoryByCategory();

	List<Object[]> getReportByCategory();

	List<Object[]> getProductByDay(Integer day, Integer month, Integer year);

	List<Object[]> getProductByEnteredDate(java.sql.Date enteredDate);

	List<Object[]> getProductDetail(Integer id);

//	void saveProduct(ProductRequest productRequest);

	void deleteProduct(Integer productId);

	Product findProductByIdAndFlag(Integer productId);

	void updateProduct(ProductRequest productRequest);

	void saveProduct(ProductRequest productRequest);

	long findProductSize();

	List<Product> findAllProduct();

	List<Product> getProductTop();

	Page<Product> findProductByNameAndPageDesc(String name, int page, int size);

	Page<Product> findProductByCategory(Integer categoryId, int page, int size);

	void deleteMultipleProducts(List<Integer> productIds);



}
