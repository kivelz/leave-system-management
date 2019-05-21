package com.leave.system.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.leave.system.model.Employee;


@Component
public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Employee.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Employee employee = (Employee) obj;
		if(employee.getName().length() < 6) {
			errors.rejectValue("name", "name");
		}
		else if(employee.getUserid().length() < 6) {
			errors.rejectValue("userid", "userid");
		}
		else if(employee.getPassword().length() < 6) {
			errors.rejectValue("password", "password");
		}
		
	}

}
