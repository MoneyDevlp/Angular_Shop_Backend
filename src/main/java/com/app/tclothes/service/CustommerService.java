package com.app.tclothes.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.app.tclothes.entity.Custommer;

public interface CustommerService {

	Page<Custommer> findAllCustommer(int page, int size);

	long getAllCustommerSize();

	void deleteCustommer(Long id);

	List<Object[]> findOrderByCustommer(String username);

	Page<Custommer> findCustommerByNameAndPage(String fullname, int page, int size);



	

}
