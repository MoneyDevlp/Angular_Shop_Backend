package com.app.tclothes.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.app.tclothes.entity.Account;

public interface AccountService {

	void deleteById(Integer id);

	Optional<Account> findById(Integer id);

	List<Account> findAll();

	<S extends Account> S save(S entity);

	List<Account> getAllAccount(int page, int size);

	long getAllAccountSize();

	Optional<Account> findByUsername(String username);

	List<Object[]> getAccountDetail(Integer accId);

	List<Account> finAllAccount();

	Account findAccountByIdAndFlag(Integer id);

	void deleteAccount(Integer id);

	void saveAccount(Account account);

	

}
