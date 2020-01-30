package com.xworkz.medi.service.forgetPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.xworkz.medi.Utility.RandomPasswordGenrator;
import com.xworkz.medi.dao.RegisterDAO;
import com.xworkz.medi.dto.ForgotPwdDTO;

@Service
public class ForgotPwdServiceImpl implements ForgotPwdService {
	@Autowired
	private MailSender mailSender;
	@Autowired
	private RegisterDAO registerDAO;
	@Autowired
	private RandomPasswordGenrator randomPasswordGenrator;

	public ForgotPwdServiceImpl() {
		System.out.println("created:" + this.getClass().getSimpleName());
	}

	@Override
	public String resetPasswordService(ForgotPwdDTO pwdDTO) {
		System.out.println("invoked resetPassword");
		String pwd=randomPasswordGenrator.genratePassword();
		pwdDTO.setPassword(pwd);
		String email=pwdDTO.getEmail();
		try
		{
			boolean entity=this.registerDAO.forgotPasswordDAO(pwdDTO);
			if(entity)
			{
				System.out.println(entity);
				this.sendMailToChangePwdService(pwdDTO);
				return email;
			}
		}
		catch (Exception e) {
			System.out.println("exception is handled");
		}
		return null;
	}

	@Override
	public boolean updatePasswordService(ForgotPwdDTO pwdDTO) {
		System.out.println("invoked update Password");
		String pwd = pwdDTO.getPassword();
		String newPwd = pwdDTO.getNewPassword();
		String confirmPwd = pwdDTO.getNewConfirmPassword();
		System.out.println("Current password:" + pwd);
		System.out.println("New password:" + newPwd);
		System.out.println("Confirmed new password:" + confirmPwd);
		boolean vaildPassword = true;
		try {
			if (vaildPassword == true) {
				if (newPwd.length() > 8 && newPwd.length() < 16 && confirmPwd.length() > 8 && confirmPwd.length() < 16
						&& newPwd.equals(confirmPwd)) {
					System.out.println("password is correct and matching");
				} else {
					return false;
				}
			}
			if (vaildPassword) {
				vaildPassword = registerDAO.updatePasswordDAO(pwdDTO);
				return vaildPassword;
			}
		} catch (Exception e) {
			System.out.println("exception is handled");
		}
		return false;
	}

	@Override
	public boolean sendMailToChangePwdService(ForgotPwdDTO pwdDTO) {
		System.out.println("invoked sendMail");
		System.out.println(pwdDTO.getEmail());
		try {
			System.out.println("email sending is in process");
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(pwdDTO.getEmail());
			mailMessage.setSubject("Successfully Registered");
			mailMessage.setText("Mr/Mrs...." + pwdDTO.getEmail() + "your reset password is" + pwdDTO.getPassword());
			mailSender.send(mailMessage);
			System.out.println("change password mail sent to" + pwdDTO.getEmail() + "reset password is" + pwdDTO.getPassword());
			return true;

		} catch (Exception e) {
			System.out.println("exception is handled");
		}
		return false;
	}

}
