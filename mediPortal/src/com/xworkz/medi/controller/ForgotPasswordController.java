package com.xworkz.medi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xworkz.medi.dto.ForgotPwdDTO;
import com.xworkz.medi.service.forgetPassword.ForgotPwdService;

@Controller
@RequestMapping("/")
public class ForgotPasswordController {
	@Autowired
	private ForgotPwdService forgotPwdService;

	public ForgotPasswordController() {
		System.out.println("created:" + this.getClass().getSimpleName());
	}

	@RequestMapping("ForgotPwd.nan")
	public ModelAndView resetPasswordContoller(ForgotPwdDTO forgotPwdDTO) {
		System.out.println(" invoked reset forgot password controller");
		try {
			String entity = this.forgotPwdService.resetPasswordService(forgotPwdDTO);
			if (entity != null) {
				System.out.println("new password generated");
				return new ModelAndView("ChangePwd", "message", "please, reset your password");
			} else {
				System.out.println("invalid user");
				return new ModelAndView("ForgotPwd", "message", "user not exits");
			}
		} catch (Exception e) {
			System.out.println("exception is caught");
		}
		return new ModelAndView("ForgotPwd", "message", "user not exits");
	}

	@RequestMapping("ChangePwd.nan")
	public ModelAndView updatePassword(ForgotPwdDTO forgotPwdDTO) {
		System.out.println("invoked update password");
		try {
			boolean noOfRowsUpdated = this.forgotPwdService.updatePasswordService(forgotPwdDTO);
			if (noOfRowsUpdated) {
				System.out.println("successfully updated");
				return new ModelAndView("LogIn", "message", "Please, login with new password");
			} else {
				System.out.println("Please, enter correct password");
				return new ModelAndView("ChangePwd", "message", "Please, enter correct details");
			}
		} catch (Exception e) {
			System.out.println("exception is caught");
		}
		return new ModelAndView("ChangePwd", "message", "please, enter correct details");
	}
}
