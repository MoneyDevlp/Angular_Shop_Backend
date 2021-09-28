package com.app.tclothes.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.tclothes.entity.Login;

@Repository
public interface LoginDao extends JpaRepository<Login, Long>{
	
	Optional<Login> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
