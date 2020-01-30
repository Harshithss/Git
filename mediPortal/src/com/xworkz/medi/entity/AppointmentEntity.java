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
@Table(name="appointment_details")
public class AppointmentEntity implements Serializable {
	@Id
	@GenericGenerator(name="auto",strategy="increment")
	@GeneratedValue(generator="auto")
	@Column(name="appointment_id")
	private int aId;
	@Column(name="appointment_empId")
	private String empId;
	@Column(name="appointment_time")
	private String aTime;
	@Column(name="appiontment_date")
	private String aDate;
	@Column(name="appointment_reason")
	private String aReason;
	@Column(name="appointment_status")
	private String aStatus;
	
}
