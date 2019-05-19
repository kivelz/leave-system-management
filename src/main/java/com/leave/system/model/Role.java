package com.leave.system.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	private String title;
	@OneToMany(targetEntity = Employee.class, mappedBy = "role")
	private Collection<Employee> employee;
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the employee
	 */
	public Collection<Employee> getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Collection<Employee> employee) {
		this.employee = employee;
	}


	@Override
	public String toString() {
		return "Role [id=" + id + ", " + (title != null ? "title=" + title + ", " : "")
				+ (employee != null ? "employee=" + employee + ", " : "") + "getId()=" + getId() + ", "
				+ (getTitle() != null ? "getTitle()=" + getTitle() + ", " : "")
				+ (getEmployee() != null ? "getEmployee()=" + getEmployee() + ", " : "") + "hashCode()=" + hashCode()
				+ ", " + (getClass() != null ? "getClass()=" + getClass() + ", " : "")
				+ (super.toString() != null ? "toString()=" + super.toString() : "") + "]";
	}
	
	
	

	

}
