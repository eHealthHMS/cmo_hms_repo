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
@Table(name = "monthlydata_fhc_chc")
public class MonthlyDataFhcChc implements Serializable{
	
	private static final long serialVersionUID = 6198698490528492269L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	/*-----------------------------phc hospital related starts------------------------------*/

	@Column(name = "forenoon_op_male")
	private Long forenoonOpMale;
	
	@Column(name = "forenoon_op_female")
	private Long forenoonOpFemale;
	
	@Column(name = "forenoon_op_tg")
	private Long forenoonOpMaleTg;
	
	@Column(name = "forenoon_op_tot")
	private Long forenoonOpTotal;
	
	@Column(name = "afternoon_op_male")
	private Long afternoonOpMale;
	
	@Column(name = "afternoon_op_female")
	private Long afternoonOpFemale;
	
	@Column(name = "afternoon_op_tg")
	private Long afternoonOpMaleTg;
	
	@Column(name = "afternoon_op_tot")
	private Long afternoonOpTotal;
	
	@Column(name = "total_precheck")
	private Long totalPrecheck;
	
	@Column(name = "total_postconsultncounsel")
	private Long totalPostConsultnCounsel;
	
	@Column(name = "patient_lab_test")
	private Long patientLabTest;
	
	@Column(name = "total_lab_test")
	private Long totalLabTest;
	
	@Column(name = "swas_clinic_new")
	private Long swasClinicNew;
	
	@Column(name = "aswasam_clinic_new")
	private Long aswasamClinicNew;
	
	@Column(name = "swas_clinic_followup")
	private Long swasClinicFollowup;
	
	@Column(name = "aswasam_clinic_followup")
	private Long aswasamClinicFollowup;
	
	@Column(name = "ncd_clinic_new")
	private Long ncdClinicNew;
	
	@Column(name = "ncd_clinic_followup")
	private Long ncdClinicFollowup;
	/*---------------------------ends---------------------------------------------*/
	
	/*---------------------------sub centre related starts---------------------------------------------*/
	
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
	
	@Column(name = "whsnc_meeting")
	private Long whsncMeeting;
	
	@Column(name = "regular_sc_clinic")
	private Long regularScClinic;
	
	
	@Column(name = "jagratha_activities")
	private Long jagrathaActivities;
	
	@Column(name = "total_attendee")
	private Long totalAttendee;
	
	
	/*---------------------------sub centre related ends---------------------------------------------*/
	
	/*---------------------------field activities  starts---------------------------------------------*/
	
	
	@Column(name = "housevisit_mo")
	private Long houseVisitMo;
	
	@Column(name = "housevisit_hs")
	private Long houseVisitHs;

	@Column(name = "housevisit_phns")
	private Long houseVisitPhns;
	
	@Column(name = "housevisit_hi")
	private Long houseVisitHi;

	@Column(name = "housevisit_phn")
	private Long houseVisitPhn;
	
	@Column(name = "housevisit_jhi")
	private Long houseVisitJhi;
	
	@Column(name = "housevisit_jphn")
	private Long houseVisitJphn;
	
	@Column(name = "housevisit_asha")
	private Long houseVisitAsha;
	
	
	//meeting
	@Column(name = "last_hmc_meeting")
	private Date lastHmcMeeting;
	
	
	@Column(name = "monthly_staff_conf")
	private Boolean monthlyStaffConf;
	
	@Column(name = "post_dmo_conf")
	private Boolean postDmoConf;
	
	@Column(name = "half_day_zonal")
	private Boolean halfDayZonal;
	
	@Column(name = "full_day_zonal")
	private Boolean fullDayZonal;
	
	
	@Column(name = "idsp_meetingconductd")
	private Boolean idspMeetingConductd;
	
	/*---------------------------field activities  ends---------------------------------------------*/
	
	
	

