package com.xworkz.medi.service;

import com.xworkz.medi.dto.RegisterDTO;
import com.xworkz.medi.entity.MediEntity;

public interface RegisterService {
	public boolean saveAndValidation(RegisterDTO dto);

	public boolean sendMail(RegisterDTO dto);

	MediEntity loginCheck(MediEntity entity);
	
	
}
