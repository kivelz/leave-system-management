package com.leave.system.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.leave.system.javabean.UserSession;
import com.leave.system.model.Employee;
import com.leave.system.model.Leavedetail;
import com.leave.system.model.Role;
import com.leave.system.repository.EmployeeRepository;
import com.leave.system.repository.LeaveRepository;
import com.leave.system.service.ManagerSvc;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import ch.qos.logback.core.util.Duration;

@Controller
@RequestMapping("/staff")
@SessionAttributes("session")
public class StaffController {

	private EmployeeRepository eRepo;
	@Autowired
	private LeaveRepository lRepo;

	@Autowired
	public void seteRepo(EmployeeRepository eRepo) {
		this.eRepo = eRepo;
	}

	@Autowired
	public void setlRepo(LeaveRepository lRepo) {
		this.lRepo = lRepo;
	}

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
	public String createLeave(@Validated Leavedetail leavedetails, HttpSession session, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		UserSession userSession = (UserSession) session.getAttribute("US");

		if (leavedetails.getStartDate() != null && leavedetails.getEndDate() != null) {
			leavedetails.setStartDate(leavedetails.getStartDate().plusDays(1));
			leavedetails.setEndDate(leavedetails.getEndDate().plusDays(1));
		}
		if (leavedetails.getStartDate().isBefore(LocalDate.now())) {
			bindingResult.rejectValue("startDate", "error.leavedetails", "*You cannot backdate your leave!");
		}
		if (leavedetails.getEndDate().isBefore(leavedetails.getStartDate())) {
			bindingResult.rejectValue("endDate", "error.leavedetails",
					"*Your end date cannot be eariler than start date");
		}

		if (bindingResult.hasErrors()) {
			return "staff/createleave";

		}
		redirectAttributes.addFlashAttribute("message", "Leave successfully applied");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		int employee = userSession.getEmployee().getId();
		leavedetails.setStatus("Applied/Updated");
		leavedetails.setEmployee(userSession.getEmployee());

		model.addAttribute("staffid", employee);
		lRepo.save(leavedetails);

		return "redirect:/staff/history";

	}

	@RequestMapping(path = "/history", method = RequestMethod.GET)
	public String ViewHistory(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		UserSession sess = (UserSession) session.getAttribute("US");
		if (sess.getEmployee() != null) {
			ArrayList<Leavedetail> list = new ArrayList<>();
			ArrayList<Leavedetail> pending = new ArrayList<>();
			String username = sess.getEmployee().getName();
			List<Leavedetail> gethistory = lRepo.findAll();

			for (Leavedetail leave : gethistory) {
				if (leave.getEmployee().getId() == sess.getEmployee().getId()
						&& !leave.getStatus().contentEquals("Applied/Updated")) {
					list.add(leave);
				}
			}
			for (Leavedetail leave : gethistory) {
				if (leave.getEmployee().getId() == sess.getEmployee().getId()
						&& leave.getStatus().contentEquals("Applied/Updated")) {
					pending.add(leave);
				}
			}
			model.addAttribute("pending", pending);
			model.addAttribute("history", list);
			model.addAttribute("username", username);

			return "staff/history";
		}
		return "redirect:/home/login";
	}

	@RequestMapping(path = "/leave/{id}", method = RequestMethod.GET)
	public String EditLeaveDetails(Model model, @PathVariable(name = "id") Integer id) {
		Leavedetail leavedetail = lRepo.findById(id).orElse(null);
		model.addAttribute("leavedetail", leavedetail);
		return "staff/editleavedetail";
	}

	@RequestMapping(path = "/leave/{id}", method = RequestMethod.POST)
	public String saveLeaveDetails(Leavedetail leavedetail, @PathVariable(name = "id") Integer id, Model model,
			BindingResult bindingResult) {

		if (leavedetail.getStartDate() != null && leavedetail.getEndDate() != null) {
			leavedetail.setStartDate(leavedetail.getStartDate().plusDays(1));
			leavedetail.setEndDate(leavedetail.getEndDate().plusDays(1));
		}

		if (leavedetail.getStartDate().isBefore(LocalDate.now())) {
			bindingResult.rejectValue("startDate", "error.leavedetails", "*You cannot backdate your leave!");
		}
		if (leavedetail.getEndDate().isBefore(leavedetail.getStartDate())) {
			bindingResult.rejectValue("endDate", "error.leavedetails",
					"*Your end date cannot be eariler than start date");
		}

		if (bindingResult.hasErrors()) {
			return "staff/createleave";

		}
		lRepo.save(leavedetail);
		return "redirect:/staff/history";
	}

	@RequestMapping(path = "/leave/delete/{id}", method = RequestMethod.GET)
	public String deleteLeaveDetails(Leavedetail leavedetail, @PathVariable(name = "id") Integer id, Model model) {
		lRepo.delete(leavedetail);
		return "redirect:/staff/history";
	}

	// Finding Employee on leave on given period
	
	
	public ArrayList<Employee> getAllStaff(int Id) {
		ArrayList<Employee> emp = new ArrayList<>();
		
		emp = eRepo.findAllEmployees();
		
		return emp;
	}

}