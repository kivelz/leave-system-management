package com.leave.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leave.system.model.Employee;
import com.leave.system.model.Role;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByRole(Role role);
	
	
//	@Query(value = "select  from Employee  as em inner join Role ON em.role.id=2")
//	List<Employee> findByRole();

}
