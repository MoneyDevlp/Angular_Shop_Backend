package com.app.tclothes.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.tclothes.entity.Account;

public interface AccountDao extends JpaRepository<Account, Integer>{
	Optional<Account> findByUsername(String username);
	
	@Query(value = "SELECT a.fullname, a.gender, a.email FROM Account a WHERE a.id =?1")
	List<Object[]> getAccountDetail(Integer accId);
	
	@Query("SELECT ac FROM Account ac WHERE ac.deleteFlag=0 ORDER BY ac.id DESC")
    List<Account> findAllDesc();
	
	@Query("SELECT ac FROM Account ac WHERE ac.id=?1 and ac.deleteFlag=0")
    Account findAccountByIdAndFlag(Integer id);
}
