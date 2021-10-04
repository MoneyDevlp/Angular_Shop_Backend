package com.app.tclothes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.tclothes.dao.OrderDetailDao;
import com.app.tclothes.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	
	@Autowired
	OrderDetailDao orderDetailDao;

	@Override
	public List<Object[]> findOrderDetailByOrderId(Long orderId) {
		return orderDetailDao.findOrderDetailByOrderId(orderId);
	}
	
	
}
