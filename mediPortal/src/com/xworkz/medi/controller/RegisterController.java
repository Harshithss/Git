package com.xworkz.medi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xworkz.medi.dto.RegisterDTO;
import com.xworkz.medi.service.RegisterService;

@Controller
@RequestMapping("/")
public class RegisterController {
	@Autowired
	private RegisterService registerService;

	public RegisterController() {
		System.out.println("registerController");

	}

	@RequestMapping(value = "Register.nan", method = RequestMethod.POST)
	public ModelAndView onRegister(RegisterDTO dto, ModelMap map) {
		System.out.println("RegisterController");
		try {

			System.out.println(dto);
			boolean saved = this.registerService.saveAndValidation(dto);
			System.out.println("onRegister");
			if (saved) {
				System.out.println("save succesfully for register data");
				return new ModelAndView("LogIn", "message", "successfully logged in");

			} else {
				System.out.println("data not saved for register data");
				return new ModelAndView("Register", "message", "enter vaild details");
			}
		} catch (Exception e) {
			System.err.println("Exception" + e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("Register", "message", "enter valid details");
	}
}
