package com.leave.system.service;

import java.util.List;

import com.leave.system.model.Employee;
import com.leave.system.model.Role;

public interface AdminServiceIF {


	void saveEmployee(Employee employee);
	
	List<Employee> findByRole(Role role);

	Employee authenticate(String name, String password);
	
	boolean createEmployee(Employee employee);
	
	Employee findById(Integer id);

}