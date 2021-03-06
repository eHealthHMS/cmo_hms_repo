package com.ehealth.hmms.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "hospital_monthlytracker")
public class HospitalMonthlyTracker implements Serializable{

	private static final long serialVersionUID = 5288682087185207627L;

	@Id 
	@SequenceGenerator(name="monthlytracker_sequence",sequenceName="monthlytracker_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="monthlytracker_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;

	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "report_date")
	private Date report_date;

	@Column(name = "final_submit_done")
	private Boolean finalSubmitDone;
	
	@Column(name = "last_modified")
	private Date lastModified;
	//cascade = CascadeType.ALL,
	@OneToOne( fetch=FetchType.EAGER)
	@JoinColumn(name = "hospital_id", referencedColumnName="gid")
	private HospitalMaster hospital;
	
	public Long getId() {
		return id;
	}

	public Long setId(Long id) {
		return this.id = id;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "gid")
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

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getFinalSubmitDone() {
		return finalSubmitDone;
	}

	public void setFinalSubmitDone(Boolean finalSubmitDone) {
		this.finalSubmitDone = finalSubmitDone;
	}

	
	
}
