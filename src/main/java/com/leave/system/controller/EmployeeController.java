package com.leave.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leave.system.model.Employee;
import com.leave.system.model.Role;
import com.leave.system.repository.EmployeeRepository;
import com.leave.system.repository.RoleRepository;

@Controller
public class EmployeeController {

	private EmployeeRepository employeeRepository;
	private RoleRepository rRepo;

	@Autowired
	public void setrRepo(RoleRepository rRepo) {
		this.rRepo = rRepo;
	}

	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@RequestMapping(path = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(path = "/employees", method = RequestMethod.GET)
	public String viewEmployee(Model model) {
		model.addAttribute("employees", employeeRepository.findAll());
		model.addAttribute("roles", rRepo.findAll());
		return "employees";
	}

	@RequestMapping(path = "/employees/add", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		
		Role roleToFindRole = new Role();
		roleToFindRole.setId(1);		
		List<Employee> list = employeeRepository.findByRole(roleToFindRole);
		
		
		model.addAttribute("employee", new Employee());
		model.addAttribute("roles" ,rRepo.findAll());
		model.addAttribute("empWithRole", list);
		
//		Role roleToFindRole = new Role();

		return "addemployee";
	}

	@RequestMapping(path = "/employees", method = RequestMethod.POST)
	public String saveEmployee(Employee employee) {
		employeeRepository.save(employee);
		return "redirect:/employees";
	}

}
