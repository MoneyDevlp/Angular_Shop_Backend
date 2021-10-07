package com.app.tclothes.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.tclothes.convert.ProductConvert;
import com.app.tclothes.dao.CategoryDao;
import com.app.tclothes.dao.ProductDao;
import com.app.tclothes.entity.Category;
import com.app.tclothes.entity.Product;
import com.app.tclothes.entity.Report;
import com.app.tclothes.request.ProductRequest;
import com.app.tclothes.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ProductConvert productConvert;

	@Override
	public <S extends Product> S save(S entity) {
		return productDao.save(entity);
	}

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productDao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		productDao.deleteById(id);
	}
	

	@Override
	public long getAllProductSize(){
        return productDao.count();
    }
	
	
	@Override
	public Page<Product> getAllProducts(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return productDao.findAllDesc(pageable);
    }
	
	@Override
	public Product findProductByIdAndFlag(Integer productId) {
		return productDao.findProductByIdAndFlag(productId);
	}
	
	@Override
	public void deleteProduct(Integer productId) {
		Product p = productDao.findProductByIdAndFlag(productId);
		p.setDeleteFlag(1);
		productDao.save(p);
	}
	
	@Override
	public void saveProduct(ProductRequest productRequest) {
		Category category = categoryDao.findById(productRequest.getCategoryId()).get();
		Product product = productDao.save(productConvert.convertEntity(productRequest, category));
		product.setDeleteFlag(0);
	}
	
	
	@Override
	public void updateProduct(ProductRequest productRequest) {
		Product product = productDao.findById(productRequest.getProductId()).get();
		product.setName(productRequest.getName());
		product.setQuantity(productRequest.getQuantity());
		product.setUnitPrice(productRequest.getUnitPrice());
		product.setDiscount(productRequest.getDiscount());
		product.setEnteredDate(productRequest.getEnteredDate());
		product.setImage(productRequest.getImage());
		product.setDescription(productRequest.getDescription());
		Category category = categoryDao.findById(productRequest.getCategoryId()).get();
		product.setCategory(category);
		productDao.save(product);
		
	}
	
	
	@Override
	public List<Report> getInventoryByCategory() {
		return productDao.getInventoryByCategory();
	}

	@Override
	public List<Object[]> getReportByCategory() {
		return productDao.getReportByCategory();
	}

	@Override
	public List<Object[]> getProductByDay(Integer day, Integer month, Integer year) {
		return productDao.getProductByDay(day, month, year);
	}

	@Override
	public List<Object[]> getProductByEnteredDate(java.sql.Date enteredDate) {
		return productDao.getProductByEnteredDate(enteredDate);
	}

	@Override
	public List<Object[]> getProductDetail(Integer id) {
		return productDao.getProductDetail(id);
	}

	
	@Override
	public long findProductSize() {
		return productDao.findProductSize();
	}

	@Override
	public List<Product> findAllProduct() {
		return productDao.findAllProduct();
	}

	@Override
	public List<Product> getProductTop() {
		return productDao.getProductTop();
	}

	@Override
	public Page<Product> findProductByNameAndPageDesc(String name, int page,int size) {
		Pageable pageable = PageRequest.of(page,size);
		return productDao.findProductByNameAndPage(name, pageable);
	}

	@Override
	public Page<Product> findProductByCategory(Integer categoryId, int page,int size) {
		Pageable pageable = PageRequest.of(page,size);
		return productDao.findProductByCategory(categoryId, pageable);
	}
	
	
	
}
