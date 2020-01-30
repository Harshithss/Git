package com.xworkz.medi.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
@Data
@Entity
@Table(name = "emp_details")
public class EmpDTO implements Serializable {
	@Id
	@GenericGenerator(name = "auto", strategy = "increment")
	@GeneratedValue(generator = "auto")
	@Column(name = "sl_no")
	private int slNo;
	@Column(name = "e_id")
	private String empId;
	@Column(name = "e_email")
	private String email;
	@Column(name = "e_name")
	private String name;
	@Column(name = "e_blood_group")
	private String bloodGroup;
	@Column(name = "e_dob")
	private Date dob;
	@Column(name = "e_phNum")
	private long phNum;

}