	@Column(name = "created_date")
	private Date createdDate;
	
	
//format should be 01 for date;previous month and year : time 00;
	@Column(name = "report_date")
	private Date reportDate;
	
	@Column(name = "last_modified")
	private String lastModified;
	
//	@Column(name = "wardhealth_sanitation")
//	private Long wardhealthSanitation;
	

	
	private HospitalMonthlyTracker hospitalMonthlyTracker;

//phc hospitals - 1
	//phc subcentre - 2
	//phc field work - 3
	
private Integer type;
	
	

/**
 * @return the forenoonOpMale
 */
public Long getForenoonOpMale() {
	return forenoonOpMale;
}

/**
 * @param forenoonOpMale the forenoonOpMale to set
 */
public void setForenoonOpMale(Long forenoonOpMale) {
	this.forenoonOpMale = forenoonOpMale;
}

/**
 * @return the forenoonOpFemale
 */
public Long getForenoonOpFemale() {
	return forenoonOpFemale;
}

/**
 * @param forenoonOpFemale the forenoonOpFemale to set
 */
public void setForenoonOpFemale(Long forenoonOpFemale) {
	this.forenoonOpFemale = forenoonOpFemale;
}

/**
 * @return the forenoonOpMaleTg
 */
public Long getForenoonOpMaleTg() {
	return forenoonOpMaleTg;
}

/**
 * @param forenoonOpMaleTg the forenoonOpMaleTg to set
 */
public void setForenoonOpMaleTg(Long forenoonOpMaleTg) {
	this.forenoonOpMaleTg = forenoonOpMaleTg;
}

/**
 * @return the afternoonOpMale
 */
public Long getAfternoonOpMale() {
	return afternoonOpMale;
}

/**
 * @param afternoonOpMale the afternoonOpMale to set
 */
public void setAfternoonOpMale(Long afternoonOpMale) {
	this.afternoonOpMale = afternoonOpMale;
}

/**
 * @return the afternoonOpFemale
 */
public Long getAfternoonOpFemale() {
	return afternoonOpFemale;
}

/**
 * @param afternoonOpFemale the afternoonOpFemale to set
 */
public void setAfternoonOpFemale(Long afternoonOpFemale) {
	this.afternoonOpFemale = afternoonOpFemale;
}

/**
 * @return the afternoonOpMaleTg
 */
public Long getAfternoonOpMaleTg() {
	return afternoonOpMaleTg;
}

/**
 * @param afternoonOpMaleTg the afternoonOpMaleTg to set
 */
public void setAfternoonOpMaleTg(Long afternoonOpMaleTg) {
	this.afternoonOpMaleTg = afternoonOpMaleTg;
}

/**
 * @return the totalLabTest
 */
public Long getTotalLabTest() {
	return totalLabTest;
}

/**
 * @param totalLabTest the totalLabTest to set
 */
public void setTotalLabTest(Long totalLabTest) {
	this.totalLabTest = totalLabTest;
}

/**
 * @return the swasClinicNew
 */
public Long getSwasClinicNew() {
	return swasClinicNew;
}

/**
 * @param swasClinicNew the swasClinicNew to set
 */
public void setSwasClinicNew(Long swasClinicNew) {
	this.swasClinicNew = swasClinicNew;
}

/**
 * @return the aswasamClinicNew
 */
public Long getAswasamClinicNew() {
	return aswasamClinicNew;
}

/**
 * @param aswasamClinicNew the aswasamClinicNew to set
 */
public void setAswasamClinicNew(Long aswasamClinicNew) {
	this.aswasamClinicNew = aswasamClinicNew;
}

/**
 * @return the swasClinicFollowup
 */
public Long getSwasClinicFollowup() {
	return swasClinicFollowup;
}

/**
 * @param swasClinicFollowup the swasClinicFollowup to set
 */
public void setSwasClinicFollowup(Long swasClinicFollowup) {
	this.swasClinicFollowup = swasClinicFollowup;
}

/**
 * @return the aswasamClinicFollowup
 */
public Long getAswasamClinicFollowup() {
	return aswasamClinicFollowup;
}

/**
 * @param aswasamClinicFollowup the aswasamClinicFollowup to set
 */
public void setAswasamClinicFollowup(Long aswasamClinicFollowup) {
	this.aswasamClinicFollowup = aswasamClinicFollowup;
}

/**
 * @return the ncdClinicNew
 */
public Long getNcdClinicNew() {
	return ncdClinicNew;
}

/**
 * @param ncdClinicNew the ncdClinicNew to set
 */
public void setNcdClinicNew(Long ncdClinicNew) {
	this.ncdClinicNew = ncdClinicNew;
}

/**
 * @return the ncdClinicFollowup
 */
public Long getNcdClinicFollowup() {
	return ncdClinicFollowup;
}

/**
 * @param ncdClinicFollowup the ncdClinicFollowup to set
 */
public void setNcdClinicFollowup(Long ncdClinicFollowup) {
	this.ncdClinicFollowup = ncdClinicFollowup;
}

/**
 * @return the whsncMeeting
 */
public Long getWhsncMeeting() {
	return whsncMeeting;
}

/**
 * @param whsncMeeting the whsncMeeting to set
 */
public void setWhsncMeeting(Long whsncMeeting) {
	this.whsncMeeting = whsncMeeting;
}

/**
 * @return the regularScClinic
 */
public Long getRegularScClinic() {
	return regularScClinic;
}

/**
 * @param regularScClinic the regularScClinic to set
 */
public void setRegularScClinic(Long regularScClinic) {
	this.regularScClinic = regularScClinic;
}

/**
 * @return the totalAttendee
 */
public Long getTotalAttendee() {
	return totalAttendee;
}

/**
 * @param totalAttendee the totalAttendee to set
 */
public void setTotalAttendee(Long totalAttendee) {
	this.totalAttendee = totalAttendee;
}

/**
 * @return the houseVisitMo
 */
public Long getHouseVisitMo() {
	return houseVisitMo;
}

/**
 * @param houseVisitMo the houseVisitMo to set
 */
public void setHouseVisitMo(Long houseVisitMo) {
	this.houseVisitMo = houseVisitMo;
}

/**
 * @return the houseVisitHs
 */
public Long getHouseVisitHs() {
	return houseVisitHs;
}

/**
 * @param houseVisitHs the houseVisitHs to set
 */
public void setHouseVisitHs(Long houseVisitHs) {
	this.houseVisitHs = houseVisitHs;
}

/**
 * @return the houseVisitPhns
 */
public Long getHouseVisitPhns() {
	return houseVisitPhns;
}

/**
 * @param houseVisitPhns the houseVisitPhns to set
 */
public void setHouseVisitPhns(Long houseVisitPhns) {
	this.houseVisitPhns = houseVisitPhns;
}

/**
 * @return the houseVisitHi
 */
public Long getHouseVisitHi() {
	return houseVisitHi;
}

/**
 * @param houseVisitHi the houseVisitHi to set
 */
public void setHouseVisitHi(Long houseVisitHi) {
	this.houseVisitHi = houseVisitHi;
}

/**
 * @return the houseVisitPhn
 */
public Long getHouseVisitPhn() {
	return houseVisitPhn;
}

/**
 * @param houseVisitPhn the houseVisitPhn to set
 */
public void setHouseVisitPhn(Long houseVisitPhn) {
	this.houseVisitPhn = houseVisitPhn;
}

/**
 * @return the houseVisitJhi
 */
public Long getHouseVisitJhi() {
	return houseVisitJhi;
}

/**
 * @param houseVisitJhi the houseVisitJhi to set
 */
public void setHouseVisitJhi(Long houseVisitJhi) {
	this.houseVisitJhi = houseVisitJhi;
}

/**
 * @return the houseVisitJphn
 */
public Long getHouseVisitJphn() {
	return houseVisitJphn;
}

/**
 * @param houseVisitJphn the houseVisitJphn to set
 */
public void setHouseVisitJphn(Long houseVisitJphn) {
	this.houseVisitJphn = houseVisitJphn;
}

/**
 * @return the houseVisitAsha
 */
public Long getHouseVisitAsha() {
	return houseVisitAsha;
}

/**
 * @param houseVisitAsha the houseVisitAsha to set
 */
public void setHouseVisitAsha(Long houseVisitAsha) {
	this.houseVisitAsha = houseVisitAsha;
}

/**
 * @return the monthlyStaffConf
 */
public Boolean getMonthlyStaffConf() {
	return monthlyStaffConf;
}

/**
 * @param monthlyStaffConf the monthlyStaffConf to set
 */
public void setMonthlyStaffConf(Boolean monthlyStaffConf) {
	this.monthlyStaffConf = monthlyStaffConf;
}

/**
 * @return the postDmoConf
 */
public Boolean getPostDmoConf() {
	return postDmoConf;
}

/**
 * @param postDmoConf the postDmoConf to set
 */
public void setPostDmoConf(Boolean postDmoConf) {
	this.postDmoConf = postDmoConf;
}

/**
 * @return the halfDayZonal
 */
public Boolean getHalfDayZonal() {
	return halfDayZonal;
}

/**
 * @param halfDayZonal the halfDayZonal to set
 */
public void setHalfDayZonal(Boolean halfDayZonal) {
	this.halfDayZonal = halfDayZonal;
}

/**
 * @return the fullDayZonal
 */
public Boolean getFullDayZonal() {
	return fullDayZonal;
}

/**
 * @param fullDayZonal the fullDayZonal to set
 */
public void setFullDayZonal(Boolean fullDayZonal) {
	this.fullDayZonal = fullDayZonal;
}

/**
 * @return the idspMeetingConductd
 */
public Boolean getIdspMeetingConductd() {
	return idspMeetingConductd;
}

/**
 * @param idspMeetingConductd the idspMeetingConductd to set
 */
public void setIdspMeetingConductd(Boolean idspMeetingConductd) {
	this.idspMeetingConductd = idspMeetingConductd;
}
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

//	public Long getForenoonOp() {
//		return forenoonOp;
//	}
//
//	public void setForenoonOp(Long forenoonOp) {
//		this.forenoonOp = forenoonOp;
//	}
//
//	public Long getAfternoonOp() {
//		return afternoonOp;
//	}
//
//	public void setAfternoonOp(Long afternoonOp) {
//		this.afternoonOp = afternoonOp;
//	}

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

//	public Long getSwasClinic() {
//		return swasClinic;
//	}
//
//	public void setSwasClinic(Long swasClinic) {
//		this.swasClinic = swasClinic;
//	}
//
//	public Long getAswasamClinic() {
//		return aswasamClinic;
//	}
//
//	public void setAswasamClinic(Long aswasamClinic) {
//		this.aswasamClinic = aswasamClinic;
//	}
//
//	public Long getNcdClinic() {
//		return ncdClinic;
//	}
//
//	public void setNcdClinic(Long ncdClinic) {
//		this.ncdClinic = ncdClinic;
//	}

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

//	public Long getHousevisitMo() {
//		return housevisitMo;
//	}
//
//	public void setHousevisitMo(Long housevisitMo) {
//		this.housevisitMo = housevisitMo;
//	}
//
//	public Long getHousevisitHs() {
//		return housevisitHs;
//	}
//
//	public void setHousevisitHs(Long housevisitHs) {
//		this.housevisitHs = housevisitHs;
//	}
//
//	public Long getHousevisitPhns() {
//		return housevisitPhns;
//	}
//
//	public void setHousevisitPhns(Long housevisitPhns) {
//		this.housevisitPhns = housevisitPhns;
//	}
//
//	public Long getHousevisitHi() {
//		return housevisitHi;
//	}
//
//	public void setHousevisitHi(Long housevisitHi) {
//		this.housevisitHi = housevisitHi;
//	}
//
//	public Long getHousevisitPhl() {
//		return housevisitPhl;
//	}
//
//	public void setHousevisitPhl(Long housevisitPhl) {
//		this.housevisitPhl = housevisitPhl;
//	}
//
//	public Long getHousevisitJhi() {
//		return housevisitJhi;
//	}
//
//	public void setHousevisitJhi(Long housevisitJhi) {
//		this.housevisitJhi = housevisitJhi;
//	}
//
//	public Long getHousevisitJphn() {
//		return housevisitJphn;
//	}
//
//	public void setHousevisitJphn(Long housevisitJphn) {
//		this.housevisitJphn = housevisitJphn;
//	}
//
//	public Boolean getFhcMeetingConductd() {
//		return fhcMeetingConductd;
//	}
//
//	public void setFhcMeetingConductd(Boolean fhcMeetingConductd) {
//		this.fhcMeetingConductd = fhcMeetingConductd;
//	}

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

//	public Long getWardhealthSanitation() {
//		return wardhealthSanitation;
//	}
//
//	public void setWardhealthSanitation(Long wardhealthSanitation) {
//		this.wardhealthSanitation = wardhealthSanitation;
//	}
//
//	public Long getNutritionCommitteeMeetings() {
//		return nutritionCommitteeMeetings;
//	}
//
//	public void setNutritionCommitteeMeetings(Long nutritionCommitteeMeetings) {
//		this.nutritionCommitteeMeetings = nutritionCommitteeMeetings;
//	}


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	public HospitalMonthlyTracker getHospitalMonthlyTracker() {
		return hospitalMonthlyTracker;
	}

