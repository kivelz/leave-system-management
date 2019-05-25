package com.leave.system.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void saveEmployee(Employee employee) {
		eRepository.save(employee);
	}
	
	public List<Employee> findByRole(Role role) {
		role.setId(1);
		List<Employee> list = eRepository.findByRole(role);		
		return list;
	}
	
	
	public boolean createEmployee(Employee employee) {
		eRepository.save(employee);
		return true;
	}
	
	public List<Employee> findAll() {
		return eRepository.findAll();
	}
		
	public Optional<Employee> findById(Integer id) {
		return eRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Optional<Employee> findEmployeeId(Integer id) {
		return eRepository.findEmployeeById(id);
	}

	@Override
	@Transactional
	public Employee findByuserid(String userid) {
		// TODO Auto-generated method stub
		return eRepository.findByuserid(userid);
	}
	
	@Override
	@Transactional
	public void deleteEmployee(Employee employee) {
		 eRepository.delete(eRepository.findById(employee.getId()).orElse(null));
	}


	
}
