package com.leave.system.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.leave.system.javabean.UserSession;
import com.leave.system.model.Employee;
import com.leave.system.model.LeaveView;
import com.leave.system.model.Leavedetail;
import com.leave.system.repository.EmployeeRepository;
import com.leave.system.repository.LeaveRepository;

import net.bytebuddy.asm.Advice.Return;

@Controller
@RequestMapping("/staff")
@SessionAttributes("session")
public class StaffController {

	private EmployeeRepository eRepo;
	private LeaveRepository lRepo;

	@Autowired
	public void seteRepo(EmployeeRepository eRepo) {
		this.eRepo = eRepo;
	}

	@Autowired
	public void setlRepo(LeaveRepository lRepo) {
		this.lRepo = lRepo;
	}

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String getIndex() {
		return "staff/index";
	}

	@RequestMapping(path = "/create", method = RequestMethod.GET)
	public String getLeave(Model model) {
		model.addAttribute("leavedetail", new Leavedetail());
		return "staff/createleave";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createLeave(@Validated Leavedetail leavedetails,HttpSession session,  BindingResult bindingResult, Model model) {
		UserSession userSession = (UserSession) session.getAttribute("US");

		
		if (leavedetails.getStartDate() == null && leavedetails.getEndDate() ==null) {
			bindingResult.rejectValue("startDate", "error.leavedetails", "Start date cannot be empty");
		}
		if (leavedetails.getStartDate().isBefore(LocalDate.now())) {
			bindingResult.rejectValue("startDate", "error.leavedetails", "*You cannot backdate your leave!");
		}
		if (leavedetails.getEndDate().isBefore(leavedetails.getStartDate())) {
			bindingResult.rejectValue("endDate", "error.leavedetails", "*Please check your date time");
		}

		if (bindingResult.hasErrors()) {
			return "staff/createleave";
		} 
			int employee = userSession.getEmployee().getId();
			leavedetails.setStatus("Applied");
			leavedetails.setEmployee(userSession.getEmployee());
			
			model.addAttribute("staffid", employee);
			lRepo.saveAndFlush(leavedetails);
				
			return "redirect:/staff/";
		
	}


}