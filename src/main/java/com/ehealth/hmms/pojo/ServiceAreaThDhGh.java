package com.ehealth.hmms.pojo;

import java.io.Serializable;

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
@Table(name = "service_area_th_gh_dh")
public class ServiceAreaThDhGh implements Serializable {

	private static final long serialVersionUID = -6025035600436375570L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "tot_out_patients")
	private Long totOutPatients;
	
	@Column(name = "tot_in_patients")
	private Long totInPatients;

	@Column(name = "patients_discharged")
	private Long patientsDischarged;
	
	@Column(name = "patients_expired")
	private Long patientsExpired;
	
	@Column(name = "patients_referred")
	private Long patientsReferred;

	@Column(name = "med_emergency")
	private Long medEmergency;
	
	@Column(name = "surg_emergency")
	private Long surgEmergency;
	
	@Column(name = "emr_patinet_treated")
	private Long emrPatinetTreated;

	@Column(name = "emr_patient_admited")
	private Long emrPatientAdmited;
	
	@Column(name = "emr_patientreferred")
	private Long emrPatientReferred;

	@Column(name = "emergencycare")
	private Long emergencyCare;
	
	@Column(name = "emr_rta")
	private Long emrRta;
	
	@Column(name = "hmc_fund")
	private Long hmcFund;

	@Column(name = "nhm_fund")
	private Long nhmFund;
	
	@Column(name = "rsby_fund")
	private Long rsbyFund;
	
	@Column(name = "hmc_expenditure")
	private Long hmcExpenditure;

	@Column(name = "nhm_expenditure")
	private Long nhmExpenditure;

	@Column(name = "rsby_expenditure")
	private Long rsbyExpenditure;
	
	private HospitalMonthlyTracker hospitalMonthlyTracker;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTotOutPatients() {
		return totOutPatients;
	}

	public void setTotOutPatients(Long totOutPatients) {
		this.totOutPatients = totOutPatients;
	}

	public Long getTotInPatients() {
		return totInPatients;
	}

	public void setTotInPatients(Long totInPatients) {
		this.totInPatients = totInPatients;
	}

	public Long getPatientsDischarged() {
		return patientsDischarged;
	}

	public void setPatientsDischarged(Long patientsDischarged) {
		this.patientsDischarged = patientsDischarged;
	}

	public Long getPatientsExpired() {
		return patientsExpired;
	}

	public void setPatientsExpired(Long patientsExpired) {
		this.patientsExpired = patientsExpired;
	}

	public Long getPatientsReferred() {
		return patientsReferred;
	}

	public void setPatientsReferred(Long patientsReferred) {
		this.patientsReferred = patientsReferred;
	}

	public Long getMedEmergency() {
		return medEmergency;
	}

	public void setMedEmergency(Long medEmergency) {
		this.medEmergency = medEmergency;
	}

	public Long getSurgEmergency() {
		return surgEmergency;
	}

	public void setSurgEmergency(Long surgEmergency) {
		this.surgEmergency = surgEmergency;
	}

	public Long getEmrPatinetTreated() {
		return emrPatinetTreated;
	}

	public void setEmrPatinetTreated(Long emrPatinetTreated) {
		this.emrPatinetTreated = emrPatinetTreated;
	}

	public Long getEmrPatientAdmited() {
		return emrPatientAdmited;
	}

	public void setEmrPatientAdmited(Long emrPatientAdmited) {
		this.emrPatientAdmited = emrPatientAdmited;
	}

	public Long getEmrPatientReferred() {
		return emrPatientReferred;
	}

	public void setEmrPatientReferred(Long emrPatientReferred) {
		this.emrPatientReferred = emrPatientReferred;
	}

	public Long getEmergencyCare() {
		return emergencyCare;
	}

	public void setEmergencyCare(Long emergencyCare) {
		this.emergencyCare = emergencyCare;
	}

	public Long getEmrRta() {
		return emrRta;
	}

	public void setEmrRta(Long emrRta) {
		this.emrRta = emrRta;
	}

	public Long getHmcFund() {
		return hmcFund;
	}

	public void setHmcFund(Long hmcFund) {
		this.hmcFund = hmcFund;
	}

	public Long getNhmFund() {
		return nhmFund;
	}

	public void setNhmFund(Long nhmFund) {
		this.nhmFund = nhmFund;
	}

	public Long getRsbyFund() {
		return rsbyFund;
	}

	public void setRsbyFund(Long rsbyFund) {
		this.rsbyFund = rsbyFund;
	}

	public Long getHmcExpenditure() {
		return hmcExpenditure;
	}

	public void setHmcExpenditure(Long hmcExpenditure) {
		this.hmcExpenditure = hmcExpenditure;
	}

	public Long getNhmExpenditure() {
		return nhmExpenditure;
	}

	public void setNhmExpenditure(Long nhmExpenditure) {
		this.nhmExpenditure = nhmExpenditure;
	}

	public Long getRsbyExpenditure() {
		return rsbyExpenditure;
	}

	public void setRsbyExpenditure(Long rsbyExpenditure) {
		this.rsbyExpenditure = rsbyExpenditure;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	public HospitalMonthlyTracker getHospitalMonthlyTracker() {
		return hospitalMonthlyTracker;
	}

	public void setHospitalMonthlyTracker(HospitalMonthlyTracker hospitalMonthlyTracker) {
		this.hospitalMonthlyTracker = hospitalMonthlyTracker;
	}
}
