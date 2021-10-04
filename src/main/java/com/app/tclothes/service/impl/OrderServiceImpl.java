package com.app.tclothes.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.tclothes.dao.OrderDao;
import com.app.tclothes.entity.Order;
import com.app.tclothes.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDao orderDao;

	@Override
	public Optional<Order> findById(Long id) {
		return orderDao.findById(id);
	}

	@Override
	public <S extends Order> S save(S entity) {
		return orderDao.save(entity);
	}

	@Override
	public List<Order> findAllOrder() {
		return orderDao.findAllOrder();
	}

	@Override
	public long findOrderSize() {
		return orderDao.findOrderSize();
	}
	
	
}
