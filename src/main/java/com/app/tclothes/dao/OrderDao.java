package com.app.tclothes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tclothes.entity.Order;

public interface OrderDao extends JpaRepository<Order, Long>{

}
