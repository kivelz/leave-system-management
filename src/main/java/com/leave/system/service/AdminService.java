package com.leave.system.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.system.model.Employee;
import com.leave.system.model.Role;
import com.leave.system.repository.EmployeeRepository;


@Service
public class AdminService implements AdminServiceIF {
	
	@Autowired 
	private EmployeeRepository eRepository;

	
	@Override
	public Employee authenticate(String userid, String password) {
		List<Employee> eList = eRepository.findByUseridAndPassword(userid, password);
		return eList.get(0);	
	}
	
	@Override
	public void saveEmployee(Employee employee) {
		eRepository.save(employee);
	}
	
	public List<Employee> findByRole(Role role) {
		role.setId(1);
		List<Employee> list = eRepository.findByRole(role);		
		return list;
	}
	
	public Employee findById(Integer id) {
		Employee employee = eRepository.findById(id).orElse(null);
		return employee;
	}
	
	public boolean createEmployee(Employee employee) {
		eRepository.save(employee);
		return true;
	}

}
