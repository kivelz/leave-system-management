package com.leave.system.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.leave.system.javabean.UserSession;
import com.leave.system.model.Employee;

import com.leave.system.service.AdminService;

@Controller
@RequestMapping(value = "/home")
@SessionAttributes("session")
public class LoginController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView Login(@ModelAttribute Employee employee) {
		ModelAndView mavAndView = new ModelAndView("login");
		mavAndView.addObject("employee", new Employee());
		return mavAndView;
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView autheticateRoute(@ModelAttribute Employee employee, HttpSession session) {
		ModelAndView mavAndView = new ModelAndView("addemployee");
		Employee emp = adminService.authenticate(employee.getName(), employee.getPassword());
		UserSession userSession = new UserSession();
		userSession.setEmployee(emp);
		session.setAttribute("US", userSession);
		if (emp.getRole().getId() == 1) {
			return new ModelAndView("redirect:/admin/employee/");
		} 
		else if(emp.getRole().getId() == 2) {
			return new ModelAndView("redirect:/manager/");
		}
		return mavAndView;
	}
}
