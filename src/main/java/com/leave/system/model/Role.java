package com.leave.system.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private int annualleave;
	private int medicalleave;
	@OneToMany(targetEntity = Employee.class, mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<Employee> employee;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String title, int annual, int medical) {
		super();
		this.title = title;
		this.annualleave = annual;
		this.medicalleave = medical;
	}

	/**
	 * @return the annualleave
	 */
	public int getAnnualleave() {
		return annualleave;
	}

	/**
	 * @param annualleave the annualleave to set
	 */
	public void setAnnualleave(int annualleave) {
		this.annualleave = annualleave;
	}

	/**
	 * @return the medicalleave
	 */
	public int getMedicalleave() {
		return medicalleave;
	}

	/**
	 * @param medicalleave the medicalleave to set
	 */
	public void setMedicalleave(int medicalleave) {
		this.medicalleave = medicalleave;
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
		return "Role [id=" + id + ", " + (title != null ? "title=" + title + ", " : "") + "annualleave=" + annualleave
				+ ", medicalleave=" + medicalleave + ", " + (employee != null ? "employee=" + employee : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
