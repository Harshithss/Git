package com.xworkz.medi.service.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.medi.dao.RegisterDAO;
import com.xworkz.medi.entity.AppointmentEntity;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private RegisterDAO dao;

	public SearchServiceImpl() {
		System.out.println("Created " + this.getClass().getSimpleName());
	}

	@Override
	public List<AppointmentEntity> searchService(String empId) {
		try {
			System.out.println("Search Service Started");
			List<AppointmentEntity> list=dao.searchDAO(empId);
			for (AppointmentEntity appointmentEntity : list) {
				System.out.println("List is "+appointmentEntity);
			}
			return list;
		}
		catch (Exception e) {
			System.out.println("Exeption in SearchServiceImpl");
			e.printStackTrace();
		}
		return null;
	}

}
