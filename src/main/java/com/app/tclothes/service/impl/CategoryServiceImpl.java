package com.app.tclothes.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.tclothes.dao.CategoryDao;
import com.app.tclothes.entity.Account;
import com.app.tclothes.entity.Category;
import com.app.tclothes.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> findAll() {
		return categoryDao.findAllDescNoPage();
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryDao.findById(id);
	}


	@Override
	public long getAllCategorySize(){
        return categoryDao.count();
    }
	
	@Override
	public Page<Category> getAllCategories(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return categoryDao.findAllDesc(pageable);
    }

	@Override
	public List<Object[]> findProductByCategory(Integer id) {
		return categoryDao.findProductByCategory(id);
	}

	@Override
	public Category findCategoryByIdAndFlag(Integer categoryId) {
		return categoryDao.findCategoryByIdAndFlag(categoryId);
	}
	
	@Override
	public void deleteCategory(Integer categoryId) {
		Category c = categoryDao.findCategoryByIdAndFlag(categoryId);
		c.setDeleteFlag(1);
		categoryDao.save(c);
	}
	
	@Override
	public void saveCategory(Category category) {
		category.setDeleteFlag(0);
		categoryDao.save(category);
	}

	@Override
	public long findCategorySize() {
		return categoryDao.findCategorySize();
	}

	@Override
	public Page<Category> findCategoryByNameAndPageDesc(String name, int page,int size) {
		Pageable pageable = PageRequest.of(page,size);
		return categoryDao.findCategoryByNameAndPageDesc(name, pageable);
	}
	
	
}
