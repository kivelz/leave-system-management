package com.leave.system.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import  org.springframework.data.repository.PagingAndSortingRepository;

import com.leave.system.model.Employee;
import com.leave.system.model.Role;
import com.leave.system.repository.EmployeeRepository;


@Service
public class AdminService implements AdminServiceIF {
	
	@Autowired 
	private EmployeeRepository eRepository;

	
	@Override
	public Employee authenticate(String userid, String password) {
		Employee eList = eRepository.findByUseridAndPassword(userid, password);
		return eList;	
	}
	
	@Override
	@Transactional
	public void saveEmployee(Employee employee) {
		eRepository.save(employee);
	}
	
	@Override
	@Transactional
	public List<Employee> findByRole(Role role) {
		role.setId(1);
		List<Employee> list = eRepository.findByRole(role);		
		return list;
	}
	
	@Override
	@Transactional
	public boolean createEmployee(Employee employee) {
		eRepository.save(employee);
		return true;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		return eRepository.findAll();
	}
		
	@Override
	@Transactional
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

	@Override
	@Transactional
	public Employee changeEmployee(Employee emp) {
		return eRepository.save(emp);
	}

	@Override
	@Transactional
	public Page<Employee> paginationFindAll(PageRequest request) {		   	
//		int page1 =0;
//		int size = 10;
		 Page<Employee> pages = eRepository.findAll(PageRequest.of(request.getPageNumber(), request.getPageSize()));
		 return pages;
	}



	
	
}
