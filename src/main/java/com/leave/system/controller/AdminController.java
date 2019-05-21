package com.leave.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leave.system.model.Employee;
import com.leave.system.model.Role;
import com.leave.system.repository.EmployeeRepository;
import com.leave.system.repository.RoleRepository;
import com.leave.system.validator.EmployeeValidator;

@Controller
public class AdminController {

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

	@InitBinder
	protected void InitBinder(WebDataBinder binder) {
		binder.addValidators(new EmployeeValidator());
	}

	@RequestMapping(path = "/")
	public String index() {
		return "index";
	}

	// get method for viewing all employee
	@RequestMapping(path = "/employees", method = RequestMethod.GET)
	public String viewEmployee(Model model) {
		List<Employee> employees = employeeRepository.findAll();
		Map<String, Employee> employeesManagers = new HashMap<String, Employee>();
		for (Employee employee : employees) {

			Optional<Employee> value = employeeRepository.findById(employee.getManagerid());
//			System.out.println( value.get());
			if (value.isPresent() && value.get().getId() != null) {
				employeesManagers.put(employee.getManagerid(), value.get());
//				System.out.println("Name : "+value.get().getName());
			}
		}

		model.addAttribute("employees", employees);
		model.addAttribute("employeesManagers", employeesManagers);
		return "employees";
	}

	// get method for getting employee form
	@RequestMapping(path = "/employees/add", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		Role roleToFindRole = new Role();
		roleToFindRole.setId(1);
		List<Employee> list = employeeRepository.findByRole(roleToFindRole);
		model.addAttribute("employee", new Employee());
		model.addAttribute("roles", rRepo.findAll());
		model.addAttribute("empWithRole", list);
		return "addemployee";
	}

	// post method for saving employee
	@RequestMapping(path = "/employees", method = RequestMethod.POST)
	public String saveEmployee(@Valid Employee employee, BindingResult bindingResult, Model model) {
		Role roleToFindRole = new Role();
		List<Role> roles = rRepo.findAll();
		roleToFindRole.setId(1);
		
		List<Employee> list = employeeRepository.findByRole(roleToFindRole);	
		if (bindingResult.hasErrors()) {	
			model.addAttribute("employee", employee);
			model.addAttribute("empWithRole", list);
			model.addAttribute("roles", roles);
			return "addemployee";
		}
		employeeRepository.save(employee);
		return "redirect:/employees";

	}

	@RequestMapping(path = "/employees/update/{id}", method = RequestMethod.GET)
	public String editEmployee(Model model, @PathVariable(name = "id") String id) {
		Role roleToFindRole = new Role();
		roleToFindRole.setId(1);
		List<Employee> list = employeeRepository.findByRole(roleToFindRole);
		Employee em = employeeRepository.findById(id).orElse(null);
		model.addAttribute("roles", rRepo.findAll());
		model.addAttribute("employee", em);
		model.addAttribute("empWithRole", list);
		return "editemployee";
	}

	@RequestMapping(path = "/employees/delete/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable(name = "id") String id) {
		employeeRepository.delete(employeeRepository.findById(id).orElse(null));
		return "redirect:/employees";
	}

}
