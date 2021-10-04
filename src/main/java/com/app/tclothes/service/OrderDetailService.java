package com.app.tclothes.service;

import java.util.List;


public interface OrderDetailService {

	List<Object[]> findOrderDetailByOrderId(Long orderId);

}
