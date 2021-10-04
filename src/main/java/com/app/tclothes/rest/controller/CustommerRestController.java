package com.app.tclothes.rest.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.tclothes.dao.CustommerDao;
import com.app.tclothes.dao.RoleDao;
import com.app.tclothes.entity.Category;
import com.app.tclothes.entity.Custommer;
import com.app.tclothes.entity.ERole;
import com.app.tclothes.entity.Order;
import com.app.tclothes.entity.Product;
import com.app.tclothes.entity.Role;
import com.app.tclothes.request.SignupRequest;
import com.app.tclothes.service.CustommerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/")
public class CustommerRestController {
	
	@Autowired
	CustommerService custommerService;
	
	@Autowired
	RoleDao roleRepository;
	
	@Autowired
	CustommerDao custommerDao;

	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("custommersPage")
	public ResponseEntity<List<Custommer>> findAllCustommer(@RequestParam int page, @RequestParam int size) {
		return ResponseEntity.ok(custommerService.findAllCustommer(page, size));
	}
	
	@GetMapping("custommerSize")
	public long custommerSize() {
		return custommerService.getAllCustommerSize();
	}
	
	@GetMapping("custommer/{id}")
	public Map<String, Object> getCustommerById(@PathVariable("id") Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Custommer custommer = custommerDao.findCustommersByIdAndFlag(id);
			if(custommer == null) {
				 map.put("status", false);
	             return map;
			}
			else {
				map.put("result", custommer);
                map.put("status", true);
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;	
	}
	
	
	@PutMapping("custommer/{id}")
	public Map<String, Object> put(@PathVariable("id") Long id,@Valid @RequestBody Custommer cus,Errors err) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(err.hasErrors()) {
				for(int i =0; i < err.getAllErrors().size(); i++) {
					map.put("er", err.getAllErrors().get(i).getDefaultMessage());
					return map;
				}
			}
			else {
				Custommer custommer = custommerDao.findCustommersByIdAndFlag(id);
				if(custommer == null) {
					map.put("status", false);
					return map;
				}
				else {
					cus.setPassword(encoder.encode(cus.getPassword()));
					custommerDao.save(cus);
					map.put("status", true);
					return map;
				}
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}
	
	@DeleteMapping("custommer/{id}")
	public Map<String, Object> delete(@PathVariable("id") Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Custommer custommer = custommerDao.findCustommersByIdAndFlag(id);
			if(custommer == null) {
				map.put("status", false);
				return map;
			}
			else {
					custommerService.deleteCustommer(id);
					map.put("status", true);
					return map;	
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}
	
	@GetMapping("custommer")
	public Optional<Custommer> getCustommer(@RequestParam("username") String username) {
		Optional<Custommer> op = custommerDao.findByUsername(username);
		if (op.isPresent()) {
			op.get();
		}
		return op;
		
	}
	
	@GetMapping("orderHistory")
	public List<Object[]> getOrderByCustommer(@RequestParam("username") String username) {
		return custommerService.findOrderByCustommer(username);
	}
}
