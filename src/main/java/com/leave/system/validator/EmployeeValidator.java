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
		if(employee.getEmail().length() > 50) {
			errors.reject("name", "name");
		}
		
	}

}
