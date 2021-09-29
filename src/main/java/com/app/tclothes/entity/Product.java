package com.app.tclothes.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer productId;
	
	@NotBlank(message = "Name is not null!")
	String name;
	
	@NotNull(message = "Quantity is not null!")
	@PositiveOrZero(message = "Quantity cannot be less than 0!")
	Integer quantity;
	
	@NotNull(message = "UnitPrice is not null!")
	@PositiveOrZero(message = "UnitPrice cannot be less than 0!")
	Double unitPrice;
	
	@NotNull(message = "Discount is not null!")
	@PositiveOrZero(message = "Discount cannot be less than 0!")
	Double discount;
	
	@NotNull(message = "EnteredDate is not null!")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date enteredDate;
	
	@NotBlank(message = "Image id not null!")
	String image;
	
	@NotBlank(message = "Description is not null!")
	String description;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	@NotNull(message = "Category is not null!")
	Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Comment> comments;
	
	@ColumnDefault("0")
    private int deleteFlag;
}
