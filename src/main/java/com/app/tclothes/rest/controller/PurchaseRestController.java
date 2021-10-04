package com.app.tclothes.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.tclothes.request.PurchaseRequest;
import com.app.tclothes.response.PurchaseResponse;
import com.app.tclothes.service.PurchaseService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/")
public class PurchaseRestController {
	
	@Autowired
	PurchaseService purchaseService;
	
	@PostMapping("purchase")
	public ResponseEntity<PurchaseResponse> placeOrder(@RequestBody PurchaseRequest purchase) {
		PurchaseResponse purchaseResponse = purchaseService.checkOut(purchase);
		return new ResponseEntity<>(purchaseResponse, HttpStatus.CREATED);
	}
}
