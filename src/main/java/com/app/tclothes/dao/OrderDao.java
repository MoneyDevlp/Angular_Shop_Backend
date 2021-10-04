package com.app.tclothes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.tclothes.entity.Order;

public interface OrderDao extends JpaRepository<Order, Long>{
	
	@Query("SELECT o FROM Order o WHERE o.status = 1 ORDER BY o.orderId DESC")
	List<Order> findAllOrder();
	
	@Query("SELECT COUNT(o) FROM Order o WHERE o.status = 1")
	long findOrderSize();
	
}
