package com.xworkz.medi.service.search;

import java.util.List;

import com.xworkz.medi.entity.AppointmentEntity;

public interface SearchService {
	public List<AppointmentEntity> searchService(String empId);
}
