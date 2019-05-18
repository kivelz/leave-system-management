package com.leave.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.system.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
