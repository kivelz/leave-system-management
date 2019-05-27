package com.leave.system.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Leavedetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "start_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Please choose a start date")
	private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
	private LocalDate endDate;
	private String category;
	private String reason;
	@Column(name = "contact_detail")
	private String contactDetail;
	private String status;
	@ManyToOne
	@JoinColumn(name = "staffId")
	private Employee employee;
	private String comment;
	
	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Leavedetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}



	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}



	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}


	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}



	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}



	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}



	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}



	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}



	/**
	 * @return the contactDetail
	 */
	public String getContactDetail() {
		return contactDetail;
	}



	/**
	 * @param contactDetail the contactDetail to set
	 */
	public void setContactDetail(String contactDetail) {
		this.contactDetail = contactDetail;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}



	public Leavedetail(LocalDate startDate, LocalDate endDate, String category, String reason, String contactDetail,
			String status) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
		this.reason = reason;
		this.contactDetail = contactDetail;
		this.status = status;
	}


	@Override
	public String toString() {
		return "Leavedetail [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", category=" + category
				+ ", reason=" + reason + ", contactDetail=" + contactDetail + ", status=" + status + ", employee="
				+ employee + ", comment=" + comment + "]";
	}


	



	
}
