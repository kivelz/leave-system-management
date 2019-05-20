package com.leave.system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leave.system.model.Employee;
import com.leave.system.model.Role;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
//	Optional<Employee> findById(String id);
	List<Employee> findByRole(Role role);
	
}
