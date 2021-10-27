package com.app.tclothes.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.tclothes.entity.Category;

public interface CategoryService {


	Optional<Category> findById(Integer id);

	List<Category> findAll();

	Page<Category> getAllCategories(int page, int size);

	long getAllCategorySize();

	List<Object[]> findProductByCategory(Integer id);

	void saveCategory(Category category);

	void deleteCategory(Integer categoryId);

	Category findCategoryByIdAndFlag(Integer categoryId);

	long findCategorySize();

	Page<Category> findCategoryByNameAndPageDesc(String name, int page, int size);

	void deleteMultipleCategories(List<Integer> categoryIds);


}
