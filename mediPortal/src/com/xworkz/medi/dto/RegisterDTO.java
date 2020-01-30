package com.xworkz.medi.dto;

import java.io.Serializable;

import lombok.Data;
@Data
public class RegisterDTO implements Serializable {
	private String empId;
	private String email;
	private String password;
	private String confirmPassword;
}
