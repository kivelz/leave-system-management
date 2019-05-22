package com.leave.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leave.system.repository.LeaveRepository;

@Controller
public class LeaveController {

	private LeaveRepository leaveRepository;

	@Autowired
	public void setLeaveRepository(LeaveRepository leaveRepository) {
		this.leaveRepository = leaveRepository;
	}
	
	@RequestMapping(path = "/leave", method = RequestMethod.GET)
	public String getLeaveDetails(Model model)
	{
		model.addAttribute("leavedetails", leaveRepository.findAll());
		return "leave";
	}
	
	
}
