package com.ehealth.hmms.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "fund_expenditure")
public class FundExpenditure implements Serializable{

	private static final long serialVersionUID = -8372548777949540310L;
	
	@Id 
	@SequenceGenerator(name="fund_expenditure_sequence",sequenceName="fund_expenditure_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="fund_expenditure_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;

	@Column(name = "fund_hmc")
	private Long fundHmc;
	
	@Column(name = "fund_nhm")
	private Long fundNhm;
	
	@Column(name = "fund_rsby")
	private Long fundRsby;
	
	@Column(name = "fund_dept_plan")
	private Long fundDeptPlan;
	
	@Column(name = "fund_lsgi")
	private Long fundLsgi;
	
	@Column(name = "exp_hmc")
	private Long expHmc;
	
	@Column(name = "exp_nhm")
	private Long expNhm;
	
	@Column(name = "exp_rsby")
	private Long expRsby;
	
	@Column(name = "exp_dept_plan")
	private Long expDeptPlan;
	
	@Column(name = "exp_lsgi")
	private Long expLsgi;
	
	@Column(name = "ongoing_construction")
	private Boolean ongoingConstruction;
	
	@Column(name = "progress_asper_plan")
	private Boolean progressAsperPlan;
	
	@Column(name = "delay_reason")
	private String delayReason;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "hospital_tracker_id", referencedColumnName="id")
	private HospitalMonthlyTracker hospitalMonthlyTracker;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFundHmc() {
		return fundHmc;
	}

	public void setFundHmc(Long fundHmc) {
		this.fundHmc = fundHmc;
	}

	public Long getFundNhm() {
		return fundNhm;
	}

	public void setFundNhm(Long fundNhm) {
		this.fundNhm = fundNhm;
	}

	public Long getFundRsby() {
		return fundRsby;
	}

	public void setFundRsby(Long fundRsby) {
		this.fundRsby = fundRsby;
	}

	public Long getFundDeptPlan() {
		return fundDeptPlan;
	}

	public void setFundDeptPlan(Long fundDeptPlan) {
		this.fundDeptPlan = fundDeptPlan;
	}

	public Long getFundLsgi() {
		return fundLsgi;
	}

	public void setFundLsgi(Long fundLsgi) {
		this.fundLsgi = fundLsgi;
	}

	public Long getExpHmc() {
		return expHmc;
	}

	public void setExpHmc(Long expHmc) {
		this.expHmc = expHmc;
	}

	public Long getExpNhm() {
		return expNhm;
	}

	public void setExpNhm(Long expNhm) {
		this.expNhm = expNhm;
	}

	public Long getExpRsby() {
		return expRsby;
	}

	public void setExpRsby(Long expRsby) {
		this.expRsby = expRsby;
	}

	public Long getExpDeptPlan() {
		return expDeptPlan;
	}

	public void setExpDeptPlan(Long expDeptPlan) {
		this.expDeptPlan = expDeptPlan;
	}

	public Long getExpLsgi() {
		return expLsgi;
	}

	public void setExpLsgi(Long expLsgi) {
		this.expLsgi = expLsgi;
	}

	public Boolean getOngoingConstruction() {
		return ongoingConstruction;
	}

	public void setOngoingConstruction(Boolean ongoingConstruction) {
		this.ongoingConstruction = ongoingConstruction;
	}

	public Boolean getProgressAsperPlan() {
		return progressAsperPlan;
	}

	public void setProgressAsperPlan(Boolean progressAsperPlan) {
		this.progressAsperPlan = progressAsperPlan;
	}

	public String getDelayReason() {
		return delayReason;
	}

	public void setDelayReason(String delayReason) {
		this.delayReason = delayReason;
	}

	public HospitalMonthlyTracker getHospitalMonthlyTracker() {
		return hospitalMonthlyTracker;
	}

	public void setHospitalMonthlyTracker(HospitalMonthlyTracker hospitalMonthlyTracker) {
		this.hospitalMonthlyTracker = hospitalMonthlyTracker;
	}

	
}
