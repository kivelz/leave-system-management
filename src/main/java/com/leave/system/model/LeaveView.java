package com.leave.system.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LeaveView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate startDate;
	private LocalDate endDate;
	private String category;
	private String reason;
	private String contactDetail;
	private String workdis;
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
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
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
	 * @return the workdis
	 */
	public String getWorkdis() {
		return workdis;
	}
	/**
	 * @param workdis the workdis to set
	 */
	public void setWorkdis(String workdis) {
		this.workdis = workdis;
	}
	@Override
	public String toString() {
		return "LeaveView [id=" + id + ", " + (startDate != null ? "startDate=" + startDate + ", " : "")
				+ (endDate != null ? "endDate=" + endDate + ", " : "")
				+ (category != null ? "category=" + category + ", " : "")
				+ (reason != null ? "reason=" + reason + ", " : "")
				+ (contactDetail != null ? "contactDetail=" + contactDetail + ", " : "")
				+ (workdis != null ? "workdis=" + workdis : "") + "]";
	}
	
	
}
