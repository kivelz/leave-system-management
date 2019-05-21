package com.leave.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leave.system.model.Role;
import com.leave.system.repository.RoleRepository;
import com.leave.system.validator.RoleValidator;

@Controller
public class RoleController {
	
	private RoleRepository repository;
	
	@Autowired
	public void setRepository(RoleRepository repository) {
		this.repository = repository;
	}
	
	@InitBinder
	protected void InitBinder(WebDataBinder binder) {
		binder.addValidators(new RoleValidator());
	}
	
	
	@RequestMapping(path = "/roles", method = RequestMethod.GET)
	public String viewRole(Model model) {
		model.addAttribute("roles", repository.findAll());
		return "viewrole";
	}
	
	@RequestMapping(path = "/roles", method = RequestMethod.POST)
	public String saveRole(Role role) {
		repository.save(role);
		return "redirect:/employees";

	}
	
	@RequestMapping(path = "/role/update/{id}", method = RequestMethod.GET)
	public String editRole(Model model, @PathVariable(name = "id") Integer id) {
		Role role = repository.findById(id).orElse(null);
		model.addAttribute("roles", role);
		return "editrole";
	}
	

}
