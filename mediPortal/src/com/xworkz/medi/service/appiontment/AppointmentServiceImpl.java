package com.xworkz.medi.service.appiontment;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.medi.dao.RegisterDAO;
import com.xworkz.medi.entity.AppointmentEntity;
@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private RegisterDAO dao;
	
	public AppointmentServiceImpl() {
		System.out.println("created appointmentServiceImpl");
	}
	@Override
	public AppointmentEntity appointmentService(AppointmentEntity entity) {
		try
		{
			AppointmentEntity appointmentEntity=new AppointmentEntity();
			BeanUtils.copyProperties(entity, appointmentEntity);
			Boolean data=dao.createAppiontment(appointmentEntity);
			System.out.println("data is saved:"+data);
			return appointmentEntity;
		}
		catch (Exception e) {
			System.err.println("exception found");
		}
		return null;
	}

}
