package com.app.tclothes.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.tclothes.entity.Product;
import com.app.tclothes.request.ProductRequest;
import com.app.tclothes.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/")
public class ProductRestController {
	
	@Autowired
	ProductService productService;
	
	/* Hàm lấy tất cả sản phẩm */
	
//	@GetMapping("listProduct")
//	public ResponseEntity<List<Product>> getAll(){
//		return ResponseEntity.ok(productService.findAllProduct());
//	}
	
	@GetMapping("productTop")
	public ResponseEntity<List<Product>> getProductTop() {
		return ResponseEntity.ok(productService.getProductTop());
	}
	
	/* Hàm lấy tất cả sản phẩm có phân trang*/
	
	@GetMapping("productPage")
	public ResponseEntity<Page<Product>> getAll(@RequestParam int page,@RequestParam int size){
		return ResponseEntity.ok(productService.getAllProducts(page, size));
	}
	
	@GetMapping("productSearchAndPage")
	public ResponseEntity<Page<Product>> getProductSearchAndPage(@RequestParam String name, @RequestParam int page,@RequestParam int size){
		return ResponseEntity.ok(productService.findProductByNameAndPageDesc(name, page, size));
	}
	
	@GetMapping("productByCategory")
	public ResponseEntity<Page<Product>> getProductByCategory(@RequestParam Integer categoryId, @RequestParam int page,@RequestParam int size){
		return ResponseEntity.ok(productService.findProductByCategory(categoryId, page, size));
	}
	
	/* Hàm lấy độ dài của sản phẩm*/
	
	@GetMapping("productSize")
    public long orderSize(){
        return productService.findProductSize();
    }
	
	/* Hàm lấy sản phẩm theo mã sản phẩm */
	
	@GetMapping("product/{productId}")
	public Map<String, Object> getProductById(@PathVariable("productId") Integer productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Product p = productService.findProductByIdAndFlag(productId);
			if(p == null) {
				 map.put("status", false);
	             return map;
			}
			else {
				map.put("result", p);
                map.put("status", true);
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;	
	}
	
	@GetMapping("pro/{id}")
	public List<Object[]> getProductDetail(@PathVariable("id") Integer id){
		return productService.getProductDetail(id);
	}
	
	/* Hàm thêm mới một sản phẩm */
	
	@PostMapping("product")
	public Map<String, Object> post(@Valid @RequestBody ProductRequest product, Errors err) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(err.hasErrors()) {
				for(int i = 0; i< err.getAllErrors().size(); i++) {
					map.put("er", err.getAllErrors().get(i).getDefaultMessage());
					return map;
				}
			}else {				
					productService.saveProduct(product);
					map.put("status", true);
					return map;			
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", 500);
		}
		return map;
	}
	
	/* Hàm chỉnh sửa một sản phẩm theo mã sản phẩm */
	
	@PutMapping("product/{productId}")
	public Map<String, Object> put(@PathVariable("productId") Integer productId,@Valid @RequestBody ProductRequest product,Errors err) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(err.hasErrors()) {
				for(int i =0; i < err.getAllErrors().size(); i++) {
					map.put("er", err.getAllErrors().get(i).getDefaultMessage());
					return map;
				}
			}
			else {
				Product p = productService.findById(product.getProductId()).get();
				if(p == null) {
					map.put("status", false);
					return map;
				}
				else {
					productService.updateProduct(product);
					map.put("status", true);
					return map;
				}
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}
	
	/* Hàm xóa sản phẩm theo mã sản phẩm */
	
	@DeleteMapping("product/{productId}")
	public Map<String, Object> delete(@PathVariable("productId") Integer productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Product p = productService.findProductByIdAndFlag(productId);
			if(p == null) {
				map.put("status", false);
				return map;
			}
			else {
				productService.deleteProduct(productId);
				map.put("status", true);
				return map;
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}
}
