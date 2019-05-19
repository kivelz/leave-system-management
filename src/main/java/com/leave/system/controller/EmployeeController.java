package com.leave.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leave.system.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	private EmployeeRepository employeeRepository;

	/**
	 * @param employeeRepository the employeeRepository to set
	 */
	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@RequestMapping(path ="/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(path = "/employees")
	public String viewEmployee(Model model) {
		model.addAttribute("employees", employeeRepository.findAll());
		return "employees";
	}
}
