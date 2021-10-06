package com.app.tclothes.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(	name = "Custommers", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})

public class Custommer implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@NotBlank(message = "Username is not null!")
	String username;
	
	@NotBlank(message = "Fullname is not null!")
	String fullname;

	@NotBlank(message = "Email is not null!")
	@Email
	String email;

	@NotBlank(message = "Password is not null!")
	String password;
	
	@ColumnDefault("0")
    private int deleteFlag;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "login_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	Set<Role> roles = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "custommer", cascade = CascadeType.ALL)
	List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "custommer")
	private List<Comment> comments;
	
	public void add(Order order) {
		if (order != null) {
			if (orders == null) {
				orders = new ArrayList<Order>();
			}
			order.setCustommer(this);
			orders.add(order);
		}
	}
	
	public Custommer(String username, String fullname, String email, String password) {
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}
	
	
}
