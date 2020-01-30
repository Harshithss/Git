package com.xworkz.medi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.xworkz.medi.dao.RegisterDAO;
import com.xworkz.medi.dto.RegisterDTO;
import com.xworkz.medi.entity.MediEntity;
@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private RegisterDAO registerDAO;

	@Autowired
	private MailSender mailSender;

	@Override
	public boolean saveAndValidation(RegisterDTO dto) {
		System.out.println("saveAndValidation RegisterServiceImpl");
		try {

			System.out.println("saving and validating dto" + dto);
			boolean valid = true;
			if (dto != null) {
				String email = dto.getEmail();
				String pwd = dto.getPassword();
				String cfmPwd = dto.getConfirmPassword();

				if (email != null && !email.isEmpty() && email.length() > 8 && email.length() < 50) {
					System.out.println("email is valid ");

				} else {
					System.out.println("invalid email");
					valid = false;

				}

				if (pwd != null && !pwd.isEmpty() && pwd.length() > 8 && pwd.length() < 16) {
					System.out.println("pwd is valid");
				} else {
					System.out.println("pwd is invalid");
					valid = false;

				}

				if (cfmPwd != null && !cfmPwd.isEmpty() && cfmPwd.length() > 8 && cfmPwd.length() < 16) {
					System.out.println("cfmPwd is valid");
				} else {
					System.out.println("cfmpwd is invalid");
					valid = false;

				}
				if (pwd.equals(cfmPwd)) {
					System.out.println("password is matching and valid");
				} else {
					System.out.println("password is not matching");
					valid = false;

				}

			}
			if (valid) {
				System.out.println("saving valid data");
				MediEntity entity = new MediEntity();
				BeanUtils.copyProperties(dto, entity);

				System.out.println("invoked save validate method" + entity);

				valid = this.registerDAO.saveService(entity);
				if (valid == true) {
					this.sendMail(dto);				}
			}
			return valid;

		} catch (Exception e) {
			System.err.println("Exception saveAndValidation");
			e.printStackTrace();

		}
		return false;

	}

	@Override
	public boolean sendMail(RegisterDTO dto) {
		System.out.println("invoked sendMail");
		System.out.println(dto);
		try {
			System.out.println("send mail proccesing");
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			//mailMessage.setFrom("nandinimc01@gmail.com");
			mailMessage.setTo(dto.getEmail());
			mailMessage.setSubject("succesful registration");
			mailMessage.setText("mr/mrs" + dto.getEmail() + "you have succesfully registred" + dto.getEmail());
			mailSender.send(mailMessage);
			System.out.println("mail send to " + dto.getEmail());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public MediEntity loginCheck(MediEntity entity) {
		try {
			System.out.println("saving and validating entity");
			boolean valid = true;
			if (valid) {
				System.out.println("validate date saving to db");
				MediEntity mediEntity=this.registerDAO.checkExistForLogin(entity);
				return mediEntity;
			}
		} catch (Exception e) {
			System.out.println("Exception for login service");
			e.printStackTrace();
		}
		return null;
	}


}
