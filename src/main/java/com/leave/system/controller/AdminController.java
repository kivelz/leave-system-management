package com.leave.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
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

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
		session.invalidate();
		redirectAttributes.addFlashAttribute("message", "You have successfully logged out");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/home/login";
	}
	
	
	
	// get method for viewing all employee
	@RequestMapping(value = "/**", method = RequestMethod.GET)
	public String viewEmployee(HttpServletRequest request, HttpSession session, Model model) {
		UserSession us = (UserSession) session.getAttribute("US");
		if (us.getEmployee().getRole().getId() == 1) {
			int page = 0;
			int size = 10;
			if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
				page = Integer.parseInt(request.getParameter("page")) - 1;
			}

		        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
		            size = Integer.parseInt(request.getParameter("size"));
		        }
		        
		    Page<Employee> page2 =  employeeRepository.paginationFindAll(PageRequest.of(page, size));
		    
		       
			List<Employee> employees = page2.getContent();
			Map<Integer, Employee> employeesManagers = new HashMap<Integer, Employee>();
			for (Employee employee : employees) {
				Optional<Employee> value = employeeRepository.findEmployeeId(employee.getManagerid());

				if (value.isPresent() && value.get() != null) {
					employeesManagers.put(employee.getManagerid(), value.get());
				}
			}
			
			model.addAttribute("username", us.getEmployee().getName());
			model.addAttribute("roles", rRepo.findAll());

			model.addAttribute("employees", employees);
			model.addAttribute("employeesManagers", employeesManagers);
			model.addAttribute("totalPages", page2.getTotalPages());
			model.addAttribute("page", page2.getNumber());
			return 	"admin/index";
		}
	return "redirect:/home/login";
	}

	// get method for getting employee form
	@RequestMapping(path = "/create", method = RequestMethod.GET)
	public String addEmployee(HttpSession session, Model model) {
		UserSession us = (UserSession) session.getAttribute("US");
		if (us.getEmployee().getRole().getId() == 1) {
			Role roleToFindRole = new Role();
			roleToFindRole.setId(1);
			List<Employee> list = employeeRepository.findByRole(roleToFindRole);
			model.addAttribute("employee", new Employee());
			model.addAttribute("roles", rRepo.findAll());
			model.addAttribute("empWithRole", list);
			return "admin/addemployee";
		}
		return "redirect:/home/login";

	}

	// post method for saving employee
	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String saveEmployee(@Validated Employee employee, HttpSession session, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		UserSession us = (UserSession) session.getAttribute("US");
		if (us.getEmployee().getRole().getId() == 1) {
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
			employeeRepository.saveEmployee(employee);
			return "redirect:/admin/employee/";
		}

		return "redirect:/home/login";
	}

	// update employee get method
	@RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
	public String editEmployee(Model model, HttpSession session, @PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
		UserSession us = (UserSession) session.getAttribute("US");
		if (us.getEmployee().getRole().getId() == 1) {
		Role roleToFindRole = new Role();
		roleToFindRole.setId(1);
		List<Employee> list = employeeRepository.findByRole(roleToFindRole);
		Employee em = employeeRepository.findById(id).orElse(null);
		model.addAttribute("roles", rRepo.findAll());
		model.addAttribute("employee", em);
		model.addAttribute("empWithRole", list);
		return "admin/editemployee";
		}
		redirectAttributes.addFlashAttribute("message", "You are not authorized to view this page");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		return "redirect:/home/login";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String UpdateEmployee(@Validated Employee employee, RedirectAttributes redirectAttributes,
			BindingResult bindingResult, @PathVariable(name = "id") Integer id, Model model) {	
		redirectAttributes.addFlashAttribute("message", "Missing attributes please fill in again");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		if(bindingResult.hasErrors()) {
			return "redirect:/admin/employee/update/{id}";
		}
		redirectAttributes.addFlashAttribute("message", "Successfully updated employee");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		employeeRepository.saveEmployee(employee);
		return "redirect:/admin/employee/";

	}

	// delete employee
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "Deleted employee");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		employeeRepository.deleteEmployee((employeeRepository.findById(id).orElse(null)));
		return "redirect:/admin/employee/";
	}

}
