package com.leave.system.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.leave.system.model.Role;

public class RoleValidator  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Role.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
