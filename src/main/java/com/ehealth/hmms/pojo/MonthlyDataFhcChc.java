package com.ehealth.hmms.pojo;

import java.beans.Transient;
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
@Table(name = "monthlydata_fhc_chc")
public class MonthlyDataFhcChc implements Serializable{
	
	private static final long serialVersionUID = 6198698490528492269L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "forenoon_op")
	private Long forenoonOp;
	
	@Column(name = "afternoon_op")
	private Long afternoonOp;
	
	@Column(name = "total_precheck")
	private Long totalPrecheck;
	
	@Column(name = "total_postconsultncounsel")
	private Long totalPostConsultnCounsel;
	
	@Column(name = "patient_labtest")
	private Long patientLabTest;
	
	@Column(name = "swas_clinic")
	private Long swasClinic;
	
	@Column(name = "aswasam_clinic")
	private Long aswasamClinic;
	
	@Column(name = "ncd_clinic")
	private Long ncdClinic;
	
	@Column(name = "tot_sc_immunizatnclinic")
	private Long totScImmunizatnClinic;
	
	@Column(name = "tot_other_sc_clinic")
	private Long totOtherScClinic;
	
	@Column(name = "tot_outreach")
	private Long totOutreach;
	
	@Column(name = "tot_ncd_clinic")
	private Long totNcdClinic;
	
	@Column(name = "iec_healthpromo_activities")
	private Long iecHealthpromoActivities;
	
	@Column(name = "housevisit_mo")
	private Long housevisitMo;
	
	@Column(name = "housevisit_hs")
	private Long housevisitHs;

	@Column(name = "housevisit_phns")
	private Long housevisitPhns;
	
	@Column(name = "housevisit_hi")
	private Long housevisitHi;

	@Column(name = "housevisit_phl")
	private Long housevisitPhl;
	
	@Column(name = "housevisit_jhi")
	private Long housevisitJhi;
	
	@Column(name = "housevisit_jphn")
	private Long housevisitJphn;
	
	@Column(name = "fhc_meetingconductd")
	private Boolean fhcMeetingConductd;

	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "report_date")
	private Date reportDate;
	
	@Column(name = "last_modified")
	private String lastModified;
	
	@Column(name = "wardhealth_sanitation")
	private Long wardhealthSanitation;
	
	@Column(name = "nutrition_committee_meetings")
	private Long nutritionCommitteeMeetings;
	
	private HospitalMonthlyTracker hospitalMonthlyTracker;

//functionalComponents - 1
	//fieldActivities - 2
	
	//subcentre - 3
private Integer type;
	
	
	/**
 * @return the type
 */
public Integer getType() {
	return type;
}

/**
 * @param type the type to set
 */
public void setType(Integer type) {
	this.type = type;
}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getForenoonOp() {
		return forenoonOp;
	}

	public void setForenoonOp(Long forenoonOp) {
		this.forenoonOp = forenoonOp;
	}

	public Long getAfternoonOp() {
		return afternoonOp;
	}

	public void setAfternoonOp(Long afternoonOp) {
		this.afternoonOp = afternoonOp;
	}

	public Long getTotalPrecheck() {
		return totalPrecheck;
	}

	public void setTotalPrecheck(Long totalPrecheck) {
		this.totalPrecheck = totalPrecheck;
	}

	public Long getTotalPostConsultnCounsel() {
		return totalPostConsultnCounsel;
	}

	public void setTotalPostConsultnCounsel(Long totalPostConsultnCounsel) {
		this.totalPostConsultnCounsel = totalPostConsultnCounsel;
	}

	public Long getPatientLabTest() {
		return patientLabTest;
	}

	public void setPatientLabTest(Long patientLabTest) {
		this.patientLabTest = patientLabTest;
	}

	public Long getSwasClinic() {
		return swasClinic;
	}

	public void setSwasClinic(Long swasClinic) {
		this.swasClinic = swasClinic;
	}

	public Long getAswasamClinic() {
		return aswasamClinic;
	}

	public void setAswasamClinic(Long aswasamClinic) {
		this.aswasamClinic = aswasamClinic;
	}

	public Long getNcdClinic() {
		return ncdClinic;
	}

	public void setNcdClinic(Long ncdClinic) {
		this.ncdClinic = ncdClinic;
	}

	public Long getTotScImmunizatnClinic() {
		return totScImmunizatnClinic;
	}

	public void setTotScImmunizatnClinic(Long totScImmunizatnClinic) {
		this.totScImmunizatnClinic = totScImmunizatnClinic;
	}

	public Long getTotOtherScClinic() {
		return totOtherScClinic;
	}

	public void setTotOtherScClinic(Long totOtherScClinic) {
		this.totOtherScClinic = totOtherScClinic;
	}

	public Long getTotOutreach() {
		return totOutreach;
	}

	public void setTotOutreach(Long totOutreach) {
		this.totOutreach = totOutreach;
	}

	public Long getTotNcdClinic() {
		return totNcdClinic;
	}

	public void setTotNcdClinic(Long totNcdClinic) {
		this.totNcdClinic = totNcdClinic;
	}

	public Long getIecHealthpromoActivities() {
		return iecHealthpromoActivities;
	}

	public void setIecHealthpromoActivities(Long iecHealthpromoActivities) {
		this.iecHealthpromoActivities = iecHealthpromoActivities;
	}

	public Long getHousevisitMo() {
		return housevisitMo;
	}

	public void setHousevisitMo(Long housevisitMo) {
		this.housevisitMo = housevisitMo;
	}

	public Long getHousevisitHs() {
		return housevisitHs;
	}

	public void setHousevisitHs(Long housevisitHs) {
		this.housevisitHs = housevisitHs;
	}

	public Long getHousevisitPhns() {
		return housevisitPhns;
	}

	public void setHousevisitPhns(Long housevisitPhns) {
		this.housevisitPhns = housevisitPhns;
	}

	public Long getHousevisitHi() {
		return housevisitHi;
	}

	public void setHousevisitHi(Long housevisitHi) {
		this.housevisitHi = housevisitHi;
	}

	public Long getHousevisitPhl() {
		return housevisitPhl;
	}

	public void setHousevisitPhl(Long housevisitPhl) {
		this.housevisitPhl = housevisitPhl;
	}

	public Long getHousevisitJhi() {
		return housevisitJhi;
	}

	public void setHousevisitJhi(Long housevisitJhi) {
		this.housevisitJhi = housevisitJhi;
	}

	public Long getHousevisitJphn() {
		return housevisitJphn;
	}

	public void setHousevisitJphn(Long housevisitJphn) {
		this.housevisitJphn = housevisitJphn;
	}

	public Boolean getFhcMeetingConductd() {
		return fhcMeetingConductd;
	}

	public void setFhcMeetingConductd(Boolean fhcMeetingConductd) {
		this.fhcMeetingConductd = fhcMeetingConductd;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public Long getWardhealthSanitation() {
		return wardhealthSanitation;
	}

	public void setWardhealthSanitation(Long wardhealthSanitation) {
		this.wardhealthSanitation = wardhealthSanitation;
	}

	public Long getNutritionCommitteeMeetings() {
		return nutritionCommitteeMeetings;
	}

	public void setNutritionCommitteeMeetings(Long nutritionCommitteeMeetings) {
		this.nutritionCommitteeMeetings = nutritionCommitteeMeetings;
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
