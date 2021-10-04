package com.app.tclothes.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.tclothes.dao.CustommerDao;
import com.app.tclothes.entity.Custommer;
import com.app.tclothes.entity.Order;
import com.app.tclothes.entity.OrderDetail;
import com.app.tclothes.request.PurchaseRequest;
import com.app.tclothes.response.PurchaseResponse;
import com.app.tclothes.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService{
	
	@Autowired
	CustommerDao custommerDao;
	
	@Override
	public PurchaseResponse checkOut(PurchaseRequest purchase) {
		Order order = purchase.getOrder();
		
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		order.setStatus(1);
		
		order.setAdress(purchase.getOrder().getAdress());
		order.setPhone(purchase.getOrder().getPhone());
		
		List<OrderDetail> orderDetails = purchase.getOrderDetails();
		orderDetails.forEach(item -> order.add(item));
		
		
		
		Custommer custommer = purchase.getCustommer();
		
		if(custommerDao.findByUsername(custommer.getUsername()) != null) {
			custommer = custommerDao.findByUsername(custommer.getUsername()).get();
		}
		
		custommer.add(order);
		
		custommerDao.save(custommer);
		
		return new PurchaseResponse(orderTrackingNumber);
	}
	
	private String generateOrderTrackingNumber() {
		return UUID.randomUUID().toString();
	}
}
