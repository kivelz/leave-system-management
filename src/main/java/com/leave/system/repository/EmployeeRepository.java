package com.leave.system.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leave.system.model.Employee;
import com.leave.system.model.Role;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//	Optional<Employee> findById(String id);
	List<Employee> findByRole(Role role);
	Employee findByuserid(String userid);
	@Query("SELECT e FROM Employee e where e.id = :id")	
	Optional<Employee> findEmployeeById(@org.springframework.data.repository.query.Param("id") Integer id);
	
	@Query("SELECT e FROM Employee e where e.id = :id")
	Optional<Employee> findById(@Value("id") Integer id);
	
	public List<Employee> findByUseridAndPassword(String userid, String password);
	
	@Query("SELECT DISTINCT m.name FROM Employee e, Employee m where e.managerid = m.id ")
	ArrayList<String> findAllManagerNames();

	
}
