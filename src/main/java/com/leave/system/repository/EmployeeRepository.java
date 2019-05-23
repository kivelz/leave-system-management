package com.leave.system.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leave.system.model.Employee;
import com.leave.system.model.Role;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//	Optional<Employee> findById(String id);
	List<Employee> findByRole(Role role);
	Employee findByuserid(String userid);
	
}
