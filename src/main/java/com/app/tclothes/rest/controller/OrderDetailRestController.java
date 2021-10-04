package com.app.tclothes.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.tclothes.service.OrderDetailService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/")
public class OrderDetailRestController {
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@GetMapping("orderDetail")
	public List<Object[]> findOrderDetail(@RequestParam("orderId") Long orderId) {
		return orderDetailService.findOrderDetailByOrderId(orderId);
	}
}
