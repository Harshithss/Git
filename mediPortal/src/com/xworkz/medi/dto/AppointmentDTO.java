package com.xworkz.medi.dto;

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
@Table(name="appointment_details")
public class AppointmentDTO implements Serializable{
	@Id
	@GenericGenerator(name="auto",strategy="increment")
	@GeneratedValue(generator="auto")
	@Column(name="appointment_empId")
	private String empId;
	@Column(name="appointment_id")
	private String id;
	@Column(name="appointment_time")
	private String time;
	@Column(name="appiontment_date")
	private String date;
	@Column(name="appointment_reason")
	private String reason;
	@Column(name="appointment_status")
	private String status;
}
