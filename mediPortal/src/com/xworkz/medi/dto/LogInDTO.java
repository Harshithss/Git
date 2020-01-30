 package com.xworkz.medi.dto;

import java.io.Serializable;

import lombok.Data;
@Data
public class LogInDTO implements Serializable{
	private String email;
	private String password;
}
