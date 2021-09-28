package com.app.tclothes.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@NotBlank(message = "Username is not null!")
	String username;
	
	@NotBlank(message = "Fullname is not null!")
	String fullname;
	
	@NotNull(message = "Gender is not null!")
	String gender;
	
	@NotNull(message = "Password is not null!")
	String password;
	
	@NotBlank(message = "Email is not null!")
	@Pattern(regexp = "^\\w{2,}.?\\w+(@\\w{3,8})(.\\w{3,8})+$",
	message = "Email is malformed! ex: abcabc@abc.abc")
	String email;
	
	@ColumnDefault("0")
    private int deleteFlag;
	
}
