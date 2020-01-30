package com.xworkz.medi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xworkz.medi.entity.MediEntity;
import com.xworkz.medi.service.RegisterService;

@Controller
@RequestMapping("/")
public class LogInController {
	@Autowired
	private RegisterService registerService;

	public LogInController() {
		System.out.println("LogInController");
	}

	@RequestMapping(value = "LogIn.nan", method = RequestMethod.POST)
	public ModelAndView onLogIn(MediEntity entity, HttpServletRequest request, ModelMap map) {
		System.out.println("entity is\t" + entity);
		try {
			MediEntity check = registerService.loginCheck(entity);
			if (check!=null) {
				HttpSession session = request.getSession(true);
				System.out.println("Session in sign in " + session);
				session.setAttribute("medi", check);
				System.out.println("save data susscessfully....");
				return new ModelAndView("Home", "message", "welcome to medi portal");
			} else {
				System.out.println("not save data");
				return new ModelAndView("LogIn", "message", "Invalid EId and  password");
			}
		} catch (Exception e) {
			System.err.println("Exception in login\t" + e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("LogIn", "message", "Something went wrong");
	}
}
