package com.app.tclothes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tclothes.entity.OrderDetail;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Long>{

}
