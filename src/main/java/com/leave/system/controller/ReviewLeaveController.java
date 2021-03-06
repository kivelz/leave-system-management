package com.leave.system.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.leave.system.repository.EmployeeRepository;
import com.leave.system.repository.LeaveRepository;
import com.leave.system.service.ManagerSvc;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.leave.system.javabean.UserSession;
import com.leave.system.model.Employee;
import com.leave.system.model.LeaveRecords;
import com.leave.system.model.Leavedetail;
import com.leave.system.model.Role;



@Controller
@SessionAttributes("session")
@RequestMapping("/manager")
public class ReviewLeaveController {

	private LeaveRepository lvRepo;
	private EmployeeRepository empRepo;
	
	@Autowired
	public void setLeaveRepository(LeaveRepository leaveRepository) {
		this.lvRepo = leaveRepository;
	}
	
	@Autowired
	private void setEmployeeRepository(EmployeeRepository empRepository) {
		this.empRepo = empRepository;
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	  public String leavePage(HttpServletRequest request, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
	        
		UserSession us = (UserSession) session.getAttribute("US");
		if(us.getEmployee().getRole().getId() == 2) {
			String url = "/manager/viewsubLeave?managerId" + us.getEmployee().getId();	
			model.addAttribute("url1", url);
		        return "leave/leave";	        	
		}
	   	redirectAttributes.addFlashAttribute("message", "You don't have authorization");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return "redirect:/home/login";
	    }
	
	@RequestMapping(path = "/leave/{id}", method = RequestMethod.GET)
	public String EditLeaveDetails(Model model, @PathVariable(name = "id") Integer id) {
		Leavedetail leavedetail = lvRepo.findById(id).orElse(null);
		model.addAttribute("leavedetail", leavedetail);
		return "leave/editleavedetail";
	}
	
	@RequestMapping(path = "/leave/{id}", method = RequestMethod.POST)
	public String saveLeaveDetails(Leavedetail leavedetail,  @PathVariable(name = "id") Integer id, Model model) {
		lvRepo.save(leavedetail);
		return "redirect:/manager/";
	}
	
	@RequestMapping(path="/subView", method = RequestMethod.GET)
	public String ViewSubordinates(HttpSession session, Model model, RedirectAttributes redirectAttributes) { 
		
		UserSession userSession = (UserSession) session.getAttribute("US");
		int managerId = userSession.getEmployee().getId();
		
		if(userSession.getEmployee().getRole().getId() == 2) {
			
			model.addAttribute("Leaverecords", new LeaveRecords());
			String managername = userSession.getEmployee().getName();
			
			List<Employee> allEmployees = empRepo.findAll();
			ArrayList<Employee> subordinates = new ArrayList<Employee>();
			List<Leavedetail> allLeave = lvRepo.findAll();
			
			ManagerSvc mSvc = new ManagerSvc(managerId, allLeave, allEmployees);
			subordinates = mSvc.getSubordinates();
			
			model.addAttribute("managername", managername);
			model.addAttribute("subordinates", subordinates);
			
			return "manager/subView";
		}
		redirectAttributes.addFlashAttribute("message", "You don't have authorization");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return "redirect:/home/login";
		
	}
	
	@RequestMapping(path="/viewsubleave", method = RequestMethod.GET)
	public String ViewApplications(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		
		UserSession userSession = (UserSession) session.getAttribute("US");
		int managerId = userSession.getEmployee().getId();
		
		if(userSession.getEmployee().getRole().getId() == 2) {
			model.addAttribute("Leaverecords", new LeaveRecords());
			String managername = userSession.getEmployee().getName();
			List<Employee> allEmployees = empRepo.findAll();
			ArrayList<Employee> subordinates = new ArrayList<Employee>();
			List<Leavedetail> allLeave = lvRepo.findAll();
			ArrayList<Leavedetail> subLeave = new ArrayList<>();
			
			int annualL = 0;
			int medicalL = 0;
			int aConsumed = 0;
			int mConsumed = 0;

			for (Employee e: allEmployees) {
				
				if (e.getManagerid() == managerId) {

					for (Leavedetail l: allLeave) {
						
							if (l.getEmployee() == e) {

								if(l.getStatus().equals("Approved")) {
									
									if (l.getCategory().equals("Annual Leave")) {
										aConsumed++;
										}
									else if(l.getCategory().equals("Medical Leave")) {
										mConsumed++;
										}
									
								}
								
								if (l.getStatus().equals("Applied/Updated")) {
									subLeave.add(l);
								}
							
							}
							
						}
					
					annualL = e.getRole().getAnnualleave();
					medicalL = e.getRole().getMedicalleave();
					annualL = annualL - aConsumed;
					medicalL = medicalL - mConsumed;
					
					e.getRole().setAnnualleave(annualL);
					e.getRole().setMedicalleave(medicalL);
					subordinates.add(e);
				}
			}
			model.addAttribute("managername", managername);
			model.addAttribute("subordinates", subordinates);
			model.addAttribute("subleave", subLeave);
			
			return "manager/viewSubLeaveApplications";
		}
		redirectAttributes.addFlashAttribute("message", "You don't have authorization");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return "redirect:/home/login";
	}
	
	@RequestMapping(path="/viewIndiRec", method = RequestMethod.GET)
	public String IndiRec(@RequestParam Integer employeeId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		UserSession us =(UserSession) session.getAttribute("US");
		
		int managerId = us.getEmployee().getId();
		List<Employee> allEmployees = empRepo.findAll();
		List<Leavedetail> allLeave = lvRepo.findAll();
		
		if(us.getEmployee().getRole().getId() == 2) {

			ManagerSvc mSvc = new ManagerSvc(managerId, allLeave, allEmployees);
			Employee emp = mSvc.getStaffbyId(employeeId);
			ArrayList<Leavedetail> empLeave = mSvc.getSubLeave(emp);

			int annualL = 0;
			int medicalL = 0;
			int aConsumed = 0;
			int mConsumed = 0;

			for (Leavedetail l: empLeave) {
					
					if(l.getStatus().equals("Approved")) {
						
						if (l.getCategory().equals("Annual Leave")) {
							aConsumed = aConsumed + 1;
						}
						else if(l.getCategory().equals("Medical Leave")) {
							mConsumed = mConsumed +1;
						}
					
						annualL = emp.getRole().getAnnualleave();
						medicalL = emp.getRole().getMedicalleave();
						annualL = annualL - aConsumed;
						medicalL = medicalL - mConsumed;
					}
			}
			
			Role role = emp.getRole();
			role.setAnnualleave(annualL);
			role.setMedicalleave(medicalL);
			
			model.addAttribute("role", role);
			model.addAttribute("employee", emp);
			model.addAttribute("empLeave", empLeave);
			
			return "manager/viewIndiRec";
		}
		redirectAttributes.addFlashAttribute("message", "You don't have authorization");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		return "redirect:/home/login";
	}
	
	
	@RequestMapping(path="/viewIndiLeaveDetails", method = RequestMethod.GET)
	public String LeaveDetails(Integer leaveId, Model model, String message, HttpSession session, RedirectAttributes redirectAttributes) {
		UserSession us =(UserSession) session.getAttribute("US");
		
		int managerId = us.getEmployee().getId();
		List<Employee> allEmployees = empRepo.findAll();
		List<Leavedetail> allLeave = lvRepo.findAll();
		
		if(us.getEmployee().getRole().getId() == 2) {
			String msg = message;
			
			Leavedetail leave = lvRepo.findById(leaveId).orElse(null);
			Employee subordinate = empRepo.findById(leave.getEmployee().getId()).get();

			int annualL = 0;
			int medicalL = 0;
			int aConsumed = 0;
			int mConsumed = 0;

			for (Leavedetail l: allLeave) {
				if (subordinate.getId() == l.getEmployee().getId()) {
					
					if(l.getStatus().equals("Approved")) {
						
						if (l.getCategory().equals("Annual Leave")) {
							aConsumed = aConsumed + 1;
						}
						else if(l.getCategory().equals("Medical Leave")) {
							mConsumed = mConsumed +1;
						}
					
						annualL = subordinate.getRole().getAnnualleave();
						medicalL = subordinate.getRole().getMedicalleave();
						annualL = annualL - aConsumed;
						medicalL = medicalL - mConsumed;
					}
				}
			}
			
			ManagerSvc mSvc = new ManagerSvc(managerId, allLeave, allEmployees);
			
			ArrayList<Leavedetail> leaveinRange = mSvc.getOtherLeaveinRange(leave);
			ArrayList<Employee> subordinates = mSvc.getEmpInLeaveRecs(leaveinRange);
			
			Role role = subordinate.getRole();
			role.setAnnualleave(annualL);
			role.setMedicalleave(medicalL);
			
			model.addAttribute("role", role);
			model.addAttribute("subordinate", subordinate);
			model.addAttribute("subordinates", subordinates);
			model.addAttribute("leaveDetail", leave);
			model.addAttribute("message", msg);
			model.addAttribute("leaveinRange", leaveinRange);
			
			return "manager/individualLeaveDetails";
		}
		redirectAttributes.addFlashAttribute("message", "You don't have authorization");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		return "redirect:/home/login";
	}
	
	@RequestMapping(path="/submitreview", method = RequestMethod.POST)
	public String SubmitReview(Leavedetail leaveDetail,Model model,RedirectAttributes redirectA) {
		Employee subordinate = empRepo.findById(leaveDetail.getEmployee().getId()).get();
		String message = new String();
		Role role = new Role();
		
		List<Leavedetail> allLeave = lvRepo.findAll();
		List<Employee> allEmployees = empRepo.findAll();

		int annualL = 0;
		int medicalL = 0;
		int aConsumed = 0;
		int mConsumed = 0;

		for (Leavedetail l: allLeave) {
			if (subordinate.getId() == l.getEmployee().getId()) {
				
				if(l.getStatus().equals("Approved")) {
					
					if (l.getCategory().equals("Annual Leave")) {
						aConsumed = aConsumed + 1;
					}
					else if(l.getCategory().equals("Medical Leave")) {
						mConsumed = mConsumed +1;
					}
				
					annualL = subordinate.getRole().getAnnualleave();
					medicalL = subordinate.getRole().getMedicalleave();
					annualL = annualL - aConsumed;
					medicalL = medicalL - mConsumed;
					
				}
			}
		}
		
		
		if (leaveDetail.getStatus() == null) {
			leaveDetail.setStatus("Applied/Updated");
			message = "Invalid Submission. Please Select an Option.";
		}
		
		else if (leaveDetail.getCategory().equals("Annual Leave")  && leaveDetail.getStatus().equals("Approved") && annualL > 0) {
			annualL--;
			lvRepo.save(leaveDetail);
			message = "Leave is Approved successfully";
		}
		
		else if (leaveDetail.getCategory().equals("Medical Leave") && leaveDetail.getStatus().equals("Approved") && medicalL > 0) {
			medicalL--;
			lvRepo.save(leaveDetail);
			message = "Leave is Approved successfully";
		}
		
		else if (leaveDetail.getStatus().equals("Rejected") && leaveDetail.getComment().isEmpty()) {
			leaveDetail.setStatus("Applied/Updated");
			message = "Comment required for Rejection";
		}
		
		else if (leaveDetail.getStatus().equals("Rejected")) {
			lvRepo.save(leaveDetail);
			message = "Leave is Rejected. Comment:" + leaveDetail.getComment();
		}
		role = subordinate.getRole();
		
		role.setAnnualleave(annualL);
		role.setMedicalleave(medicalL);
		
		String url = "/viewsubleave?managerId=" + subordinate.getManagerid();
		
		ManagerSvc mansvc = new ManagerSvc(leaveDetail.getEmployee().getManagerid(), allLeave, allEmployees);
		ArrayList<Leavedetail> leaveinRange = mansvc.getOtherLeaveinRange(leaveDetail);
		ArrayList<Employee> subordinates = mansvc.getEmpInLeaveRecs(leaveinRange);
		
		role = subordinate.getRole();
		role.setAnnualleave(annualL);
		role.setMedicalleave(medicalL);
		
		model.addAttribute("role", role);
		model.addAttribute("subordinate", subordinate);
		model.addAttribute("subordinates", subordinates);
		model.addAttribute("leaveinRange", leaveinRange);
		model.addAttribute("leaveDetail", leaveDetail);
		model.addAttribute("message", message);
		model.addAttribute("url",url);
		
		
		int val = leaveDetail.getId();
		model.addAttribute("leaveId", val);
		return "manager/individualLeaveDetails";
	}
	
	@RequestMapping(path="/returnSubLeave", method = RequestMethod.POST)
	public String ReturnSubLeave(Leavedetail leavedetail,RedirectAttributes redirectA) {

		int val = leavedetail.getEmployee().getManagerid();		
		redirectA.addAttribute("managerId", val);
		return "redirect:/viewsubleave";
	}

	
	//Deprecated
	@RequestMapping(path="/submitBatchReview", method = RequestMethod.POST)
	public String SubmitBatchReview(LeaveRecords leaverecords,RedirectAttributes redirectA) {
		
		Iterator<Leavedetail> iterL = leaverecords.getLeavelist().iterator();
		
		while (iterL.hasNext()) {
			lvRepo.save(iterL.next());
		}
		
		int val = leaverecords.getLeavelist().get(0).getEmployee().getManagerid();		
		redirectA.addAttribute("managerId", val);
		return "redirect:/viewsubleave";
	}
	
	@RequestMapping(path = "/findleave", method = RequestMethod.GET)
	public String findEmpOnLeave(HttpSession session, Model model,RedirectAttributes redirectAttributes) {
		UserSession us = (UserSession) session.getAttribute("US"); 
		if(us.getEmployee().getRole().getId() == 2) {
			int id = us.getEmployee().getId();
			model.addAttribute("id", id);
			model.addAttribute("leaves", new Leavedetail());
			return "manager/findleave";
		}
		redirectAttributes.addFlashAttribute("message", "You don't have authorization");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		return "redirect:/home/login";
	}

	@RequestMapping(path = "/EmpOnLeave", method = RequestMethod.GET)
	public String FindEmpOnLeave(HttpSession session, Model model, Leavedetail leave, RedirectAttributes redirectAttributes) {
		UserSession us = (UserSession) session.getAttribute("US");
		int id = us.getEmployee().getId();
		if(us.getEmployee().getRole().getId() == 2) {
			List<Leavedetail> lea = lvRepo.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(leave.getStartDate(),
					leave.getEndDate());
			for (Leavedetail l : lea) {
				System.out.println(l);
			}
			model.addAttribute("id", id);
			model.addAttribute("leaves", lvRepo
					.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(leave.getStartDate(), leave.getEndDate()));
			return "manager/leaves";
		}
		redirectAttributes.addFlashAttribute("message", "You don't have authorization");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		return "redirect:/home/login";
	}

	// Export Products to CSV file
	@GetMapping("/leave/export")
	public void exportCSV(HttpServletResponse response) throws Exception {
		
		String filename = "LeaveList.csv";

		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

		// create a csv writer
		StatefulBeanToCsv<Leavedetail> writer = new StatefulBeanToCsvBuilder<Leavedetail>(response.getWriter())
				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR)
				.withOrderedResults(false).build();

		writer.write(lvRepo.findAll());
	}
	
}
