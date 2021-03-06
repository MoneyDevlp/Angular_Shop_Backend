package com.app.tclothes.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long orderId;

	String orderTrackingNumber;

	int totalQuantity;

	BigDecimal totalPrice;

	Integer status;

	@CreationTimestamp
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	private LocalDateTime lastUpdated;

	String phone;

	String adress;

	@ManyToOne
	@JoinColumn(name = "id")
	Custommer custommer;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	List<OrderDetail> orderDetails;

	public void add(OrderDetail orderDetail) {
		if (orderDetail != null) {
			if (orderDetails == null) {
				orderDetails = new ArrayList<>();
			}
			orderDetail.setOrder(this);
			orderDetails.add(orderDetail);

		}
	}
}
