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
@Table(name = "servicearea_others")
public class ServiceAreaOthers implements Serializable {

	private static final long serialVersionUID = -7238148752498097671L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "dental_procedures")
	private Long dental_procedures;
	
	@Column(name = "operatn_theatre")
	private Long operatnTheatre;
	
	@Column(name = "blood_bank")
	private Long bloodBank;
	
	@Column(name = "bloodstorage_unit")
	private Long bloodstorageUnit;

	@Column(name = "ambulance")
	private Long ambulance;
	
	@Column(name = "medical_icu")
	private Boolean medicalIcu;
	
	@Column(name = "medical_icu_count")
	private Long medicalIcuCount;
	
	@Column(name = "xrayunitsCount")
	private Long xrayunitsCount;
	
	@Column(name = "montly_xraycount")
	private Long montlyXrayCount;
	
	@Column(name = "anti_natal_count")
	private Long antiNatalCount;
	
	@Column(name = "normal_delivery_count")
	private Long normalDeliveryCount;
	
	@Column(name = "lscs_count")
	private Long lscsCount;
	
	@Column(name = "maternal_death_reasn")
	private Long maternalDeathReasn;
	
	@Column(name = "drugs_availability")
	private Long drugsAvailability;
	
	@Column(name = "critical_shortage_details")
	private Long criticalShortageDetails;
	
	@Column(name = "arv_availability")
	private Boolean arvAvailability;
	
	@Column(name = "asv_availability")
	private Long asvAvailability;
	
	@Column(name = "last_hmc_meeting")
	private Date lastHmcMeeting;
	
	@Column(name = "work_status")
	private String workStatus;
	
	@Column(name = "work_nature")
	private String work_nature;
	
	@Column(name = "work_start_date")
	private Date work_start_date;

	@Column(name = "idlingmajor_equipmt")
	private String idlingMajorEquipmt;
	
	@Column(name = "relevant_issues")
	private String relevantIssues;
	
	@Column(name = "signed_by")
	private String signedBy;

	private HospitalMonthlyTracker hospitalMonthlyTracker;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDental_procedures() {
		return dental_procedures;
	}

	public void setDental_procedures(Long dental_procedures) {
		this.dental_procedures = dental_procedures;
	}

	public Long getOperatnTheatre() {
		return operatnTheatre;
	}

	public void setOperatnTheatre(Long operatnTheatre) {
		this.operatnTheatre = operatnTheatre;
	}

	public Long getBloodBank() {
		return bloodBank;
	}

	public void setBloodBank(Long bloodBank) {
		this.bloodBank = bloodBank;
	}

	public Long getBloodstorageUnit() {
		return bloodstorageUnit;
	}

	public void setBloodstorageUnit(Long bloodstorageUnit) {
		this.bloodstorageUnit = bloodstorageUnit;
	}

	public Long getAmbulance() {
		return ambulance;
	}

	public void setAmbulance(Long ambulance) {
		this.ambulance = ambulance;
	}

	public Boolean getMedicalIcu() {
		return medicalIcu;
	}

	public void setMedicalIcu(Boolean medicalIcu) {
		this.medicalIcu = medicalIcu;
	}

	public Long getMedicalIcuCount() {
		return medicalIcuCount;
	}

	public void setMedicalIcuCount(Long medicalIcuCount) {
		this.medicalIcuCount = medicalIcuCount;
	}

	public Long getXrayunitsCount() {
		return xrayunitsCount;
	}

	public void setXrayunitsCount(Long xrayunitsCount) {
		this.xrayunitsCount = xrayunitsCount;
	}

	public Long getMontlyXrayCount() {
		return montlyXrayCount;
	}

	public void setMontlyXrayCount(Long montlyXrayCount) {
		this.montlyXrayCount = montlyXrayCount;
	}

	public Long getAntiNatalCount() {
		return antiNatalCount;
	}

	public void setAntiNatalCount(Long antiNatalCount) {
		this.antiNatalCount = antiNatalCount;
	}

	public Long getNormalDeliveryCount() {
		return normalDeliveryCount;
	}

	public void setNormalDeliveryCount(Long normalDeliveryCount) {
		this.normalDeliveryCount = normalDeliveryCount;
	}

	public Long getLscsCount() {
		return lscsCount;
	}

	public void setLscsCount(Long lscsCount) {
		this.lscsCount = lscsCount;
	}

	public Long getMaternalDeathReasn() {
		return maternalDeathReasn;
	}

	public void setMaternalDeathReasn(Long maternalDeathReasn) {
		this.maternalDeathReasn = maternalDeathReasn;
	}

	public Long getDrugsAvailability() {
		return drugsAvailability;
	}

	public void setDrugsAvailability(Long drugsAvailability) {
		this.drugsAvailability = drugsAvailability;
	}

	public Long getCriticalShortageDetails() {
		return criticalShortageDetails;
	}

	public void setCriticalShortageDetails(Long criticalShortageDetails) {
		this.criticalShortageDetails = criticalShortageDetails;
	}

	public Boolean getArvAvailability() {
		return arvAvailability;
	}

	public void setArvAvailability(Boolean arvAvailability) {
		this.arvAvailability = arvAvailability;
	}

	public Long getAsvAvailability() {
		return asvAvailability;
	}

	public void setAsvAvailability(Long asvAvailability) {
		this.asvAvailability = asvAvailability;
	}

	public Date getLastHmcMeeting() {
		return lastHmcMeeting;
	}

	public void setLastHmcMeeting(Date lastHmcMeeting) {
		this.lastHmcMeeting = lastHmcMeeting;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getWork_nature() {
		return work_nature;
	}

	public void setWork_nature(String work_nature) {
		this.work_nature = work_nature;
	}

	public Date getWork_start_date() {
		return work_start_date;
	}

	public void setWork_start_date(Date work_start_date) {
		this.work_start_date = work_start_date;
	}

	public String getIdlingMajorEquipmt() {
		return idlingMajorEquipmt;
	}

	public void setIdlingMajorEquipmt(String idlingMajorEquipmt) {
		this.idlingMajorEquipmt = idlingMajorEquipmt;
	}

	public String getRelevantIssues() {
		return relevantIssues;
	}

	public void setRelevantIssues(String relevantIssues) {
		this.relevantIssues = relevantIssues;
	}

	public String getSignedBy() {
		return signedBy;
	}

	public void setSignedBy(String signedBy) {
		this.signedBy = signedBy;
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
