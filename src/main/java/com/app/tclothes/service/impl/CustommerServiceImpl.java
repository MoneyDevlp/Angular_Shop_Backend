package com.app.tclothes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.tclothes.dao.CustommerDao;
import com.app.tclothes.entity.Category;
import com.app.tclothes.entity.Custommer;
import com.app.tclothes.entity.Order;
import com.app.tclothes.request.SignupRequest;
import com.app.tclothes.service.CustommerService;

@Service
public class CustommerServiceImpl implements CustommerService{
	
	@Autowired
	CustommerDao custommerDao;

	@Override
	public Page<Custommer> findAllCustommer(int page,int size) {
		Pageable pageable = PageRequest.of(page,size);
		return custommerDao.findAllCustommer(pageable);
	}

	@Override
	public long getAllCustommerSize(){
        return custommerDao.findCustommerSize();
    }

	@Override
	public void deleteCustommer(Long id) {
		Custommer c = custommerDao.findCustommersByIdAndFlag(id);
		c.setDeleteFlag(1);
		custommerDao.save(c);
	}

	@Override
	public List<Object[]> findOrderByCustommer(String username) {
		return custommerDao.findOrderByCustommer(username);
	}

	@Override
	public Page<Custommer> findCustommerByNameAndPage(String fullname, int page,int size) {
		Pageable pageable = PageRequest.of(page,size);
		return custommerDao.findCustommerByNameAndPage(fullname, pageable);
	}
	
	
	
}
