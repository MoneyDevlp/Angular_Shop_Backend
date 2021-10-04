package com.app.tclothes.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.tclothes.entity.Category;
import com.app.tclothes.entity.Order;
import com.app.tclothes.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/")
public class OrderRestController {
	
	@Autowired
	OrderService orderService;
	
	@PutMapping("updateCancel/{orderId}")
	public Map<String, Object> UpdateCancel(@PathVariable("orderId") Long orderId,@RequestBody Order order) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Order o = orderService.findById(orderId).get();
				if(o == null) {
					map.put("status", false);
					return map;
				}
				else {
					o.setStatus(0);
					orderService.save(o);
					map.put("status", true);
					return map;
				}

		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}
	
	@PutMapping("updatePending/{orderId}")
	public Map<String, Object> updatePending(@PathVariable("orderId") Long orderId,@RequestBody Order order) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Order o = orderService.findById(orderId).get();
				if(o == null) {
					map.put("status", false);
					return map;
				}
				else {
					o.setStatus(1);
					orderService.save(o);
					map.put("status", true);
					return map;
				}

		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}
	
	@PutMapping("updateDelivering/{orderId}")
	public Map<String, Object> updateDelivering(@PathVariable("orderId") Long orderId,@RequestBody Order order) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Order o = orderService.findById(orderId).get();
				if(o == null) {
					map.put("status", false);
					return map;
				}
				else {
					o.setStatus(2);
					orderService.save(o);
					map.put("status", true);
					return map;
				}

		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}
	
	@GetMapping("orders")
	public ResponseEntity<List<Order>> getAllOrder() {
		return ResponseEntity.ok(orderService.findAllOrder());
	}
	
	@GetMapping("orderSize")
	public long orderSize() {
		return orderService.findOrderSize();
	}
}
