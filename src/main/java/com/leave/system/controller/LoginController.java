package com.leave.system.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.leave.system.javabean.UserSession;
import com.leave.system.model.Employee;
import com.leave.system.model.Role;
import com.leave.system.service.AdminServiceIF;
import com.leave.system.service.RoleServiceIF;

@Controller
@SessionAttributes("session")
public class LoginController {
	
	@Autowired
	private AdminServiceIF adminIF;
	@Autowired
	private RoleServiceIF rIF;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView Login(@ModelAttribute Employee employee) {
		ModelAndView mavAndView = new ModelAndView("login");
		mavAndView.addObject("employee", new Employee());
		return mavAndView;
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView autheticateRoute(@ModelAttribute Employee employee, HttpSession session) {
		ModelAndView mavAndView = new ModelAndView("addemployee");
		Employee emp = adminIF.authenticate(employee.getName(), employee.getPassword());
		if(emp.getRole().getId() == 1) {
			UserSession userSession = new UserSession();
			userSession.setEmployee(emp);
			session.setAttribute("US", userSession);
			Role role = new Role();
			List<Employee> list = adminIF.findByRole(role);
			mavAndView.addObject("empWithRole", list);
			mavAndView.addObject("roles", rIF.findAll());
			mavAndView.addObject("employee", new Employee());
			return mavAndView;
		}
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public ModelAndView createEmployee(@ModelAttribute Employee employee, HttpSession session) {
		ModelAndView mav = new ModelAndView("success");
		UserSession userSession = (UserSession) session.getAttribute("US");
		if(userSession.getEmployee().getRole().getId() == 1) {
			adminIF.saveEmployee(userSession.getEmployee());
			if(adminIF.createEmployee(employee))
				return mav;
			else {
				mav = new ModelAndView("error");
				return mav;
			}
		}
		return new ModelAndView("/redirect:/login");
		
	}
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
	public ModelAndView editEmployee(@ModelAttribute Employee employee, HttpSession session, @PathVariable(name = "id") Integer id) {
		ModelAndView mav = new ModelAndView("editEmployee");
		UserSession userSession = (UserSession) session.getAttribute("US");
		adminIF.saveEmployee(userSession.getEmployee());
		Role role = new Role();
		List<Employee> list = adminIF.findByRole(role);
		Employee em = adminIF.findById(id);
		mav.addObject("empWithRole", list);
		mav.addObject("roles", rIF.findAll());
		mav.addObject("employee", em);
		return mav;			
		
	}
	

}
