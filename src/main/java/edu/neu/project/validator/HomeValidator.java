package edu.neu.project.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.neu.project.model.UserAccount;


public class HomeValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserAccount.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserAccount user = (UserAccount)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "validate.username", "Your Name Is Incorrenct");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validate.password", "Your password Is Incorrenct");
	}
}
