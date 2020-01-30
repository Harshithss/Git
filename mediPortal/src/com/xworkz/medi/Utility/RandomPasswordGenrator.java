package com.xworkz.medi.Utility;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class RandomPasswordGenrator {

	public RandomPasswordGenrator() {
		System.out.println(this.getClass().getSimpleName()+ " is Created...........");
	}

	String capLetters = "QWERTYUIOPLKJHGFDSAZXCVBNM";
	String lowLetters = "qwertyuioplkjhgfdsazxcvbnm";
	String specLetters = "*^%$#/-+";
	String numLetters = "1234567890";

	public String genratePassword() {
		StringBuilder builder = new StringBuilder();
		builder.append(RandomStringUtils.random(1, capLetters)).append(RandomStringUtils.random(1, lowLetters))
				.append(RandomStringUtils.random(1, specLetters)).append(RandomStringUtils.random(2, numLetters));
		return new String(builder);
	}
}