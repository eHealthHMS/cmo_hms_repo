package com.ehealth.hmms.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hospital_monthlytracker")
public class HospitalMonthlyTracker implements Serializable{

	private static final long serialVersionUID = 5288682087185207627L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "created_date")
	private Long createdDate;
	
	@Column(name = "report_date")
	private Date report_date;
	
	@Column(name = "last_modified")
	private Date lastModified;
	
	private HospitalMaster hospital;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Long getReportMonth() {
//		return reportMonth;
//	}
//
//	public void setReportMonth(Long reportMonth) {
//		this.reportMonth = reportMonth;
//	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gid")
	public HospitalMaster getHospital() {
		return hospital;
	}

	public void setHospital(HospitalMaster hospital) {
		this.hospital = hospital;
	}

	/**
	 * @return the report_date
	 */
	public Date getReport_date() {
		return report_date;
	}

	/**
	 * @param report_date the report_date to set
	 */
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}

	
	
}