	public void setHospitalMonthlyTracker(HospitalMonthlyTracker hospitalMonthlyTracker) {
		this.hospitalMonthlyTracker = hospitalMonthlyTracker;
	}

	/**
	 * @return the forenoonOpTotal
	 */
	public Long getForenoonOpTotal() {
		return forenoonOpTotal;
	}

	/**
	 * @param forenoonOpTotal the forenoonOpTotal to set
	 */
	public void setForenoonOpTotal(Long forenoonOpTotal) {
		this.forenoonOpTotal = forenoonOpTotal;
	}

	/**
	 * @return the afternoonOpTotal
	 */
	public Long getAfternoonOpTotal() {
		return afternoonOpTotal;
	}

	/**
	 * @param afternoonOpTotal the afternoonOpTotal to set
	 */
	public void setAfternoonOpTotal(Long afternoonOpTotal) {
		this.afternoonOpTotal = afternoonOpTotal;
	}

	/**
	 * @return the lastHmcMeeting
	 */
	public Date getLastHmcMeeting() {
		return lastHmcMeeting;
	}

	/**
	 * @param lastHmcMeeting the lastHmcMeeting to set
	 */
	public void setLastHmcMeeting(Date lastHmcMeeting) {
		this.lastHmcMeeting = lastHmcMeeting;
	}

	/**
	 * @return the jagrathaActivities
	 */
	public Long getJagrathaActivities() {
		return jagrathaActivities;
	}

	/**
	 * @param jagrathaActivities the jagrathaActivities to set
	 */
	public void setJagrathaActivities(Long jagrathaActivities) {
		this.jagrathaActivities = jagrathaActivities;
	}

	/**
	 * @return the totallabTest
	 */
//	public Long getTotallabTest() {
//		return totallabTest;
//	}
//
//	/**
//	 * @param totallabTest the totallabTest to set
//	 */
//	public void setTotallabTest(Long totallabTest) {
//		this.totallabTest = totallabTest;
//	}
	

}
