package com.leave.system.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.leave.system.javabean.UserSession;
import com.leave.system.model.Role;
import com.leave.system.repository.RoleRepository;
import com.leave.system.service.RoleServiceIF;
import com.leave.system.validator.RoleValidator;

@Controller
@SessionAttributes("session")
@RequestMapping("/admin/role")
public class RoleController {
	
	@Autowired
	private RoleServiceIF repository;
	
	
	@InitBinder
	protected void InitBinder(WebDataBinder binder) {
		binder.addValidators(new RoleValidator());
	}
	
	
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)

	public String viewRole(Model model, HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("US");
		if(userSession.getEmployee().getRole().getId() == 1) {
			int userid = userSession.getEmployee().getId();
			String id = "/role/update" + userid;
			model.addAttribute("roles", repository.findAll());
			model.addAttribute("uri", id);
			return "viewrole";
		}
		return "redirect:/home/login";
	}
	
	
	@RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
	public String editRole(Model model, @PathVariable(name = "id") Integer id, HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("US");
		if(userSession.getEmployee().getRole().getId() == 1) {
			Role role = repository.findById(id).orElse(null);
			model.addAttribute("roles", role);
			return "editrole";
		}
		return "redirect:/home/login";
	}
	
	@RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
	public String saveRole(Role role, @PathVariable(name = "id") Integer id) {
		repository.saveRole(role);
		return "redirect:/admin/role/list";

	}
	
	
	

}
