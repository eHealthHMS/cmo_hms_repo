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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "servicearea_others")
public class ServiceAreaOthers implements Serializable {

	private static final long serialVersionUID = -7238148752498097671L;


	@Id 
	@SequenceGenerator(name="servicearea_others_sequence",sequenceName="servicearea_others_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="servicearea_others_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;

	@Column(name = "dental_procedures")
	private Long dentalProcedures;
	
	@Column(name = "og_normal_delivery_count")
	private Long ogNormalDeliveryCount;
	
	@Column(name = "og_lscs_count")
	private Long ogLscsCount;
	
	@Column(name = "og_maternal_death_count")
	private Long ogMaternalDeathCount;

	@Column(name = "drugs_availability")
	private Long drugsAvailability;
	
	@Column(name = "critical_shortage_details")
	private Long criticalShortageDetails;
	
	@Column(name = "last_hmc_meeting")
	private Date lasthmcMeeting;

	@Column(name = "ambulance")
	private Boolean ambulance;
	
	@Column(name = "og_referred_cases_count")
	private Long ogReferredCasesCount;
	
	@Column(name = "major_surgery_count")
	private Long majorSurgeryCount;
	
	@Column(name = "sc_high_dependency_unit")
	private Boolean scHighDependencyUnit;
	
	@Column(name = "sc_patients_treated_count")
	private Long scPatientsTreatedCount;
	
	@Column(name = "other_relevant_info")
	private String otherRelevantInfo;

	private HospitalMonthlyTracker hospitalMonthlyTracker;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDentalProcedures() {
		return dentalProcedures;
	}

	public void setDentalProcedures(Long dentalProcedures) {
		this.dentalProcedures = dentalProcedures;
	}

	public Long getOgNormalDeliveryCount() {
		return ogNormalDeliveryCount;
	}

	public void setOgNormalDeliveryCount(Long ogNormalDeliveryCount) {
		this.ogNormalDeliveryCount = ogNormalDeliveryCount;
	}

	public Long getOgLscsCount() {
		return ogLscsCount;
	}

	public void setOgLscsCount(Long ogLscsCount) {
		this.ogLscsCount = ogLscsCount;
	}

	public Long getOgMaternalDeathCount() {
		return ogMaternalDeathCount;
	}

	public void setOgMaternalDeathCount(Long ogMaternalDeathCount) {
		this.ogMaternalDeathCount = ogMaternalDeathCount;
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

	public Date getLasthmcMeeting() {
		return lasthmcMeeting;
	}

	public void setLasthmcMeeting(Date lasthmcMeeting) {
		this.lasthmcMeeting = lasthmcMeeting;
	}

	public Boolean getAmbulance() {
		return ambulance;
	}

	public void setAmbulance(Boolean ambulance) {
		this.ambulance = ambulance;
	}

	public Boolean getScHighDependencyUnit() {
		return scHighDependencyUnit;
	}

	public void setScHighDependencyUnit(Boolean scHighDependencyUnit) {
		this.scHighDependencyUnit = scHighDependencyUnit;
	}

	public Long getScPatientsTreatedCount() {
		return scPatientsTreatedCount;
	}

	public void setScPatientsTreatedCount(Long scPatientsTreatedCount) {
		this.scPatientsTreatedCount = scPatientsTreatedCount;
	}

	public String getOtherRelevantInfo() {
		return otherRelevantInfo;
	}

	public void setOtherRelevantInfo(String otherRelevantInfo) {
		this.otherRelevantInfo = otherRelevantInfo;
	}

	public Long getOgReferredCasesCount() {
		return ogReferredCasesCount;
	}

	public void setOgReferredCasesCount(Long ogReferredCasesCount) {
		this.ogReferredCasesCount = ogReferredCasesCount;
	}

	public Long getMajorSurgeryCount() {
		return majorSurgeryCount;
	}

	public void setMajorSurgeryCount(Long majorSurgeryCount) {
		this.majorSurgeryCount = majorSurgeryCount;
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
