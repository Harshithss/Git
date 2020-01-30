package com.xworkz.medi.service.forgetPassword;

import com.xworkz.medi.dto.ForgotPwdDTO;

public interface ForgotPwdService {
	
	public String resetPasswordService(ForgotPwdDTO pwdDTO);

	public boolean updatePasswordService(ForgotPwdDTO pwdDTO);

	public boolean sendMailToChangePwdService(ForgotPwdDTO pwdDTO);
	
	//public String changePasswordService(String newPassword, String email) ;
	
}
