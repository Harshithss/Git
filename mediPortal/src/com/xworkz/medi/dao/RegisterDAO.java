package com.xworkz.medi.dao;

import java.util.List;

import com.xworkz.medi.dto.ForgotPwdDTO;
import com.xworkz.medi.entity.AppointmentEntity;
import com.xworkz.medi.entity.MediEntity;

public interface RegisterDAO {
	public boolean saveService(MediEntity entity);
	MediEntity checkExistForLogin(MediEntity entity);
	public boolean forgotPasswordDAO(ForgotPwdDTO pwdDTO);
	public boolean updatePasswordDAO(ForgotPwdDTO pwdDTO);
	public boolean createAppiontment(AppointmentEntity appointmentEntity);
	public List<AppointmentEntity> searchDAO(String empId);
}
