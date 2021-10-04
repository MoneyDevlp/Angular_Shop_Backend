package com.app.tclothes.service;

import java.util.List;


import com.app.tclothes.entity.Custommer;
import com.app.tclothes.entity.Order;

public interface CustommerService {

	List<Custommer> findAllCustommer(int page, int size);

	long getAllCustommerSize();

	void deleteCustommer(Long id);

	List<Object[]> findOrderByCustommer(String username);



	

}
