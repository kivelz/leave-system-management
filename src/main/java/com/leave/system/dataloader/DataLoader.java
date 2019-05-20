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
//		Employee emp1 =  new Employee("Apa Yuan Kwan", "KH2011", "DKSLJDKJDD", "ayk@u.nus.edu", "9c12f827-9b1c-4b6c-9669-7a2b6f460a70", role1);
//		Employee emp2 =  new Employee("Nani Yuan Kwan", "KH2012", "DKSLJDKJDD", "nyk@u.nus.edu", null, role1);
//		Employee emp3 =  new Employee("Sapa Yuan Kwan", "KH2013", "DKSLJDKJDD", "syk@u.nus.edu", "9c12f827-9b1c-4b6c-9669-7a2b6f460a70", role1);
//		Employee emp4 =  new Employee("Awas Yuan Kwan", "KH2014", "DKSLJDKJDD", "cyk@u.nus.edu", "9c12f827-9b1c-4b6c-9669-7a2b6f460a70", role1);
//		Employee emp5 =  new Employee("Goh Yuan Kwan", "KH2015", "DKSLJDKJDD", "ayk@u.nus.edu", "9c12f827-9b1c-4b6c-9669-7a2b6f460a70", role1);
//		Employee emp6 =  new Employee("Esther Tan", "KH2017", "DKSLJDKJDD", "etank@u.nus.edu", null, role1);
//		
//		List<Employee> elist = new ArrayList<>();
//		elist.add(emp1);
//		elist.add(emp2);
//		elist.add(emp3);
//		elist.add(emp4);
//		elist.add(emp5);
//		elist.add(emp6);
//		employeeRepository.saveAll(elist);
//
//	}
//
//}
