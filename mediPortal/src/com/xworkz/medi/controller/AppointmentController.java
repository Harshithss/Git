package com.xworkz.medi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xworkz.medi.entity.AppointmentEntity;
import com.xworkz.medi.entity.MediEntity;
import com.xworkz.medi.service.RegisterService;
import com.xworkz.medi.service.appiontment.AppointmentService;
@Controller
@RequestMapping("/")
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;

	public AppointmentController() {
		System.out.println("Created " + this.getClass().getSimpleName());
	
	}

	@RequestMapping(value = "Appointment.nan", method = RequestMethod.POST)
	public ModelAndView onSubmit(AppointmentEntity entity, HttpServletRequest request) {
		try {
			System.out.println("AppointmentController started");
			HttpSession session = request.getSession(false);
			System.out.println("Session in Appointment Controller "+session);
			if(session!=null) {
				MediEntity mediEntity=(MediEntity) session.getAttribute("medi");
				System.out.println("Entity from Session "+mediEntity);
				BeanUtils.copyProperties(mediEntity, entity);
				entity.setEmpId(mediEntity.getEmpId());
				AppointmentEntity entity2=appointmentService.appointmentService(entity);
				System.out.println("data from db "+entity2);
				System.out.println("AppointmentCntroller ended");
				return new ModelAndView("Home","message","Appointment is confirmed");
			}
			else {
				System.out.println("session is null");
				return new ModelAndView("Home","message","Appointment is not created");
			}

		} catch (Exception e) {
			System.out.println("Exception is handled");
			e.printStackTrace();
		}
		return new ModelAndView("Home","message","Appointment is not created");

	}
}
