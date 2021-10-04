package com.app.tclothes.service;

import java.util.List;
import java.util.Optional;

import com.app.tclothes.entity.Order;

public interface OrderService {

	Optional<Order> findById(Long id);

	<S extends Order> S save(S entity);

	List<Order> findAllOrder();

	long findOrderSize();

}
