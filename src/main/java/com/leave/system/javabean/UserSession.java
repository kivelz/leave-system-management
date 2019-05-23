package com.leave.system.javabean;

import com.leave.system.model.Employee;
import com.leave.system.model.Role;

public class UserSession {
	private Employee employee;
	private Role role;
	
	
	public UserSession() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}
	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
