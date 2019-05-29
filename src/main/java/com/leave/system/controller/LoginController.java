package com.leave.system.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.SessionAttributes;

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
	public String Login(Model model, Employee employee) {
		model.addAttribute("employee", new Employee());
		return "login";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String autheticateRoute(Model model, Employee employee, HttpSession session, HttpServletRequest request) {
		Employee emp = adminService.authenticate(employee.getName(), employee.getPassword());
//		System.out.println(emp.toString());
		if (emp != null) {
			UserSession userSession = new UserSession();
			userSession.setEmployee(emp);
			int id = userSession.getEmployee().getId();
			session.setAttribute("US", userSession);
			if (emp.getRole().getId() == 1) {
				return "redirect:/admin/employee/";
			} else if (emp.getRole().getId() == 2) {
				return "redirect:/manager/viewsubleave?managerId=" + id;
			} else if (emp.getRole() != null) {
				return "redirect:/staff/history";
			}
		}
//		else {
			model.addAttribute("error", "invalid username or password");
			return "login";
//		}			
//		return "login";
	}
}
