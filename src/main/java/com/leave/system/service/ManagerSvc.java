package com.leave.system.service;

import java.util.ArrayList;


import org.springframework.stereotype.Service;

import com.leave.system.model.Employee;
import com.leave.system.model.Leavedetail;
import com.leave.system.model.Role;



@Service
public class ManagerSvc {

	int annualL;
	int medicalL;
	int aConsumed;
	int mConsumed;
	int managerId;
	ArrayList<Employee> allEmployees;
	ArrayList<Employee> subordinates;
	ArrayList<Leavedetail> subLeave;
	ArrayList<Leavedetail> allLeave;
	
//	public ManagerSvc() {
//		super();
//		annualL = 0;
//		medicalL = 0;
//		aConsumed = 0;
//		mConsumed = 0;
//	}
	
	public ManagerSvc(int managerId, ArrayList<Leavedetail> allLeave, ArrayList<Employee> allEmployees) {
		super();
		this.managerId = managerId;
		this.allLeave = allLeave;
		this.allEmployees = allEmployees;
		
		for (Employee e: allEmployees) {
			
			if (e.getManagerid() == managerId) {
		
				for (Leavedetail l: allLeave) {
					
					if (l.getEmployee() == e) {
						subLeave.add(l);
					}
				}
			}
		}
		
		for (Employee e: allEmployees) {
			
			if (e.getManagerid() == managerId) {
				
				subordinates.add(e);
			}
		}
	}

	public Leavedetail getLeaveById(int id) {
		
		Leavedetail leave = new Leavedetail();
		for (Leavedetail l: subLeave) {
			if (l.getId() == id) {
				leave = l;
			}
		}
		
		return leave;
	}

	
	public Role getRoleWBal(Employee e) {
		
		annualL = 0;
		medicalL = 0;
		aConsumed = 0;
		mConsumed = 0;
		
		Role role = e.getRole();
		
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
					
			}
		}
	
		annualL = e.getRole().getAnnualleave();
		medicalL = e.getRole().getAnnualleave();
		annualL = annualL - aConsumed;
		medicalL = medicalL - mConsumed;
		
		role.setAnnualleave(annualL);
		role.setAnnualleave(medicalL);
		
		return role;
	}
	
	public Leavedetail checkValid(Leavedetail leave) {
		annualL = 0;
		medicalL = 0;
		aConsumed = 0;
		mConsumed = 0;
		
		Role roleWBal = getRoleWBal(leave.getEmployee());
		
		return leave;
	}
	
	public String checkValidMsg(Leavedetail leave, Role role) {
		
		String message = new String();
		
		if (leave.getStatus() == null) {
			leave.setStatus("Applied/Updated");
			message = "Invalid Submission. Please Select an Option.";
		}
		
		else if (leave.getCategory().equals("Annual Leave")  && leave.getStatus().equals("Approved") && role.getAnnualleave() > 0) {
			int annualL = role.getAnnualleave();
			role.setAnnualleave(annualL - 1);
			message = "Leave is Approved successfully";
		}
		
		else if (leave.getCategory().equals("Medical Leave") && leave.getStatus().equals("Approved") && medicalL > 0) {
			int medicalL = role.getAnnualleave();
			role.setAnnualleave(medicalL - 1);
			message = "Leave is Approved successfully";
		}
		
		else if (leave.getStatus().equals("Rejected") && leave.getComment().isEmpty()) {
			leave.setStatus("Applied/Updated");
			message = "Comment required for Rejection";
		}
		
		else if (leave.getStatus().equals("Rejected")) {
			message = "Leave is Rejected. Comment:" + leave.getComment();
		}
		
		
		return message;
	}
	
	public Boolean toSave(String message) {

		Boolean save = false;
		
		if (message == "Invalid Submission. Please Select an Option.") {
			save = false;
		}
		
		else if (message == "Leave is Approved successfully") {
			save = true;
		}
		
		else if (message == "Comment required for Rejection") {
			save = false;
		}

		return save;
	}
	
	public ArrayList<Leavedetail> getLeaveinRange(Leavedetail leave) {
		
		ArrayList<Leavedetail> leaveInRange = new ArrayList<>();
		
		for (Leavedetail l : subLeave) {
			if(leave.overlaps(l)) {
				leaveInRange.add(l);
			}
		}
		
		return leaveInRange;
	}
	
	public ArrayList<Employee> getSubordinates() {
		return subordinates;
	}

	public ArrayList<Leavedetail> getSubLeave() {
		return subLeave;
	}
}