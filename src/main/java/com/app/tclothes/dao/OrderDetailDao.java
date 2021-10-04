package com.app.tclothes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.tclothes.entity.OrderDetail;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Long>{
	
	@Query("SELECT d.name, d.imageUrl, d.quantity, d.unitPrice FROM OrderDetail d "
			+ "INNER JOIN Order o ON d.order.orderId = o.orderId WHERE o.orderId=?1")
	List<Object[]> findOrderDetailByOrderId(Long orderId);
}
