package com.app.tclothes.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import com.app.tclothes.dao.ProductDao;
import com.app.tclothes.entity.Account;
import com.app.tclothes.entity.Category;
import com.app.tclothes.entity.Product;
import com.app.tclothes.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/")
public class CategoryRestController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductDao productDao;

	// Check CategoryName
	public boolean checkCategoryName(String name) {
		List<Category> list = categoryService.findAll();
		for (Category c : list) {
			if (c.getName().equalsIgnoreCase(name)) {
				return false;
			}
		}
		return true;
	}

	/* Hàm lấy tất cả loại hàng */

	@GetMapping("categories")
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(categoryService.findAll());
	}

	/* Hàm lấy tất cả loại hàng có phân trang */

	@GetMapping("categoryPage")
	public ResponseEntity<Page<Category>> getAll(@RequestParam int page, @RequestParam int size) {
		return ResponseEntity.ok(categoryService.getAllCategories(page, size));
	}
	
	@GetMapping("categorySearchAndPage")
	public ResponseEntity<Page<Category>> getCategorySearchAndPage(@RequestParam String name, @RequestParam int page, @RequestParam int size) {
		return ResponseEntity.ok(categoryService.findCategoryByNameAndPageDesc(name, page, size));
	}
	/* Hàm lấy độ dài của loại hàng */

	@GetMapping("categorySize")
	public long orderSize() {
		return categoryService.findCategorySize();
	}

	/* Hàm lấy loại hàng theo mã loại hàng */

	@GetMapping("category/{categoryId}")
	public Map<String, Object> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Category c = categoryService.findCategoryByIdAndFlag(categoryId);
			if(c == null) {
				 map.put("status", false);
	             return map;
			}
			else {
				map.put("result", c);
                map.put("status", true);
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;	
	}
	
	@GetMapping("cate/{id}")
	public List<Object[]> findProductByCategory(@PathVariable("id") Integer id){
		return categoryService.findProductByCategory(id);
	}

	/* Hàm thêm mới một loại hàng */

	@PostMapping("category")
	public Map<String, Object> post(@Valid @RequestBody Category category, Errors err) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(err.hasErrors()) {
				for(int i = 0; i< err.getAllErrors().size(); i++) {
					map.put("er", err.getAllErrors().get(i).getDefaultMessage());
					return map;
				}
			}else {
				if(!checkCategoryName(category.getName())) {
					map.put("status", false);
					return map;
				}
				else {
					categoryService.saveCategory(category);
					map.put("status", true);
					return map;
				}
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}

	/* Hàm chỉnh sửa một loại hàng theo mã loại hàng */

	@PutMapping("category/{categoryId}")
	public Map<String, Object> put(@PathVariable("categoryId") Integer categoryId,@Valid @RequestBody Category category,Errors err) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(err.hasErrors()) {
				for(int i =0; i < err.getAllErrors().size(); i++) {
					map.put("er", err.getAllErrors().get(i).getDefaultMessage());
					return map;
				}
			}
			else {
				Category c = categoryService.findCategoryByIdAndFlag(categoryId);
				if(c == null) {
					map.put("status", false);
					return map;
				}
				else {
					categoryService.saveCategory(category);
					map.put("status", true);
					return map;
				}
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}

	/* Hàm xóa loại hàng theo mã loại hàng */

	@DeleteMapping("category/{categoryId}")
	public Map<String, Object> delete(@PathVariable("categoryId") Integer categoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Category c = categoryService.findCategoryByIdAndFlag(categoryId);
			List<Product> list = productDao.findProductByCategory(categoryId);
			if(c == null) {
				map.put("status", false);
				return map;
			}
			else {
				if(list.size() > 0) {
					map.put("status", 501);
					return map;
				}
				else {
					categoryService.deleteCategory(categoryId);
					map.put("status", true);
					return map;
				}
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}
}
