package com.app.tclothes.request;



import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date enteredDate;
	
	@NotBlank(message = "Image id not null!")
	String image;
	
	@NotBlank(message = "Description is not null!")
	String description;
	
	@NotNull(message = "Category is not null!")
	Integer categoryId;
}
