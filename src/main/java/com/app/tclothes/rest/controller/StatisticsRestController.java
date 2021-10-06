package com.app.tclothes.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.tclothes.entity.Report;
import com.app.tclothes.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/")
public class StatisticsRestController {
	
	@Autowired
	ProductService productService;
	
//	@GetMapping()
//	public List<Report> getReportByCategory(){
//		return productService.getInventoryByCategory();
//	}
	
	@GetMapping("reportByCategory")
	public List<Object[]> getReportByCategorys(){
		return productService.getReportByCategory();
	}
	
	@GetMapping("{day}/{month}/{year}")
	public List<Object[]> getReportProductByDay(@PathVariable("day") Integer day,
			@PathVariable("month") Integer month, 
			@PathVariable("year") Integer year) {
		return productService.getProductByDay(day, month, year);
	}
	
	@GetMapping("reportProduct/{enteredDate}")
	public List<Object[]> getReportProductByEnteredDate(@PathVariable("enteredDate") java.sql.Date enteredDate) {
		return productService.getProductByEnteredDate(enteredDate);
	}
}
