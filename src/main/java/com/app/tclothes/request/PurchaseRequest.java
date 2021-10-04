package com.app.tclothes.request;

import java.util.List;
import java.util.Set;

import com.app.tclothes.entity.Account;
import com.app.tclothes.entity.Custommer;
import com.app.tclothes.entity.Order;
import com.app.tclothes.entity.OrderDetail;

import lombok.Data;

@Data
public class PurchaseRequest {
	
	 Custommer custommer;
	 Order order;
	 List<OrderDetail> orderDetails;
}
