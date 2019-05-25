package com.leave.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.leave.system.javabean.UserSession;
import com.leave.system.model.Employee;
import com.leave.system.model.Role;
import com.leave.system.service.AdminServiceIF;
import com.leave.system.service.RoleServiceIF;
import com.leave.system.validator.EmployeeValidator;

@RequestMapping(path = "/admin/employee")
@Controller
@SessionAttributes("session")
public class AdminController {

	@Autowired
	private AdminServiceIF employeeRepository;
	@Autowired
	private RoleServiceIF rRepo;

	@InitBinder
	protected void InitBinder(WebDataBinder binder) {
		binder.addValidators(new EmployeeValidator());
	}

	@RequestMapping(path = "/")
	public String index() {
		return "index";
	}

	// get method for viewing all employee
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String viewEmployee(HttpSession session, Model model) {
		UserSession us = (UserSession) session.getAttribute("US");
		System.out.println(us.toString());
		if (us.getEmployee().getRole().getId() == 1) {

			List<Employee> employees = employeeRepository.findAll();
			Map<Integer, Employee> employeesManagers = new HashMap<Integer, Employee>();
			for (Employee employee : employees) {
				Optional<Employee> value = employeeRepository.findEmployeeId(employee.getManagerid());
//				System.out.println( value.get());
				if (value.isPresent() && value.get() != null) {
					employeesManagers.put(employee.getManagerid(), value.get());
					System.out.println("Name : " + value.get().getName());
				}

				model.addAttribute("roles", rRepo.findAll());
				model.addAttribute("employees", employees);
				model.addAttribute("employeesManagers", employeesManagers);
				return "admin/employees";
			}
		}
		return "redirect:/home/login";
	}

	// get method for getting employee form
	@RequestMapping(path = "/create", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		Role roleToFindRole = new Role();
		roleToFindRole.setId(1);
		List<Employee> list = employeeRepository.findByRole(roleToFindRole);
		model.addAttribute("employee", new Employee());
		model.addAttribute("roles", rRepo.findAll());
		model.addAttribute("empWithRole", list);
		return "admin/addemployee";
	}

	// post method for saving employee
	@RequestMapping(path = "/employees", method = RequestMethod.POST)
	public String saveEmployee(@Validated Employee employee, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		Role roleToFindRole = new Role();
		List<Role> roles = rRepo.findAll();
		roleToFindRole.setId(1);
		List<Employee> list = employeeRepository.findByRole(roleToFindRole);
		Employee emp = employeeRepository.findByuserid(employee.getUserid());
		redirectAttributes.addFlashAttribute("message", "Failed to add employee");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		if (emp != null) {
			bindingResult.rejectValue("userid", "error.user",
					"There is already a employee registered with the username provided");
		}
		if (bindingResult.hasErrors()) {

			model.addAttribute("employee", employee);
			model.addAttribute("empWithRole", list);
			model.addAttribute("roles", roles);
			return "admin/addemployee";
		}
		redirectAttributes.addFlashAttribute("message", "Successfully added employee");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		// to be implemented once login feature is done
		employeeRepository.saveEmployee(employee);
		return "redirect:/admin/employee/list";

	}

	// update employee get method
	@RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
	public String editEmployee(Model model, @PathVariable(name = "id") Integer id) {
		Role roleToFindRole = new Role();
		roleToFindRole.setId(1);
		List<Employee> list = employeeRepository.findByRole(roleToFindRole);
		Employee em = employeeRepository.findById(id).orElse(null);
		model.addAttribute("roles", rRepo.findAll());
		model.addAttribute("employee", em);
		model.addAttribute("empWithRole", list);
		return "admin/editemployee";
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public String UpdateEmployee(Employee employee) {
		employeeRepository.saveEmployee(employee);
		return "redirect:/admin/employee/list";
	}

	//delete employee
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable(name = "id") Integer id) {
		employeeRepository.deleteEmployee((employeeRepository.findById(id).orElse(null)));
		return "redirect:/admin/employee/list";
	}

}
