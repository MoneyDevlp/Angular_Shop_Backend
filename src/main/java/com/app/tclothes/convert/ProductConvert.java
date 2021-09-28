package com.app.tclothes.convert;

import org.springframework.stereotype.Component;

import com.app.tclothes.entity.Category;
import com.app.tclothes.entity.Product;
import com.app.tclothes.request.ProductRequest;

@Component
public class ProductConvert {
	
	public Product convertEntity(ProductRequest productRequest, Category category) {
		Product product = new Product();
		product.setName(productRequest.getName());
		product.setQuantity(productRequest.getQuantity());
		product.setUnitPrice(productRequest.getUnitPrice());
		product.setDiscount(productRequest.getDiscount());
		product.setEnteredDate(productRequest.getEnteredDate());
		product.setImage(productRequest.getImage());
		product.setDescription(productRequest.getDescription());
		product.setCategory(category);
		return product;
	}
}
