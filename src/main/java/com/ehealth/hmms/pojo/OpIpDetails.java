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

/**
 * @author Krishnapriya V S
 *
 */

@Entity
@Table(name = "op_ip_th_gh_dh")
public class OpIpDetails implements Serializable{

	private static final long serialVersionUID = 7609838762982956384L;

	@Id 
	@SequenceGenerator(name="op_ip_details_sequence",sequenceName="op_ip_details_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="op_ip_details_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name = "forenoon_op_male")
	private Long forenoonOpMale;
	
	@Column(name = "forenoon_op_female")
	private Long forenoonOpFemale;
	
	@Column(name = "forenoon_op_tg")
	private Long forenoonOpTg;
	
	@Column(name = "forenoon_op_total")
	private Long forenoonOpTotal;
	
	@Column(name = "afternoon_op_male")
	private Long afternoonOpMale;
	
	@Column(name = "afternoon_op_female")
	private Long afternoonOpFemale;
	
	@Column(name = "afternoon_op_tg")
	private Long afternoonOpTg;
	
	@Column(name = "afternoon_op_total")
	private Long afternoonOpTotal;
	
	@Column(name = "ip_patients_discharged")
	private Long ipPatientsDischarged;
	
	@Column(name = "ip_patients_expired")
	private Long ipPatientsExpired;
	
	@Column(name = "ip_patients_referred")
	private Long ipPatientsReferred;	
	
	@Column(name = "ip_admissions_male")
	private Long ipAdmissionsMale;
	
	@Column(name = "ip_admissions_female")
	private Long ipAdmissionsFemale;
	
	@Column(name = "ip_admissions_tg")
	private Long ipAdmissionsTg;
	
	@Column(name = "ip_admissions_total")
	private Long ipAdmissionsTotal;
	
	@Column(name = "emr_patient_admited")
	private Long emrPatientAdmited;
	
	@Column(name = "emr_patient_referred")
	private Long emrPatientReferred;
	
	@Column(name = "emr_rta_trauma")
	private Long emrRtaTrauma;
	
	@Column(name = "emr_patinet_attended")
	private Long emrPatinetAttended;

	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "hospmonthlytrack_id", referencedColumnName="id")
	private HospitalMonthlyTracker hospitalMonthlyTracker;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getForenoonOpMale() {
		return forenoonOpMale;
	}

	public void setForenoonOpMale(Long forenoonOpMale) {
		this.forenoonOpMale = forenoonOpMale;
	}

	public Long getForenoonOpFemale() {
		return forenoonOpFemale;
	}

	public void setForenoonOpFemale(Long forenoonOpFemale) {
		this.forenoonOpFemale = forenoonOpFemale;
	}

	public Long getForenoonOpTg() {
		return forenoonOpTg;
	}

	public void setForenoonOpTg(Long forenoonOpTg) {
		this.forenoonOpTg = forenoonOpTg;
	}

	public Long getForenoonOpTotal() {
		return forenoonOpTotal;
	}

	public void setForenoonOpTotal(Long forenoonOpTotal) {
		this.forenoonOpTotal = forenoonOpTotal;
	}

	public Long getAfternoonOpMale() {
		return afternoonOpMale;
	}

	public void setAfternoonOpMale(Long afternoonOpMale) {
		this.afternoonOpMale = afternoonOpMale;
	}

	public Long getAfternoonOpFemale() {
		return afternoonOpFemale;
	}

	public void setAfternoonOpFemale(Long afternoonOpFemale) {
		this.afternoonOpFemale = afternoonOpFemale;
	}

	public Long getAfternoonOpTg() {
		return afternoonOpTg;
	}

	public void setAfternoonOpTg(Long afternoonOpTg) {
		this.afternoonOpTg = afternoonOpTg;
	}

	public Long getAfternoonOpTotal() {
		return afternoonOpTotal;
	}

	public void setAfternoonOpTotal(Long afternoonOpTotal) {
		this.afternoonOpTotal = afternoonOpTotal;
	}

	public Long getIpPatientsDischarged() {
		return ipPatientsDischarged;
	}

	public void setIpPatientsDischarged(Long ipPatientsDischarged) {
		this.ipPatientsDischarged = ipPatientsDischarged;
	}

	public Long getIpPatientsExpired() {
		return ipPatientsExpired;
	}

	public void setIpPatientsExpired(Long ipPatientsExpired) {
		this.ipPatientsExpired = ipPatientsExpired;
	}

	public Long getIpPatientsReferred() {
		return ipPatientsReferred;
	}

	public void setIpPatientsReferred(Long ipPatientsReferred) {
		this.ipPatientsReferred = ipPatientsReferred;
	}

	public Long getIpAdmissionsMale() {
		return ipAdmissionsMale;
	}

	public void setIpAdmissionsMale(Long ipAdmissionsMale) {
		this.ipAdmissionsMale = ipAdmissionsMale;
	}

	public Long getIpAdmissionsFemale() {
		return ipAdmissionsFemale;
	}

	public void setIpAdmissionsFemale(Long ipAdmissionsFemale) {
		this.ipAdmissionsFemale = ipAdmissionsFemale;
	}

	public Long getIpAdmissionsTg() {
		return ipAdmissionsTg;
	}

	public void setIpAdmissionsTg(Long ipAdmissionsTg) {
		this.ipAdmissionsTg = ipAdmissionsTg;
	}

	public Long getIpAdmissionsTotal() {
		return ipAdmissionsTotal;
	}

	public void setIpAdmissionsTotal(Long ipAdmissionsTotal) {
		this.ipAdmissionsTotal = ipAdmissionsTotal;
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

	public Long getEmrRtaTrauma() {
		return emrRtaTrauma;
	}

	public void setEmrRtaTrauma(Long emrRtaTrauma) {
		this.emrRtaTrauma = emrRtaTrauma;
	}

	public Long getEmrPatinetAttended() {
		return emrPatinetAttended;
	}

	public void setEmrPatinetAttended(Long emrPatinetAttended) {
		this.emrPatinetAttended = emrPatinetAttended;
	}

	public HospitalMonthlyTracker getHospitalMonthlyTracker() {
		return hospitalMonthlyTracker;
	}

	public void setHospitalMonthlyTracker(HospitalMonthlyTracker hospitalMonthlyTracker) {
		this.hospitalMonthlyTracker = hospitalMonthlyTracker;
	}

}
