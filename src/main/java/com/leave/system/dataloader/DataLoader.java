//package com.leave.system.dataloader;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.leave.system.model.Employee;
//import com.leave.system.model.Role;
//import com.leave.system.repository.EmployeeRepository;
//import com.leave.system.repository.RoleRepository;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//	private EmployeeRepository employeeRepository;
//	private RoleRepository rolerepo;
//
//	@Autowired
//	public void setRolerepo(RoleRepository rolerepo) {
//		this.rolerepo = rolerepo;
//	}
//
//	@Autowired
//	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
//		this.employeeRepository = employeeRepository;
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		Role role = new Role("Manager", 22, 60);
//		Role role1 = new Role("Admin", 14, 60);
//		Role role3 = new Role("Staff", 22, 60);
//		List<Role> entities = new ArrayList<Role>();
//		entities.add(role);
//		entities.add(role1);
//		entities.add(role3);
//		rolerepo.saveAll(entities);
//
//		Employee emp1 = new Employee("Tan ah tan", "Test1", "DJHSDHJKH", "KEWKE@dssd.com", 2, role);
//		Employee emp2 = new Employee("tony tan", "Test2", "dhsjhdaksjhdh", "test2@test.com", 2, role1);
//		Employee emp3 = new Employee("Alfred yeoh", "Test3", "dhsjhdaksjhdh", "test3@test.com", 2, role1);
//		Employee emp4 = new Employee("Esther tan", "Test4", "dhsjhdaksjhdh", "test2@test.com", 1, role1);
//		Employee emp5 = new Employee("Yuen kwan", "Test5", "dhsjhdaksjhdh", "test2@test.com", 2, role1);
//		Employee emp6 = new Employee("Cher wah", "Test10", "dhsjhdaksjhdh", "test2@test.com", 2, role1);
//		Employee emp7 = new Employee("Kwan ge", "Test5", "dhsjhdaksjhdh", "test2@test.com", 2, role1);
//
//		List<Employee> elist = new ArrayList<>();
//		elist.add(emp1);
//		elist.add(emp2);
//		elist.add(emp3);
//		elist.add(emp4);
//		elist.add(emp5);
//		elist.add(emp6);
//		elist.add(emp7);
//		employeeRepository.saveAll(elist);
//
//	}
//
//}
