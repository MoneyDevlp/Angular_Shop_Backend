package com.app.tclothes.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.app.tclothes.entity.Product;
import com.app.tclothes.entity.Report;
import com.app.tclothes.request.ProductRequest;

public interface ProductService {

	void deleteById(Integer id);

	Optional<Product> findById(Integer id);

	List<Product> findAll();

	<S extends Product> S save(S entity);

	List<Product> getAllProducts(int page, int size);

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





}
