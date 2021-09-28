package com.app.tclothes.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.tclothes.entity.Account;
import com.app.tclothes.entity.Category;
import com.app.tclothes.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/")
public class AccountRestController {

	@Autowired
	AccountService accountService;

	// check email
	public boolean checkEmail(String email) {
		List<Account> list = accountService.findAll();
		for (Account c : list) {
			if (c.getEmail().equalsIgnoreCase(email)) {
				return false;
			}
		}
		return true;
	}
	
	// check username
		public boolean checkUsername(String username) {
			List<Account> list = accountService.findAll();
			for (Account c : list) {
				if (c.getUsername().equalsIgnoreCase(username)) {
					return false;
				}
			}
			return true;
		}
	
	/* Hàm lấy tất cả tài khoản */
		
	@GetMapping("accounts")
	public List<Account> getAll() {
		return accountService.finAllAccount();
	}
	
	/* Hàm lấy tất cả tài khoản có phân trang*/
	
	@GetMapping("/accountPage")
	public ResponseEntity<List<Account>> getAll(@RequestParam int page,@RequestParam int size){
		return ResponseEntity.ok(accountService.getAllAccount(page, size));
	}
	
	/* Hàm lấy độ dài của tài khoản*/
	
	@GetMapping("accountSize")
    public long accountSize(){
        return accountService.getAllAccountSize();
    }
	
	/* Hàm lấy tài khoản theo mã tài khoản */
	
	@GetMapping("account/{id}")
	public Map<String, Object> getAccountById(@PathVariable("id") Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Account acc = accountService.findAccountByIdAndFlag(id);
			if(acc == null) {
				 map.put("status", false);
	             return map;
			}
			else {
				map.put("result", acc);
                map.put("status", true);
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}
	
	@GetMapping("acc/{accId}")
	public List<Object[]> getAccountDetail(@PathVariable("accId") Integer accId){
		return accountService.getAccountDetail(accId);
	}
	
	/* Hàm thêm mới một tài khoản */
	
	@PostMapping("account")
	public Map<String, Object> post(@Valid @RequestBody Account account, Errors err) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(err.hasErrors()) {
				for(int i = 0; i< err.getAllErrors().size(); i++) {
					map.put("er", err.getAllErrors().get(i).getDefaultMessage());
					return map;
				}
			}else {
				if(!checkUsername(account.getUsername())) {
					map.put("status", false);
					return map;
				}
				else {
					accountService.save(account);
					map.put("status", true);
					return map;
				}
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}
	
	/* Hàm chỉnh sửa một tài khoản theo mã tài khoản */
	
	@PutMapping("account/{id}")
	public Map<String, Object> put(@PathVariable("id") Integer id,@Valid @RequestBody Account account,Errors err ) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(err.hasErrors()) {
				for(int i =0; i < err.getAllErrors().size(); i++) {
					map.put("er", err.getAllErrors().get(i).getDefaultMessage());
					return map;
				}
			}
			else {
				Account acc = accountService.findAccountByIdAndFlag(id);
				if(acc == null) {
					map.put("status", false);
					return map;
				}
				else {
					accountService.save(account);
					map.put("status", true);
					return map;
				}
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}
	
	/* Hàm xóa tài khoản theo mã tài khoản */
	
	@DeleteMapping("account/{id}")
	public Map<String, Object> delete(@PathVariable("id") Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Account acc = accountService.findAccountByIdAndFlag(id);
			if(acc == null) {
				map.put("status", false);
				return map;
			}
			else {
				accountService.deleteAccount(id);
				map.put("status", true);
				return map;
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}

}
