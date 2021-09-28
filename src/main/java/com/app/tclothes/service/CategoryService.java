package com.app.tclothes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.tclothes.entity.Category;

public interface CategoryService {

	void deleteById(Integer id);

	Optional<Category> findById(Integer id);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	List<Category> getAllCategories(int page, int size);

	long getAllCategorySize();

	List<Object[]> findProductByCategory(Integer id);

	void saveCategory(Category category);

	void deleteCategory(Integer categoryId);

	Category findCategoryByIdAndFlag(Integer categoryId);

	long findCategorySize();


}
