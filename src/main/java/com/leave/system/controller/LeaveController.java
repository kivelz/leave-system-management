package com.leave.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;


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
	
//	@RequestMapping(path = "/leave", method = RequestMethod.GET)
//	public String getLeaveDetails(Model model, @PageableDefault(page = 0, value=10) Pageable pageable)
//	{
//		Page<Leavedetail> page = leaveRepository.findAll(pageable);
//		model.addAttribute("leavedetails", leaveRepository.findAll());
//		model.addAttribute("page", page);
//		return "leave";
//	}
	
	@RequestMapping(path = "/leave", method = RequestMethod.GET)
	  public String leavePage(HttpServletRequest request, Model model) {
	        
	        int page = 0; //default page number is 0 (yes it is weird)
	        int size = 5; //default page size is 10
	        
	        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }

	        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
	        
	        model.addAttribute("leavedetails", leaveRepository.findAll(PageRequest.of(page, size)));
	        return "leave";
	    }
	
	
}
