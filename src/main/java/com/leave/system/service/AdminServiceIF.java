package com.leave.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.leave.system.model.Employee;
import com.leave.system.model.Role;

public interface AdminServiceIF {


	void saveEmployee(Employee employee);
	
	List<Employee> findByRole(Role role);


	Employee authenticate(String name, String password);
	
	boolean createEmployee(Employee employee);	
	
	List<Employee> findAll();

	Optional<Employee> findEmployeeId(Integer managerid);
	
	Optional<Employee> findById(Integer id);

	Employee findByuserid(String userid);
	
	void deleteEmployee(Employee employee);
	

	Employee changeEmployee(Employee emp);

	Page<Employee> paginationFindAll(PageRequest of);


	


}