package com.xworkz.medi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "user_details")
public class MediEntity implements Serializable {
	@Id
	@GenericGenerator(name = "auto", strategy = "increment")
	@GeneratedValue(generator = "auto")
	
	@Column(name = "sl_no")
	private int slNo;
	
	@Column(name = "e_emp_id")
	private String empId;

	@Column(name = "e_email")
	private String email;

	@Column(name = "e_password")
	private String password;

	private transient String confirmPassword;
}
