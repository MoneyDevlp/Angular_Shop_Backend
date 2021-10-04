package com.app.tclothes.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderdetails")
public class OrderDetail implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long orderDetailId;
	
	String name;
	
	String imageUrl;
	
	Integer quantity;
	
	Double unitPrice;
	
//	@ManyToOne
//	@JoinColumn(name = "productId")
//	Product product;
	Long productId;
	
	@ManyToOne
	@JoinColumn(name = "orderId")
	Order order;
}
