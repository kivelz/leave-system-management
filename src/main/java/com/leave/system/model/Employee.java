package com.leave.system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;



@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String userid;

	private String password;
	@Email
	String email;
	private Integer managerid;
	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private Role role;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String name, String userid, String password, String email, Integer managerid, Role role) {
		super();
		this.name = name;
		this.userid = userid;
		this.password = password;
		this.email = email;
		this.managerid = managerid;
		this.role = role;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the managerid
	 */
	public Integer getManagerid() {
		return managerid;
	}

	/**
	 * @param managerid the managerid to set
	 */
	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
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
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", " + (name != null ? "name=" + name + ", " : "")
				+ (userid != null ? "userid=" + userid + ", " : "")
				+ (password != null ? "password=" + password + ", " : "")
				+ (email != null ? "email=" + email + ", " : "")
				+ (managerid != null ? "managerid=" + managerid + ", " : "") + (role != null ? "role=" + role : "")
				+ "]";
	}

	

}
