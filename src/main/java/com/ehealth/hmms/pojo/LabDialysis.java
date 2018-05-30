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
@Table(name = "lab_dialysis_xray_pharmacy")
public class LabDialysis implements Serializable{
	
	private static final long serialVersionUID = -3042387409531442341L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "lab_patients_tested")
	private Long labPatientsTested;
	
	@Column(name = "lab_total_tests")
	private Long labTotalTests;
	
	@Column(name = "lab_usg_func_machines")
	private Boolean labUsgFuncMachines;
	
	@Column(name = "lab_usg_count")
	private Long labUsgCount;
	
	@Column(name = "lab_ecg_count")
	private Long labEcgCount;

	@Column(name = "dia_shift_functioning")
	private Long diaShiftFunctioning;
	
	@Column(name = "dia_patient_count")
	private Long diaPatientCount; 	
	
	@Column(name = "dia_total_count")
	private Long diaTotalCount;
	
	@Column(name = "dia_patient_waiting")
	private Long diaPatientWaiting;
	
	@Column(name = "xray_units_functioning")
	private Long xrayUnitsFunctioning;
	
	@Column(name = "total_xray_taken")
	private Long totalXrayTaken;
	
	@Column(name = "ph_arv_availability")
	private Boolean phArvAvailability;
	
	@Column(name = "ph_asv_availability")
	private Boolean phAsvAvailability;
	
	@Column(name = "blood_bank")
	private Boolean bloodBank;
	
	@Column(name = "blood_storage_unit")
	private Boolean bloodStorageUnit;
	
	@Column(name = "ph_shortage_details")
	private Boolean phShortageDetails;
	
	private HospitalMonthlyTracker hospitalMonthlyTracker;
	
	private DrugAvailabilityStatus drugAvailabilityStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLabPatientsTested() {
		return labPatientsTested;
	}

	public void setLabPatientsTested(Long labPatientsTested) {
		this.labPatientsTested = labPatientsTested;
	}

	public Long getLabTotalTests() {
		return labTotalTests;
	}

	public void setLabTotalTests(Long labTotalTests) {
		this.labTotalTests = labTotalTests;
	}

	public Boolean getLabUsgFuncMachines() {
		return labUsgFuncMachines;
	}

	public void setLabUsgFuncMachines(Boolean labUsgFuncMachines) {
		this.labUsgFuncMachines = labUsgFuncMachines;
	}

	public Long getLabUsgCount() {
		return labUsgCount;
	}

	public void setLabUsgCount(Long labUsgCount) {
		this.labUsgCount = labUsgCount;
	}

	public Long getLabEcgCount() {
		return labEcgCount;
	}

	public void setLabEcgCount(Long labEcgCount) {
		this.labEcgCount = labEcgCount;
	}

	public Long getDiaShiftFunctioning() {
		return diaShiftFunctioning;
	}

	public void setDiaShiftFunctioning(Long diaShiftFunctioning) {
		this.diaShiftFunctioning = diaShiftFunctioning;
	}

	public Long getDiaPatientCount() {
		return diaPatientCount;
	}

	public void setDiaPatientCount(Long diaPatientCount) {
		this.diaPatientCount = diaPatientCount;
	}

	public Long getDiaTotalCount() {
		return diaTotalCount;
	}

	public void setDiaTotalCount(Long diaTotalCount) {
		this.diaTotalCount = diaTotalCount;
	}

	public Long getDiaPatientWaiting() {
		return diaPatientWaiting;
	}

	public void setDiaPatientWaiting(Long diaPatientWaiting) {
		this.diaPatientWaiting = diaPatientWaiting;
	}

	public Long getXrayUnitsFunctioning() {
		return xrayUnitsFunctioning;
	}

	public void setXrayUnitsFunctioning(Long xrayUnitsFunctioning) {
		this.xrayUnitsFunctioning = xrayUnitsFunctioning;
	}

	public Long getTotalXrayTaken() {
		return totalXrayTaken;
	}

	public void setTotalXrayTaken(Long totalXrayTaken) {
		this.totalXrayTaken = totalXrayTaken;
	}

	public Boolean getPhArvAvailability() {
		return phArvAvailability;
	}

	public void setPhArvAvailability(Boolean phArvAvailability) {
		this.phArvAvailability = phArvAvailability;
	}

	public Boolean getPhAsvAvailability() {
		return phAsvAvailability;
	}

	public void setPhAsvAvailability(Boolean phAsvAvailability) {
		this.phAsvAvailability = phAsvAvailability;
	}

	public Boolean getBloodBank() {
		return bloodBank;
	}

	public void setBloodBank(Boolean bloodBank) {
		this.bloodBank = bloodBank;
	}

	public Boolean getBloodStorageUnit() {
		return bloodStorageUnit;
	}

	public void setBloodStorageUnit(Boolean bloodStorageUnit) {
		this.bloodStorageUnit = bloodStorageUnit;
	}

	public Boolean getPhShortageDetails() {
		return phShortageDetails;
	}

	public void setPhShortageDetails(Boolean phShortageDetails) {
		this.phShortageDetails = phShortageDetails;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	public DrugAvailabilityStatus getDrugAvailabilityStatus() {
		return drugAvailabilityStatus;
	}

	public void setDrugAvailabilityStatus(DrugAvailabilityStatus drugAvailabilityStatus) {
		this.drugAvailabilityStatus = drugAvailabilityStatus;
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
