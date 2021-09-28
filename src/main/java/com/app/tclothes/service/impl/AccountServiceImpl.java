package com.app.tclothes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.tclothes.dao.AccountDao;
import com.app.tclothes.entity.Account;
import com.app.tclothes.entity.Category;
import com.app.tclothes.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountDao accountDao;

	@Override
	public <S extends Account> S save(S entity) {
		return accountDao.save(entity);
	}

	@Override
	public List<Account> findAll() {
		return accountDao.findAll();
	}

	@Override
	public Optional<Account> findById(Integer id) {
		return accountDao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		accountDao.deleteById(id);
	}
	
	
	@Override
	public long getAllAccountSize(){
        return accountDao.count();
    }
	
	
	@Override
	public List<Account> getAllAccount(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return accountDao.findAll(pageable).getContent();
    }

	@Override
	public Optional<Account> findByUsername(String username) {
		return accountDao.findByUsername(username);
	}

	@Override
	public List<Object[]> getAccountDetail(Integer accId) {
		return accountDao.getAccountDetail(accId);
	}

	@Override
	public List<Account> finAllAccount() {
		return accountDao.findAllDesc();
	}

	@Override
	public Account findAccountByIdAndFlag(Integer id) {
		return accountDao.findAccountByIdAndFlag(id);
	}
	
	@Override
	public void deleteAccount(Integer id) {
		Account acc = accountDao.findAccountByIdAndFlag(id);
		acc.setDeleteFlag(1);
		accountDao.save(acc);
	}
	
	@Override
	public void saveAccount(Account account) {
		account.setDeleteFlag(0);
		accountDao.save(account);
	}
}
